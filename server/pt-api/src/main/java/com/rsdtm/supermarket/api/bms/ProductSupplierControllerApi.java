package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProductSupplier;
import com.rsdtm.supermarket.mbg.query.ProductSupplierParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductSupplierVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "商品供应商中间表",description = "商品和供应商关系处理")
public interface ProductSupplierControllerApi {

    @ApiOperation("添加商品供应商关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",value = "产品ID",required = true),
            @ApiImplicitParam(name = "supplierId",value = "供应商ID",required = true),
            @ApiImplicitParam(name = "operator",value = "操作人"),
    })
    ResponseEntity<Void> addProductSupplier(Integer productId,Integer supplierId,String operator);

    @ApiOperation("删除商品供应商关系")
    @ApiImplicitParam(name = "id",value = "商品供应商中间表ID",required = true)
    ResponseEntity<ProductSupplierVo> deleteProductSupplierById(Integer id);

    @ApiOperation("修改商品供应商关系")
    @ApiImplicitParam(name = "id",value = "商品供应商中间表ID",required = true)
    ResponseEntity<Void> updateProductSupplier(BmsProductSupplier bmsProductSupplier);

    @ApiOperation("根据ID查询商品供应商关系")
    @ApiImplicitParam(name = "id",value = "商品供应商中间表ID",required = true)
    ResponseEntity<ProductSupplierVo> getProductSupplierById(Integer id);

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<ProductSupplierVo>> getProductSupplierByPage(int page, int rows, ProductSupplierParamVo paramVo);
}
