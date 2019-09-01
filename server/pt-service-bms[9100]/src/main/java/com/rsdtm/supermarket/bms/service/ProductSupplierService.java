package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.ProductDao;
import com.rsdtm.supermarket.bms.dao.ProductSupplierDao;
import com.rsdtm.supermarket.bms.dao.SupplierDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProductSupplier;
import com.rsdtm.supermarket.mbg.query.ProductSupplierParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductSupplierVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSupplierService {
    @Autowired
    ProductDao productDao;

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    ProductSupplierDao productSupplierDao;



    @Transactional
    public int addProductSupplier(Integer productId, Integer supplierId, String operator) {
        checkProductAndSupplier(productId, supplierId);
        BmsProductSupplier bmsProductSupplier = new BmsProductSupplier();
        bmsProductSupplier.setProductId(productId);
        bmsProductSupplier.setSupplierId(supplierId);
        bmsProductSupplier.setOperator(operator);
        bmsProductSupplier.setCreateTime(new Date());
        return productSupplierDao.insert(bmsProductSupplier);
    }

    private void checkProductAndSupplier(Integer productId, Integer supplierId) {
        if(!productDao.existsWithPrimaryKey(productId))
            throw new PtException(ExceptionEnum.PRODUCT_ID_NOT_NULL);
        if (!supplierDao.existsWithPrimaryKey(supplierId))
            throw new PtException(ExceptionEnum.SUPPLIER_ID_NOT_NULL);
    }

    @Transactional
    public int deleteProductSupplierById(Integer id) {
        if (!productSupplierDao.existsWithPrimaryKey(id)) {
            throw new PtException(ExceptionEnum.PRODUCT_SUPPLIER_NOT_EXIST);
        }
        return productSupplierDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateProductSupplier(BmsProductSupplier bmsProductSupplier) {
        if (bmsProductSupplier.getId() == null)
            throw new PtException(ExceptionEnum.PRODUCT_SUPPLIER_ID_NOT_NULL);
        if(!productSupplierDao.existsWithPrimaryKey(bmsProductSupplier.getId()))
            throw new PtException(ExceptionEnum.PRODUCT_SUPPLIER_NOT_EXIST);
        if (bmsProductSupplier.getProductId() == null)
            throw new PtException(ExceptionEnum.PRODUCT_ID_NOT_NULL);
        if (bmsProductSupplier.getSupplierId() == null)
            throw new PtException(ExceptionEnum.SUPPLIER_ID_NOT_NULL);
        checkProductAndSupplier(bmsProductSupplier.getProductId(),bmsProductSupplier.getSupplierId());
        return productSupplierDao.updateByPrimaryKeySelective(bmsProductSupplier);
    }

    public ProductSupplierVo getProductSupplierById(Integer id) {
        if(!productSupplierDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.PRODUCT_SUPPLIER_NOT_EXIST);
       return productSupplierDao.getProductSupplierById(id);
    }
    private ProductSupplierVo builder(BmsProductSupplier bmsProductSupplier){
        ProductSupplierVo temp = new ProductSupplierVo();
        BeanUtils.copyProperties(bmsProductSupplier,temp);
        if (bmsProductSupplier.getProductId()!=null)
            temp.setProductName(productDao.selectByPrimaryKey(bmsProductSupplier.getProductId()).getName());
        if (bmsProductSupplier.getSupplierId() != null)
            temp.setSupplierName(supplierDao.selectByPrimaryKey(bmsProductSupplier.getSupplierId()).getName());
        return temp;
    }

    public PageResult<ProductSupplierVo> getProductSupplierByPage(int page, int rows, ProductSupplierParamVo paramVo) {
        PageHelper.startPage(page,rows);
        Example example = new Example(BmsProductSupplier.class);
        Example.Criteria criteria = example.createCriteria();
        if (paramVo.getProductId()!=null)
            criteria.andEqualTo("productId",paramVo.getProductId());
        if (paramVo.getSupplierId()!=null)
            criteria.andEqualTo("supplierId",paramVo.getSupplierId());
        if (paramVo.getStartDate()!=null)
            criteria.andGreaterThanOrEqualTo("createTime",paramVo.getStartDate());
        if (paramVo.getEndDate()!=null)
            criteria.andLessThanOrEqualTo("createTime",paramVo.getEndDate());
        if (StringUtils.isNotEmpty(paramVo.getOperator()))
            criteria.andLike("operator",paramVo.getOperator());

        Page<BmsProductSupplier> pageInfo = (Page<BmsProductSupplier>) productSupplierDao.selectByExample(example);
        List<BmsProductSupplier> pageResult = pageInfo.getResult();

        List<ProductSupplierVo> collect = pageResult.stream().map(this::builder).collect(Collectors.toList());
        PageResult<ProductSupplierVo> result = PageResult.restPage(collect);
        result.setPageSize(rows);
        result.setPageNum(page);
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        return result;
    }
}
