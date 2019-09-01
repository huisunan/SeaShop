package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsUser;
import com.rsdtm.supermarket.mbg.query.BmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "客户管理",description = "客户的增删改查")
public interface UserControllerApi {
    @ApiOperation("添加客户")
    ResponseEntity<Void> addUser(BmsUser bmsUser);

    @ApiOperation("删除客户根据ID")
    @ApiImplicitParam(name = "id",value = "要删除客户的ID",required = true)
    ResponseEntity<Void> deleteUserById(Integer id);

    @ApiOperation("修改用户")
    ResponseEntity<Void> updateUser(BmsUser bmsUser);



    @ApiOperation("根据ID查询客户")
    @ApiImplicitParam(name = "id",value = "要查询客户的ID",required = true)
    ResponseEntity<BmsUserVo> getUserById(Integer id);

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<BmsUserVo>> getUserByPage(int page, int rows, BmsUserParamVo paramVo);
}
