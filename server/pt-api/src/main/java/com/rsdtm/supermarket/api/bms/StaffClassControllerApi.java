package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.mbg.model.BmsStaffClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "员工部门管理",description = "员工部门管理,一套增删改查")
public interface StaffClassControllerApi {

    @ApiOperation("添加员工部门")
    ResponseEntity<Void> addStaffClass(BmsStaffClass bmsStaffClass);

    @ApiOperation("根据ID员工部门")
    @ApiImplicitParam(name = "id",value = "员工部门ID",required = true)
    ResponseEntity<Void> deleteStaffClassById(Integer id);

    @ApiOperation("更新员工部门信息")
    ResponseEntity<Void> updateStaffClass(BmsStaffClass bmsStaffClass);

    @ApiOperation("根据ID查询员工部门")
    @ApiImplicitParam(name = "id",value = "员工部门ID",required = true)
    ResponseEntity<BmsStaffClass> queryStaffClassById(Integer id);

    @ApiOperation("查询所有员工部门")
    ResponseEntity<List<BmsStaffClass>> queryAllStaffClass();

}
