package com.rsdtm.supermarket.api.oms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsOrderItem;
import com.rsdtm.supermarket.mbg.query.OmsOrderItemParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "关于订单项的",description = "订单项的增删改查")
public interface OrderItemControllerApi {

    @ApiOperation("添加订单项")
    ResponseEntity<Void> addOrderItem(OmsOrderItemParamVo omsOrderItem);

    @ApiOperation("删除订单项，根据ID")
    @ApiImplicitParam(name = "id",value = "要删除订单项的ID",required = true)
    ResponseEntity<Void> deleteOrderItemById(Integer id);

    @ApiOperation("修改订单项")
    ResponseEntity<Void> updateOrderItem(OmsOrderItem omsOrderItem);

    @ApiOperation("根据ID查询订单项")
    ResponseEntity<OmsOrderItem> getOrderItemById(Integer id);

    @ApiOperation("分页查询")
    ResponseEntity<PageResult<OmsOrderItem>> getOrderItemByPage(int page, int rows, OmsOrderItem omsOrderItem);
}
