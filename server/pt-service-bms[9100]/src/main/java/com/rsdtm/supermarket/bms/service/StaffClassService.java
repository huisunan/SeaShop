package com.rsdtm.supermarket.bms.service;

import com.mysql.jdbc.PacketTooBigException;
import com.rsdtm.supermarket.bms.dao.StaffClassDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.mbg.model.BmsStaffClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffClassService{
    @Autowired
    StaffClassDao staffClassDao;

    @Transactional
    public int addStaffClass(BmsStaffClass bmsStaffClass) {
        bmsStaffClass.setId(null);
        return staffClassDao.insert(bmsStaffClass);
    }

    @Transactional
    public int deleteStaffClassById(Integer id) {
        if (!staffClassDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_CLASS_NOT_EXIST);
        return staffClassDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateStaffClass(BmsStaffClass bmsStaffClass) {
        if (bmsStaffClass.getId() == null)
            throw new PtException(ExceptionEnum.STAFF_CLASS_ID_NOT_NULL);
        if (!staffClassDao.existsWithPrimaryKey(bmsStaffClass.getId()))
            throw new PtException(ExceptionEnum.STAFF_CLASS_NOT_EXIST);
        return staffClassDao.updateByPrimaryKeySelective(bmsStaffClass);
    }

    public BmsStaffClass queryStaffClassById(Integer id) {
        if (!staffClassDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_CLASS_NOT_EXIST);
        return staffClassDao.selectByPrimaryKey(id);
    }

    public List<BmsStaffClass> queryAllStaffClass() {
        return staffClassDao.selectAll();
    }
}
