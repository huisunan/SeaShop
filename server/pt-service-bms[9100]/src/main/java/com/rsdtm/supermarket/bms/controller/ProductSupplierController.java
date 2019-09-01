package com.rsdtm.supermarket.bms.controller;

import com.netflix.discovery.converters.Auto;
import com.rsdtm.supermarket.api.bms.ProductSupplierControllerApi;
import com.rsdtm.supermarket.bms.service.ProductSupplierService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProductSupplier;
import com.rsdtm.supermarket.mbg.query.ProductSupplierParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductSupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("productSupplier")
public class ProductSupplierController implements ProductSupplierControllerApi {
    @Autowired
    ProductSupplierService productSupplierService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addProductSupplier(
            @RequestParam(required = true) Integer productId,
            @RequestParam(required = true) Integer supplierId,
            @RequestParam(required = true) String operator) {
        int result = productSupplierService.addProductSupplier(productId, supplierId, operator);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ProductSupplierVo> deleteProductSupplierById(
            @RequestParam(required = true) Integer id) {
        int result = productSupplierService.deleteProductSupplierById(id);
        if (result != 1)
            ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateProductSupplier(
            BmsProductSupplier bmsProductSupplier) {
        int result = productSupplierService.updateProductSupplier(bmsProductSupplier);
        if (result != 1)
            ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<ProductSupplierVo> getProductSupplierById(
            @RequestParam(required = true) Integer id) {
        ProductSupplierVo productSupplierVo = productSupplierService.getProductSupplierById(id);
        if (productSupplierVo == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(productSupplierVo);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<ProductSupplierVo>> getProductSupplierByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            ProductSupplierParamVo paramVo) {
        PageResult<ProductSupplierVo> result = productSupplierService.getProductSupplierByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }
}
