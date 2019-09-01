package com.rsdtm.supermarket.api.ums;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsUser;

import com.rsdtm.supermarket.mbg.query.UmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "用户管理",description = "用户的增删改查")
public interface UmsUserControllerApi {
    @ApiOperation("添加用户")
    ResponseEntity<Void> addUser(UmsUserParamVo umsUser);

    @ApiOperation("删除用户根据ID")
    @ApiImplicitParam(name = "id",value = "要删除用户的ID",required = true)
    ResponseEntity<Void> deleteUserById(Integer id);

    @ApiOperation("修改用户,不包含密码")
    ResponseEntity<Void> updateUser(UmsUser umsUser);

    @ApiOperation("根据ID查询用户")
    @ApiImplicitParam(name = "id",value = "要查询用户的ID",required = true)
    ResponseEntity<UmsUserVo> getUserById(Integer id);

    @ApiOperation("根据用户名查询用户")
    @ApiImplicitParam(name = "name",value = "要查询用户的名",required = true)
    ResponseEntity<UmsUserVo> getUserByName(String name);

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<UmsUserVo>> getUserByPage(int page, int rows, UmsUser umsUser);


    @ApiOperation("修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true),
            @ApiImplicitParam(name = "oldPassword",value = "旧密码",required = true),
            @ApiImplicitParam(name = "newPassword",value = "新密码",required = true)
    })
    ResponseEntity<Void> updateUserPassWord(Integer id,String oldPassword,String newPassword);

    @ApiOperation("给用户添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true),
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true),
    })
    ResponseEntity<Void> addUserRole(Integer userId,Integer roleId);

    @ApiOperation("删除用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true),
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true),
    })
    ResponseEntity<Void> deleteUserRole(Integer userId,Integer roleId);

    @ApiOperation("根据用户名密码查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "用户密码",required = true),
    })
    ResponseEntity<UmsUser> getByUsernameAndPassword(String username,String password);
}
