package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.VipControllerApi;
import com.rsdtm.supermarket.bms.service.VipService;
import com.rsdtm.supermarket.mbg.model.BmsVip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vip")
public class VipController implements VipControllerApi {
    @Autowired
    VipService vipService;

    @Override
    @PostMapping()
    public ResponseEntity<Void> addVip(
            @RequestBody BmsVip bmsVip) {
        int result = vipService.addVip(bmsVip);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteVipById(
            @RequestParam(required = true) Integer id) {
        int result = vipService.deleteVipById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateVip(
             BmsVip bmsVip) {
        int result = vipService.updateVip(bmsVip);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsVip> queryVipById(
            @RequestParam(required = true) Integer id) {
        BmsVip bmsVip = vipService.queryVipById(id);
        if (bmsVip == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();

    }

    @Override
    @GetMapping("all")
    public ResponseEntity<List<BmsVip>> queryAllVip() {
        List<BmsVip> result = vipService.queryAllVip();
        if (result.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
}
