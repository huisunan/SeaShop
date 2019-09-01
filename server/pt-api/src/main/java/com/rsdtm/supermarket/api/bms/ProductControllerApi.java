package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProduct;
import com.rsdtm.supermarket.mbg.query.ProductParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "商品操作",description = "商品操作,一套增删改查")
public interface ProductControllerApi {

    @ApiOperation("商品添加")
    @ApiImplicitParam(name = "productCategoryId",value = "商品所属分类ID",required = true)
    ResponseEntity<Void> addProduct(BmsProduct bmsProduct);

    @ApiOperation("商品删除")
    @ApiImplicitParam(name = "id",value = "需要删除商品的id",required = true)
    ResponseEntity<Void> deleteProduct(Integer id);

    @ApiOperation("根据ID查询商品")
    @ApiImplicitParam(name = "id",value = "需要查询商品的id",required = true)
    ResponseEntity<ProductVo> getProductById(Integer id);


    @ApiOperation("修改商品")
    ResponseEntity<Void> updateProduct(BmsProduct bmsProduct);

    @ApiOperation("商品分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<ProductVo>> queryProductByPage(int page, int rows, ProductParamVo paramVo);

    @ApiOperation("根据供应商ID查找商品")
    @ApiImplicitParam(name = "id",value = "供应商ID",required = true)
    ResponseEntity<List<ProductVo>> queryProductBySupplierId(Integer id);
}
