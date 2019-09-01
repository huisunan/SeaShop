package com.rsdtm.supermarket.api.bms;


import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsStaff;
import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import com.rsdtm.supermarket.mbg.query.StaffParamVo;
import com.rsdtm.supermarket.mbg.vo.BmsStaffJobVo;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "员工管理",description = "员工管理,一套增删改查")
public interface StaffControllerApi {

    @ApiOperation("添加员工")
    ResponseEntity<Void> addStaff(BmsStaff bmsStaff);

    @ApiOperation("根据ID员工")
    @ApiImplicitParam(name = "id",value = "员工ID",required = true)
    ResponseEntity<Void> deleteStaffById(Integer id);

    @ApiOperation("更新员工信息")
    ResponseEntity<Void> updateStaff(BmsStaff bmsStaff);

    @ApiOperation("根据ID查询员工")
    @ApiImplicitParam(name = "id",value = "员工ID",required = true)
    ResponseEntity<StaffVo> queryStaffById(Integer id);

    @ApiOperation("查询员工,分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<StaffVo>> queryAllStaffByPage(int page, int rows, StaffParamVo paramVo);
}
