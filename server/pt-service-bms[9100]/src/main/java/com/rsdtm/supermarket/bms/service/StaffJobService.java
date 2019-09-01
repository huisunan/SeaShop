package com.rsdtm.supermarket.bms.service;

import com.rsdtm.supermarket.bms.dao.StaffJobDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import com.rsdtm.supermarket.mbg.vo.BmsStaffJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffJobService {
    @Autowired
    StaffJobDao staffJobDao;

    @Transactional
    public int addStaffJob(BmsStaffJob bmsStaffJob) {
        bmsStaffJob.setId(null);
        return staffJobDao.insert(bmsStaffJob);
    }

    @Transactional
    public int deleteStaffJobById(Integer id) {
        if (!staffJobDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_JOB_NOT_EXIST);
        return staffJobDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateStaffJob(BmsStaffJob bmsStaffJob) {
        if (bmsStaffJob.getId() == null)
            throw new PtException(ExceptionEnum.STAFF_JOB_ID_NOT_NULL);

        if (!staffJobDao.existsWithPrimaryKey(bmsStaffJob.getId()))
            throw new PtException(ExceptionEnum.STAFF_JOB_NOT_EXIST);

        return staffJobDao.updateByPrimaryKeySelective(bmsStaffJob);
    }

    public BmsStaffJobVo queryStaffJobById(Integer id) {
        if (!staffJobDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_JOB_NOT_EXIST);
        return staffJobDao.queryStaffJobById(id);
    }

    public List<BmsStaffJobVo> queryAllStaffJob() {
       return staffJobDao.queryAllStaffJob();
    }
}
