package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.SupplierControllerApi;
import com.rsdtm.supermarket.bms.service.SupplierService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import com.rsdtm.supermarket.mbg.query.SupplierParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController implements SupplierControllerApi {

    @Autowired
    SupplierService supplierService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addSupplier(
            @RequestBody BmsSupplier bmsSupplier) {
        int result = supplierService.addSupplier(bmsSupplier);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteSupplier(
            @RequestParam(required = true) Integer id) {
        int result = supplierService.deleteSupplier(id);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateSupplier(
             BmsSupplier bmsSupplier) {
        int result = supplierService.updateSupplier(bmsSupplier);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsSupplier> getSupplierById(
            @RequestParam(required = true) Integer id) {
        BmsSupplier bmsSupplier = supplierService.getSupplierById(id);
        if (bmsSupplier == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bmsSupplier);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<BmsSupplier>> querySupplierByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            SupplierParamVo paramVo) {
        PageResult<BmsSupplier> result = supplierService.querySupplierByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("product")
    public ResponseEntity<List<BmsSupplier>> querySupplierByProductId(
            @RequestParam(required = true) Integer id) {
        List<BmsSupplier> result = supplierService.querySupplierByProductId(id);
        if (result.size() == 0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
}
