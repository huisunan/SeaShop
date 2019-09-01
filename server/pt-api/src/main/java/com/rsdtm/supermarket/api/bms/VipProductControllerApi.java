package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsVipProduct;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "会员商品价格管理",description = "会员商品价格管理,一套增删改查")
public interface VipProductControllerApi {
    @ApiOperation("添加会员商品价格")
    ResponseEntity<Void> addVipProduct(BmsVipProduct bmsVipProduct);

    @ApiOperation("根据ID删除商品会员价格")
    @ApiImplicitParam(name = "id",value = "要删除的ID",required = true)
    ResponseEntity<Void> deleteVipProductById(Integer id);


    @ApiOperation("修改会员商品价格")
    ResponseEntity<Void> updateVipProduct(BmsVipProduct bmsVipProduct);


    @ApiOperation("根据ID查询会员价格")
    @ApiImplicitParam(name = "id",value = "要删除的ID",required = true)
    ResponseEntity<VipProductVo> getVipProductVoById(Integer id);

    @ApiOperation("会员价格分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "起始页",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页行数",defaultValue = "10")
    })
    ResponseEntity<PageResult<VipProductVo>> queryVipProductVoByPage(int page,int rows,BmsVipProduct bmsVipProduct);

    @ApiOperation("根据商品ID查询会员价格")
    ResponseEntity<List<VipProductVo>> getVipProductVoByProductId(Integer id);

    @ApiOperation("根据商品ID和会员ID查询会员价格")
    ResponseEntity<VipProductVo> getVipProductVoByProductIdAndUserId(Integer productId,Integer userId);
}
