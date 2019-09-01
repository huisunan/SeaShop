package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.ProductCategoryControllerApi;
import com.rsdtm.supermarket.bms.service.ProductCategoryService;
import com.rsdtm.supermarket.mbg.model.BmsProductCategory;
import com.rsdtm.supermarket.mbg.vo.ProductCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class ProductCategoryController implements ProductCategoryControllerApi {

    @Autowired
    ProductCategoryService productCategoryService;


    @Override
    @GetMapping
    public ResponseEntity<BmsProductCategory> getProductCategory(
            @RequestParam Integer id) {
        BmsProductCategory productCategory = productCategoryService.getProductCategory(id);
        if (productCategory==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(productCategory);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> addProductCategory(
            @RequestBody BmsProductCategory productCategory) {
        int result = productCategoryService.addProductCategory(productCategory);
        if (result != 1)
            ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteProductCategory(
            @RequestParam Integer id) {
        int result = productCategoryService.deleteProductCategory(id);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateProductCategory(
            @RequestBody BmsProductCategory productCategory) {
        int result = productCategoryService.updateProductCategory(productCategory);
        if (result !=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("tree")
    public ResponseEntity<ProductCategoryVo> getProductCategoryTree(
           @RequestParam Integer id) {
        ProductCategoryVo categoryVo = productCategoryService.getProductCategoryTree(id);
        return ResponseEntity.ok(categoryVo);
    }

    @Override
    @PutMapping("move")
    public ResponseEntity<Void> moveProductCategory(
            @RequestParam Integer categoryId,
            @RequestParam Integer parentID) {
        int result = productCategoryService.moveProductCategory(categoryId, parentID);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
