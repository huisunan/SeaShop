package com.rsdtm.supermarket.api.ums;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "角色管理",description = "角色的增删改查")
public interface RoleControllerApi {
    @ApiOperation("添加角色")
    ResponseEntity<Void> addRole(UmsRole umsRole);

    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id",value = "要删除角色的ID",required = true)
    ResponseEntity<Void> deleteRoleById(Integer id);

    @ApiOperation("修改角色")
    ResponseEntity<Void> updateRole(UmsRole umsRole);

    @ApiOperation("查询角色")
    ResponseEntity<UmsRoleVo> getRoleByID(Integer id);

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<UmsRoleVo>> getRoleByPage(int page, int rows);

    @ApiOperation("添加角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true),
            @ApiImplicitParam(name = "menuId",value = "资源ID",required = true)
    })
    ResponseEntity<Void> addRoleMenu(Integer roleId,Integer menuId);

    @ApiOperation("删除角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true),
            @ApiImplicitParam(name = "menuId",value = "资源ID",required = true)
    })
    ResponseEntity<Void> deleteRoleMenu(Integer roleId,Integer menuId);

    @ApiOperation("获取所有角色")
    ResponseEntity<List<UmsRole>> getAll();
}
