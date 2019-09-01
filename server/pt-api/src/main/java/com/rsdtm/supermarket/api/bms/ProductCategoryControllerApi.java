package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.mbg.model.BmsProductCategory;
import com.rsdtm.supermarket.mbg.vo.ProductCategoryVo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(value = "商品分类",description = "商品分类的增删改查")
public interface ProductCategoryControllerApi {

    @ApiOperation("根据id查询分类")
    @ApiImplicitParam(name = "id",value = "分类id",required = true)
    ResponseEntity<BmsProductCategory> getProductCategory(Integer id);

    @ApiOperation("添加分类")
    ResponseEntity<Void> addProductCategory(BmsProductCategory productCategory);

    @ApiOperation("删除分类，如果是父节点则会递归删除所有子结点")
    @ApiImplicitParam(name = "id",value = "分类id",required = true)
    ResponseEntity<Void> deleteProductCategory(Integer id);

    @ApiOperation("修改分类")
    ResponseEntity<Void> updateProductCategory(BmsProductCategory productCategory);

    @ApiOperation("获取商品分类树形结构")
    @ApiImplicitParam(name = "id",value = "父分类id",required = true,defaultValue = "0")
    ResponseEntity<ProductCategoryVo> getProductCategoryTree(Integer id);

    @ApiOperation("转移分类")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "categoryId",value = "分类ID",required = true),
                    @ApiImplicitParam(name = "parentID",value = "需要转移到的父类id",required = true)
    })
    ResponseEntity<Void> moveProductCategory(Integer categoryId,Integer parentID);


}
