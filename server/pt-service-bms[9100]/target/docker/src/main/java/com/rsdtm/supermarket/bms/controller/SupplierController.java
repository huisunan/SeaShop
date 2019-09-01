package com.rsdtm.supermarket.bms.controller;

import com.netflix.discovery.converters.Auto;
import com.rsdtm.supermarket.api.bms.SupplierControllerApi;
import com.rsdtm.supermarket.bms.service.SupplierService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import com.rsdtm.supermarket.mbg.vo.SupplierParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("supplier")
public class SupplierController implements SupplierControllerApi {

    @Autowired
    SupplierService supplierService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addSupplier(@RequestBody BmsSupplier bmsSupplier) {
        int result = supplierService.addSupplier(bmsSupplier);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteSupplier(Integer id) {
        int result = supplierService.deleteSupplier(id);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateSupplier(BmsSupplier bmsSupplier) {
        int result = supplierService.updateSupplier(bmsSupplier);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsSupplier> getSupplierById(Integer id) {
        BmsSupplier bmsSupplier = supplierService.getSupplierById(id);
        if (bmsSupplier == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bmsSupplier);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<BmsSupplier>> querySupplierByPage(int page, int rows, SupplierParamVo paramVo) {
        PageResult<BmsSupplier> result = supplierService.querySupplierByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }
}
