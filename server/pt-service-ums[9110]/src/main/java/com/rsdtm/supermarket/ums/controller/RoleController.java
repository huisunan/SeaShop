package com.rsdtm.supermarket.ums.controller;

import com.rsdtm.supermarket.api.ums.RoleControllerApi;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsRoleVo;
import com.rsdtm.supermarket.ums.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController implements RoleControllerApi {

    @Autowired
    RoleService roleService;

    @Override
    @PostMapping
    public ResponseEntity<Void> addRole(
            @RequestBody  UmsRole umsRole) {
        int result = roleService.addRole(umsRole);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteRoleById(
           @RequestParam(required = true) Integer id) {
        int result = roleService.deleteRoleById(id);

        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateRole(
            UmsRole umsRole) {
        int result = roleService.updateRole(umsRole);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<UmsRoleVo> getRoleByID(
           @RequestParam(required = true) Integer id) {
        UmsRoleVo result = roleService.getRoleByID(id);
        return null;
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<UmsRoleVo>> getRoleByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows) {
        PageResult<UmsRoleVo> pageResult = roleService.getRoleByPage(page,rows);
        return ResponseEntity.ok(pageResult);
    }

    @Override
    @PostMapping("menu")
    public ResponseEntity<Void> addRoleMenu(
            @RequestParam(required = true) Integer roleId,
            @RequestParam(required = true) Integer menuId) {
        int result = roleService.addRoleMenu(roleId,menuId);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("menu")
    public ResponseEntity<Void> deleteRoleMenu(
            @RequestParam(required = true) Integer roleId,
            @RequestParam(required = true) Integer menuId) {
        int result = roleService.deleteRoleMenu(roleId,menuId);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("all")
    public ResponseEntity<List<UmsRole>> getAll() {
        List<UmsRole> result = roleService.getAll();
        return ResponseEntity.ok(result);
    }
}
