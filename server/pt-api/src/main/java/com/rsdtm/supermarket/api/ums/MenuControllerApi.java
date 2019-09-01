package com.rsdtm.supermarket.api.ums;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsMenu;
import com.rsdtm.supermarket.mbg.query.UmsMenuParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsMenuVo;
import com.rsdtm.supermarket.mbg.vo.UmsRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "资源菜单管理",description = "资源菜单的增删改查")
public interface MenuControllerApi {
    @ApiOperation("添加资源菜单")
    ResponseEntity<Void> addMenu(UmsMenuParamVo umsMenu);

    @ApiOperation("删除资源菜单")
    @ApiImplicitParam(name = "id",value = "要删除资源菜单的id",required = true)
    ResponseEntity<Void> deleteMenu(Integer id);

    @ApiOperation("修改资源菜单")
    ResponseEntity<Void> updateMenu(UmsMenuParamVo umsMenu);

    @ApiOperation("根据ID查询资源菜单")
    @ApiImplicitParam(name = "id",value = "要查询资源菜单的id",required = true)
    ResponseEntity<UmsMenuVo> getMenuById(Integer id);


    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<UmsMenuVo>> getMenuByPage(int page, int rows);

    @ApiOperation("获取所有")
    ResponseEntity<List<UmsMenuVo>> getAll();
}
