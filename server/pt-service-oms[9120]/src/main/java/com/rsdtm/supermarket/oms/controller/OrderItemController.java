package com.rsdtm.supermarket.oms.controller;

import com.rsdtm.supermarket.api.oms.OrderItemControllerApi;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsOrderItem;
import com.rsdtm.supermarket.mbg.query.OmsOrderItemParamVo;
import com.rsdtm.supermarket.oms.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orderItem")
public class OrderItemController implements OrderItemControllerApi {
    @Autowired
    OrderItemService orderItemService;


    @Override
    @PostMapping
    public ResponseEntity<Void> addOrderItem(
            @RequestBody OmsOrderItemParamVo omsOrderItem) {
        int result = orderItemService.addOrderItem(omsOrderItem);
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOrderItemById(
            @RequestParam(required = true) Integer id) {
        return null;
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateOrderItem(
            OmsOrderItem omsOrderItem) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<OmsOrderItem> getOrderItemById(
           @RequestParam(required = true) Integer id) {
        return null;
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<OmsOrderItem>> getOrderItemByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            OmsOrderItem omsOrderItem) {
        return null;
    }
}
