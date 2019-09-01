package com.rsdtm.supermarket.bms.service;

import com.rsdtm.supermarket.bms.dao.ProductCategoryDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.mbg.model.BmsProductCategory;
import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import com.rsdtm.supermarket.mbg.vo.ProductCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryDao categoryDao;

    public BmsProductCategory getProductCategory(Integer id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Transactional
    public int addProductCategory(BmsProductCategory productCategory) {
        productCategory.setId(null);
        return categoryDao.insert(productCategory);
    }

    @Transactional
    public int deleteProductCategory(Integer id) {
        return deleteChildren(id);
    }

    private int deleteChildren(Integer id){
        BmsProductCategory temp = new BmsProductCategory();
        //设置查询条件，查询所有父ID
        temp.setParentId(id);
        //查到所有ParenID为id的项
        List<BmsProductCategory> select = categoryDao.select(temp);
        for (BmsProductCategory productCategory : select) {
            deleteChildren(productCategory.getId());
            categoryDao.deleteByPrimaryKey(productCategory.getId());
        }
        return categoryDao.deleteByPrimaryKey(id);
    }


    @Transactional
    public int updateProductCategory(BmsProductCategory productCategory) {
        if (productCategory.getId()==null)
            throw  new PtException(ExceptionEnum.CATEGORY_ID_NOT_NULL);

        BmsProductCategory temp = new BmsProductCategory();
        temp.setId(productCategory.getId());
        int i = categoryDao.selectCount(temp);
        if (i==0)
            throw new PtException(ExceptionEnum.CATEGORY_NOT_EXIST);
        return categoryDao.updateByPrimaryKeySelective(productCategory);
    }


    public ProductCategoryVo getProductCategoryTree(Integer id) {
        //设置父类信息
        ProductCategoryVo categoryVo = new ProductCategoryVo();
        if (id == 0){
            categoryVo.setId(0);
            categoryVo.setName("根结点");
            categoryVo.setType(0);
        }else {
            BmsProductCategory productCategory = categoryDao.selectByPrimaryKey(id);
            BeanUtils.copyProperties(productCategory,categoryVo);
        }
        categoryVo.setChildrenCategories(findListByParentId(id));

        return categoryVo;
    }

    public List<ProductCategoryVo> findListByParentId(Integer id){
        //查询所有子类
        BmsProductCategory temp = new BmsProductCategory();
        temp.setParentId(id);
        List<BmsProductCategory> select = categoryDao.select(temp);
        //创建List
        List<ProductCategoryVo> list = new LinkedList<>();
        //填写数据
        for (BmsProductCategory productCategory : select) {
            ProductCategoryVo tempVo = new ProductCategoryVo();
            BmsProductCategory tempCategory = categoryDao.selectByPrimaryKey(productCategory.getId());
            BeanUtils.copyProperties(tempCategory,tempVo);
            tempVo.setChildrenCategories(findListByParentId(productCategory.getId()));
            list.add(tempVo);
        }
        return list;
    }

    @Transactional
    public int moveProductCategory(Integer categoryId, Integer parentID) {
        BmsProductCategory bmsProductCategory = new BmsProductCategory();
        bmsProductCategory.setId(categoryId);
        bmsProductCategory.setParentId(parentID);
        return categoryDao.updateByPrimaryKeySelective(bmsProductCategory);
    }
}
