package com.rsdtm.supermarket.ums.controller;

import com.rsdtm.supermarket.api.ums.MenuControllerApi;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsMenu;
import com.rsdtm.supermarket.mbg.query.UmsMenuParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsMenuVo;
import com.rsdtm.supermarket.ums.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController implements MenuControllerApi {

    @Autowired
    MenuService menuService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addMenu(
           @RequestBody UmsMenuParamVo umsMenu) {
        int result = menuService.addMenu(umsMenu);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteMenu(
            @RequestParam(required = true) Integer id) {
        int result = menuService.deleteMenu(id);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateMenu(
             UmsMenuParamVo umsMenu) {
        int result = menuService.updateMenu(umsMenu);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<UmsMenuVo> getMenuById(Integer id) {
        UmsMenuVo result = menuService.getMenuById(id);
        if (result == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<UmsMenuVo>> getMenuByPage(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int rows) {

        PageResult<UmsMenuVo> result = menuService.getMenuByPage(page,rows);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("all")
    public ResponseEntity<List<UmsMenuVo>> getAll() {
        List<UmsMenuVo> menus = menuService.getAll();
        return ResponseEntity.ok(menus);
    }
}
