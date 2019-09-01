package com.rsdtm.supermarket.bms.controller;

import com.netflix.discovery.converters.Auto;
import com.rsdtm.supermarket.api.bms.VipProductControllerApi;
import com.rsdtm.supermarket.bms.service.VipProductService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsVipProduct;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vipProduct")
public class VipProductController implements VipProductControllerApi {
    @Autowired
    VipProductService vipProductService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addVipProduct(
            @RequestBody BmsVipProduct bmsVipProduct) {
        int result = vipProductService.addVipProduct(bmsVipProduct);
        if (result!=1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteVipProductById(
            @RequestParam(required = true) Integer id) {
        int result = vipProductService.deleteVipProductById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateVipProduct(
             BmsVipProduct bmsVipProduct) {
        int result = vipProductService.updateVipProduct(bmsVipProduct);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<VipProductVo> getVipProductVoById(
           @RequestParam(required = true) Integer id) {
        VipProductVo result = vipProductService.getVipProductVoById(id);
        if (result == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<VipProductVo>> queryVipProductVoByPage(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int rows,
            BmsVipProduct bmsVipProduct) {
        PageResult<VipProductVo> result = vipProductService.queryVipProductVoByPage(page,rows,bmsVipProduct);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("product")
    public ResponseEntity<List<VipProductVo>> getVipProductVoByProductId(
            @RequestParam(required = true) Integer id) {
        List<VipProductVo> result = vipProductService.getVipProductVoByProductId(id);
        if (result.size() == 0)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("productVip")
    public ResponseEntity<VipProductVo> getVipProductVoByProductIdAndUserId(Integer productId, Integer userId) {
        VipProductVo vipProductVo = vipProductService.getVipProductVoByProductIdAndUserId(productId,userId);
        if (vipProductVo == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(vipProductVo);
    }
}
