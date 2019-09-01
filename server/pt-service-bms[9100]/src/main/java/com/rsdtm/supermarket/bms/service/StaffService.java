package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.StaffDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsStaff;
import com.rsdtm.supermarket.mbg.query.StaffParamVo;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    StaffDao staffDao;

    @Transactional
    public int addStaff(BmsStaff bmsStaff) {
        bmsStaff.setId(null);
        bmsStaff.setCreateTime(new Date());
        return staffDao.insert(bmsStaff);
    }

    @Transactional
    public int deleteStaffById(Integer id) {
        if (!staffDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_NOT_EXIST);
        return staffDao.deleteByPrimaryKey(id);
    }
    @Transactional
    public int updateStaff(BmsStaff bmsStaff) {
        if (bmsStaff.getId()==null)
            throw new PtException(ExceptionEnum.STAFF_ID_NOT_NULL);

        if (!staffDao.existsWithPrimaryKey(bmsStaff.getId()))
            throw new PtException(ExceptionEnum.STAFF_NOT_EXIST);
        bmsStaff.setCreateTime(null);
        return staffDao.updateByPrimaryKeySelective(bmsStaff);
    }

    public StaffVo queryStaffById(Integer id) {
        if (!staffDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.STAFF_NOT_EXIST);
        return staffDao.queryStaffById(id);
    }

    public PageResult<StaffVo> queryAllStaffByPage(int page, int rows, StaffParamVo paramVo) {
        PageHelper.startPage(page,rows);
        Example example = new Example(BmsStaff.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(paramVo.getName()))
            criteria.andLike("name","%"+ paramVo.getName()+"%");
        if(paramVo.getSex()!=null)
            criteria.andEqualTo("sex", paramVo.getSex());
        if (paramVo.getStaffJobId()!=null)
            criteria.andEqualTo("staffJobId", paramVo.getStaffJobId());
        if (paramVo.getStartAge()!=null)
            criteria.andGreaterThanOrEqualTo("age",paramVo.getStartAge());
        if (paramVo.getEndAge()!=null)
            criteria.andLessThanOrEqualTo("age",paramVo.getEndAge());
        if (StringUtils.isNotEmpty(paramVo.getAddress()))
            criteria.andLike("address",paramVo.getAddress());
        if (paramVo.getStartTime()!=null)
            criteria.andGreaterThanOrEqualTo("createTime", paramVo.getStartTime());
        if (paramVo.getEndTime()!=null)
            criteria.andLessThanOrEqualTo("createTime", paramVo.getEndTime());
        example.selectProperties("id");
        Page<BmsStaff> bmsStaffs = (Page<BmsStaff>) staffDao.selectByExample(example);

        List<Integer> collect = bmsStaffs.stream().map(BmsStaff::getId).collect(Collectors.toList());
        List<StaffVo> staffVos = staffDao.queryStaffByIds(StringUtils.strip(collect.toString(), "[]"));

        PageResult<StaffVo> result = PageResult.restPage(staffVos);
        result.setTotalPage(bmsStaffs.getPages());
        result.setTotal(bmsStaffs.getTotal());
        result.setPageNum(page);
        result.setPageSize(rows);
        return result;


    }
}
