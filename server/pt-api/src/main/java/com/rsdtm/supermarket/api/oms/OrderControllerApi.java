package com.rsdtm.supermarket.api.oms;


import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "关于订单的",description = "订单的增删改查")
public interface OrderControllerApi {
    @ApiOperation("添加订单")
    ResponseEntity<Void> addOrder(OmsOrder omsOrder);

    @ApiOperation("删除订单，根据ID")
    @ApiImplicitParam(name = "id",value = "要删除订单的ID",required = true)
    ResponseEntity<Void> deleteOrderById(Integer id);

    @ApiOperation("修改订单")
    ResponseEntity<Void> updateOrder(OmsOrder omsOrder);

    @ApiOperation("根据ID查询订单")
    ResponseEntity<OmsOrder> getOrderById(Integer id);

    @ApiOperation("分页查询")
    ResponseEntity<PageResult<OmsOrder>> getOrderByPage(int page,int rows,OmsOrder omsOrder);
}
