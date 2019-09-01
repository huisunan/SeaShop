package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsStaff;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StaffDao extends Mapper<BmsStaff> {
    @Select("select s.*,j.name staff_job_name from bms_staff s,bms_staff_job j where s.id = #{id} and s.staff_job_id = j.id")
    StaffVo queryStaffById(Integer id);

    @Select("select s.*,j.name staff_job_name from bms_staff s,bms_staff_job j where s.id in (${ids}) and s.staff_job_id = j.id")
    List<StaffVo> queryStaffByIds(@Param("ids") String ids);
}
