package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import com.rsdtm.supermarket.mbg.vo.BmsStaffJobVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StaffJobDao extends Mapper<BmsStaffJob> {

    @Select("select j.*,c.name staff_class from bms_staff_job j,bms_staff_class c where j.id = #{id} and j.staff_class_id = c.id")
    BmsStaffJobVo queryStaffJobById(Integer id);

    @Select("select j.*,c.name staff_class from bms_staff_job j,bms_staff_class c where j.staff_class_id = c.id")
    List<BmsStaffJobVo> queryAllStaffJob();
}
