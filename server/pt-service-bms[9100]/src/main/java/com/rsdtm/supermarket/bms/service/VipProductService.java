package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.VipProductDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsVipProduct;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VipProductService {
    @Autowired
    VipProductDao vipProductDao;

    @Transactional
    public int addVipProduct(BmsVipProduct bmsVipProduct) {
        bmsVipProduct.setId(null);
        checkNull(bmsVipProduct);
        if (vipProductDao.checkExist(bmsVipProduct)!=0)
            throw new PtException(ExceptionEnum.VIP_PRODUCT_IS_EXIST);
        return vipProductDao.insert(bmsVipProduct);
    }

    private void checkNull(BmsVipProduct bmsVipProduct) {
        if (bmsVipProduct.getProductId()==null)
            throw new PtException(ExceptionEnum.PRODUCT_ID_NOT_NULL);
        if (bmsVipProduct.getVipId() == null)
            throw new PtException(ExceptionEnum.VIP_ID_NOT_NULL);
        if (bmsVipProduct.getPrice() == null)
            throw new PtException(ExceptionEnum.VIP_PRODUCT_PRICE_NOT_NULL);
    }

    @Transactional
    public int deleteVipProductById(Integer id) {
        if (!vipProductDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.VIP_PRODUCT_NOT_EXIST);
        return vipProductDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateVipProduct(BmsVipProduct bmsVipProduct) {
        if (bmsVipProduct.getId()==null)
            throw new PtException(ExceptionEnum.VIP_PRODUCT_ID_NOT_NULL);
        checkNull(bmsVipProduct);
        if (!vipProductDao.existsWithPrimaryKey(bmsVipProduct.getId()))
            throw new PtException(ExceptionEnum.VIP_PRODUCT_NOT_EXIST);
        return vipProductDao.updateByPrimaryKey(bmsVipProduct);
    }

    public VipProductVo getVipProductVoById(Integer id) {
        if (!vipProductDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.VIP_PRODUCT_NOT_EXIST);
        return vipProductDao.getVipProductVoById(id);
    }


    public PageResult<VipProductVo> queryVipProductVoByPage(int page, int rows, BmsVipProduct bmsVipProduct) {
        PageHelper.startPage(page,rows);

        Example example = new Example(BmsVipProduct.class);
        Example.Criteria criteria = example.createCriteria();
        if (bmsVipProduct.getProductId() != null)
            criteria.andEqualTo("productId",bmsVipProduct.getProductId());
        if (bmsVipProduct.getVipId() != null)
            criteria.andEqualTo("vipId",bmsVipProduct.getVipId());
        example.selectProperties("id");

        //封装数据
        Page<BmsVipProduct> bmsVipProducts = (Page<BmsVipProduct>) vipProductDao.selectByExample(example);
        PageResult<VipProductVo> result = PageResult.restPage(bmsVipProducts.stream().map(b -> getVipProductVoById(b.getId())).collect(Collectors.toList()));
        result.setTotalPage(bmsVipProducts.getPages());
        result.setTotal(bmsVipProducts.getTotal());
        result.setPageNum(page);
        result.setPageSize(rows);

        return result;
    }

    public List<VipProductVo> getVipProductVoByProductId(Integer id) {

        return vipProductDao.getVipProductVoByProductId(id);
    }

    public VipProductVo getVipProductVoByProductIdAndUserId(Integer productId, Integer userId) {
        return vipProductDao.getVipProductVoByProductIdAndUserId(productId,userId);

    }
}
