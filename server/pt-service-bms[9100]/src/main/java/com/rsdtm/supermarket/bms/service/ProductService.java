package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.ProductCategoryDao;
import com.rsdtm.supermarket.bms.dao.ProductDao;
import com.rsdtm.supermarket.bms.dao.SupplierDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProduct;
import com.rsdtm.supermarket.mbg.model.BmsProductCategory;
import com.rsdtm.supermarket.mbg.query.ProductParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Transactional
    public int addProduct(BmsProduct bmsProduct) {
        bmsProduct.setId(null);
        return productDao.insert(bmsProduct);
    }



    @Transactional
    public int deleteProduct(Integer id) {
        if (!productDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.PRODUCT_NOT_EXIST);
        return productDao.deleteByPrimaryKey(id);
    }

    private ProductVo productVoBuilder(BmsProduct bmsProduct){
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(bmsProduct,productVo);
        if (bmsProduct.getProductCategoryId() != null)
        {
            BmsProductCategory productCategory = productCategoryDao.selectByPrimaryKey(bmsProduct.getProductCategoryId());
            if (productCategory!=null&&StringUtils.isNotEmpty(productCategory.getName()))
                productVo.setCategoryName(productCategory.getName());
        }

        productVo.setCategoryNodeList(getNodeList(bmsProduct.getProductCategoryId()));
       return productVo;
    }

    public ProductVo getProductById(Integer id) {
        BmsProduct bmsProduct = productDao.selectByPrimaryKey(id);
        return productVoBuilder(bmsProduct);
    }

    private List<Integer> getNodeList(Integer id){
       if (!productCategoryDao.existsWithPrimaryKey(id))
           return null;
        BmsProductCategory productCategory = productCategoryDao.selectByPrimaryKey(id);
        List<Integer> result = new ArrayList<>();
        while (true){
            result.add(0,productCategory.getId());
            if (productCategory.getType()==1)
                break;
            productCategory = productCategoryDao.selectByPrimaryKey(productCategory.getParentId());
        }
        result.add(0,0);
        return result;
    }


    @Transactional
    public int updateProduct(BmsProduct bmsProduct) {
        if (bmsProduct.getId() == null)
            throw new PtException(ExceptionEnum.PRODUCT_ID_NOT_NULL);
        if (!productDao.existsWithPrimaryKey(bmsProduct.getId()))
            throw new PtException(ExceptionEnum.PRODUCT_NOT_EXIST);
        return productDao.updateByPrimaryKeySelective(bmsProduct);
    }

    public PageResult<ProductVo> queryProductByPage(int page, int rows, ProductParamVo paramVo) {
        PageHelper.startPage(page,rows);

        //创建
        Example example = new Example(BmsProduct.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(paramVo.getName()))
            criteria.andLike("name","%"+paramVo.getName()+"%");
        if(paramVo.getEnable()!=null)
            criteria.andEqualTo("enable",paramVo.getEnable());
        if (paramVo.getProductCategoryId()!=null)
            criteria.andEqualTo("productCategoryId",paramVo.getProductCategoryId());
        //根据权重排序
        if (BooleanUtils.isTrue(paramVo.getSort()))
            example.orderBy("sort").desc();
        if (paramVo.getStartPrice() != null)
            criteria.andGreaterThanOrEqualTo("price",paramVo.getStartPrice());
        if (paramVo.getEndPrice() != null)
            criteria.andLessThanOrEqualTo("price",paramVo.getEndPrice());
        if (paramVo.getStartSale() != null)
            criteria.andGreaterThanOrEqualTo("sale",paramVo.getStartSale());
        if (paramVo.getEndSale() != null)
            criteria.andLessThanOrEqualTo("sale",paramVo.getEndSale());
        if (paramVo.getStartPromotionPrice() != null)
            criteria.andGreaterThanOrEqualTo("promotionPrice",paramVo.getStartPromotionPrice());
        if (paramVo.getEndPromotionPrice() !=null)
            criteria.andLessThanOrEqualTo("promotionPrice",paramVo.getEndPromotionPrice());
        if (paramVo.getStartGiftPoint() != null)
            criteria.andGreaterThanOrEqualTo("giftPoint",paramVo.getStartGiftPoint());
        if (paramVo.getEndGiftPoint() != null)
            criteria.andLessThanOrEqualTo("giftPoint",paramVo.getEndGiftPoint());
        if (paramVo.getStartStock() != null)
            criteria.andGreaterThanOrEqualTo("stock",paramVo.getStartStock());
        if (paramVo.getEndStock() != null)
            criteria.andLessThanOrEqualTo("stock",paramVo.getEndStock());
        if (paramVo.getStartLowStock() != null)
            criteria.andGreaterThanOrEqualTo("lowStock",paramVo.getStartLowStock());
        if (paramVo.getEndLowStock() != null)
            criteria.andLessThanOrEqualTo("lowStock",paramVo.getEndLowStock());
        if (BooleanUtils.isTrue(paramVo.getStockLessLowStock()))
            criteria.andCondition("stock<=low_stock");
        //获取按条件查询的结果
        Page<BmsProduct> bmsProductsPage = (Page<BmsProduct>) productDao.selectByExample(example);
        List<BmsProduct> bmsProducts = bmsProductsPage.getResult();

        //将结果封装
        List<ProductVo> productVoList = bmsProducts.stream().map(this::productVoBuilder).collect(Collectors.toList());

        PageResult<ProductVo> result = PageResult.restPage(productVoList);
        result.setTotal(bmsProductsPage.getTotal());
        result.setTotalPage(bmsProductsPage.getPages());
        result.setPageNum(page);
        result.setPageSize(rows);


        return result;
    }

    public List<ProductVo> queryProductBySupplierId(Integer id) {
        List<BmsProduct> bmsProducts = productDao.queryProductBySupplierId(id);
        return bmsProducts.stream().map(this::productVoBuilder).collect(Collectors.toList());
    }
}
