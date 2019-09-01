package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import com.rsdtm.supermarket.mbg.query.SupplierParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "供应商管理",description = "供应商管理,一套增删改查")
public interface SupplierControllerApi {

    @ApiOperation("添加供应商")
    ResponseEntity<Void> addSupplier(BmsSupplier bmsSupplier);

    @ApiOperation("删除供应商")
    @ApiImplicitParam(name = "id",value = "供应商ID",required = true)
    ResponseEntity<Void> deleteSupplier(Integer id);

    @ApiOperation("修改供应商信息")
    ResponseEntity<Void> updateSupplier(BmsSupplier bmsSupplier);

    @ApiOperation("根据ID查询供应商")
    @ApiImplicitParam(name = "id",value = "供应商ID",required = true)
    ResponseEntity<BmsSupplier> getSupplierById(Integer id);

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<BmsSupplier>> querySupplierByPage(int page, int rows, SupplierParamVo paramVo);

    @ApiOperation("根据商品ID查询供应商")
    @ApiImplicitParam(name = "id",value = "商品ID",required = true)
    ResponseEntity<List<BmsSupplier>> querySupplierByProductId(Integer id);
}
