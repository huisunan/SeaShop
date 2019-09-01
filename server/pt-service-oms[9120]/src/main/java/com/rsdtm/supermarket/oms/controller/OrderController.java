package com.rsdtm.supermarket.oms.controller;

import com.rsdtm.supermarket.api.oms.OrderControllerApi;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsOrder;
import com.rsdtm.supermarket.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController implements OrderControllerApi {

    @Autowired
    OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addOrder(
           @RequestBody OmsOrder omsOrder) {
        int result = orderService.addOrder(omsOrder);
        if (result!=1)
           return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOrderById(
            @RequestParam(required = true) Integer id) {
        int result = orderService.deleteOrderById(id);
        if (result!=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateOrder(
            OmsOrder omsOrder) {
        int result = orderService.updateOrder(omsOrder);
        if (result!=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<OmsOrder> getOrderById(
            @RequestParam(required = true) Integer id) {
        OmsOrder result = orderService.getOrderById(id);
        if (result==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<OmsOrder>> getOrderByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            OmsOrder omsOrder) {
        PageResult<OmsOrder> result = orderService.getOrderByPage(page,rows,omsOrder);
        return ResponseEntity.ok(result);
    }
}
