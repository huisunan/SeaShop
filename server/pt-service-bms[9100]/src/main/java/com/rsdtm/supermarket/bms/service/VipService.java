package com.rsdtm.supermarket.bms.service;

import com.rsdtm.supermarket.bms.dao.VipDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.mbg.model.BmsVip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VipService {
    @Autowired
    VipDao vipDao;


    @Transactional
    public int addVip(BmsVip bmsVip) {
        bmsVip.setId(null);
        return vipDao.insert(bmsVip);
    }

    @Transactional
    public int deleteVipById(Integer id) {
        if (!vipDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.VIP_NOT_EXIST);
        return vipDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateVip(BmsVip bmsVip) {
        if (!vipDao.existsWithPrimaryKey(bmsVip.getId()))
            throw new PtException(ExceptionEnum.VIP_NOT_EXIST);
        return vipDao.updateByPrimaryKeySelective(bmsVip);
    }

    public BmsVip queryVipById(Integer id) {

        return vipDao.selectByPrimaryKey(id);
    }

    public List<BmsVip> queryAllVip() {
        return vipDao.selectAll();
    }
}
