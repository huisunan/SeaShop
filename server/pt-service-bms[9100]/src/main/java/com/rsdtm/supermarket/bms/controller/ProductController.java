package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.ProductControllerApi;
import com.rsdtm.supermarket.bms.service.ProductService;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsProduct;
import com.rsdtm.supermarket.mbg.query.ProductParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController implements ProductControllerApi {
    @Autowired
    ProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addProduct(
            @RequestBody BmsProduct bmsProduct) {
        int result = productService.addProduct(bmsProduct);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(
            @RequestParam(required = true) Integer id) {
        int result = productService.deleteProduct(id);
        if (result != 1)
            return  ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<ProductVo> getProductById(
            @RequestParam(required = true) Integer id) {
        ProductVo bmsProduct = productService.getProductById(id);
        if (bmsProduct == null)
            throw new PtException(ExceptionEnum.PRODUCT_NOT_EXIST);
        return ResponseEntity.ok(bmsProduct);
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateProduct(
             BmsProduct bmsProduct) {
        int result = productService.updateProduct(bmsProduct);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<ProductVo>> queryProductByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
             ProductParamVo paramVo) {
        PageResult<ProductVo> result = productService.queryProductByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("supplier")
    public ResponseEntity<List<ProductVo>> queryProductBySupplierId(
           @RequestParam(required = true) Integer id) {
        List<ProductVo> result = productService.queryProductBySupplierId(id);
        if (result.size() ==0)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
}
