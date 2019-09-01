package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import com.rsdtm.supermarket.mbg.vo.BmsStaffJobVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "员工职务管理",description = "员工职务管理,一套增删改查")
public interface StaffJobControllerApi {


    @ApiOperation("添加员工职务")
    ResponseEntity<Void> addStaffJob(BmsStaffJob bmsStaffJob);

    @ApiOperation("根据ID员工职务")
    @ApiImplicitParam(name = "id",value = "员工职务ID",required = true)
    ResponseEntity<Void> deleteStaffJobById(Integer id);

    @ApiOperation("更新员工职务信息")
    ResponseEntity<Void> updateStaffJob(BmsStaffJob bmsStaffJob);

    @ApiOperation("根据ID查询员工职务")
    @ApiImplicitParam(name = "id",value = "员工职务ID",required = true)
    ResponseEntity<BmsStaffJobVo> queryStaffJobById(Integer id);

    @ApiOperation("查询所有员工职务")
    ResponseEntity<List<BmsStaffJobVo>> queryAllStaffJob();

}
