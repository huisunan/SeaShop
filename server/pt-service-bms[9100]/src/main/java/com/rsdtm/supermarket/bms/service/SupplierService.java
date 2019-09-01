package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.SupplierDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import com.rsdtm.supermarket.mbg.query.SupplierParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierDao supplierDao;

    @Transactional
    public int addSupplier(BmsSupplier bmsSupplier) {
        bmsSupplier.setId(null);
        bmsSupplier.setCreateTime(new Date());
        return supplierDao.insert(bmsSupplier);
    }

    @Transactional
    public int deleteSupplier(Integer id) {
        return supplierDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateSupplier(BmsSupplier bmsSupplier){
        if (bmsSupplier.getId()==null)
            throw  new PtException(ExceptionEnum.SUPPLIER_ID_NOT_NULL);
        if (!supplierDao.existsWithPrimaryKey(bmsSupplier.getId()))
            throw new PtException(ExceptionEnum.SUPPLIER_NOT_EXIST);
        return supplierDao.updateByPrimaryKeySelective(bmsSupplier);
    }

    public BmsSupplier getSupplierById(Integer id) {
        return supplierDao.selectByPrimaryKey(id);
    }

    public PageResult<BmsSupplier> querySupplierByPage(int page, int rows, SupplierParamVo paramVo) {
        PageHelper.startPage(page,rows);
        Example example = new Example(BmsSupplier.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(paramVo.getName()))
            criteria.andLike("name","%"+paramVo.getName()+"%");
        if (StringUtils.isNotEmpty(paramVo.getAddress()))
            criteria.andLike("address","%"+paramVo.getAddress()+"%");
        if (StringUtils.isNotEmpty(paramVo.getLeader()))
            criteria.andLike("leader","%"+paramVo.getLeader()+"%");
        if (paramVo.getStartTime()!=null)
            criteria.andGreaterThanOrEqualTo("createTime",paramVo.getStartTime());
        if (paramVo.getEndTime()!=null)
            criteria.andLessThanOrEqualTo("createTime",paramVo.getEndTime());
        Page<BmsSupplier> pageInfo = (Page<BmsSupplier>) supplierDao.selectByExample(example);
        return PageResult.restPage(pageInfo);
    }

    public List<BmsSupplier> querySupplierByProductId(Integer id) {
        return supplierDao.querySupplierByProductId(id);
    }
}
