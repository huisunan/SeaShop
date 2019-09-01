package com.rsdtm.supermarket.ums.controller;

import com.rsdtm.supermarket.api.ums.UmsUserControllerApi;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsUser;
import com.rsdtm.supermarket.mbg.query.UmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import com.rsdtm.supermarket.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController implements UmsUserControllerApi {
    @Autowired
    UserService userService;


    @Override
    @PostMapping
    public ResponseEntity<Void> addUser(
            @RequestBody UmsUserParamVo umsUser ){
        int result = userService.addUser(umsUser);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(
            @RequestParam(required = true) Integer id) {
        int result = userService.deleteUserById(id);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateUser(
             UmsUser umsUser) {
        int result = userService.updateUser(umsUser);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<UmsUserVo> getUserById(
            @RequestParam(required = true) Integer id) {
        UmsUserVo umsUserVo = userService.getUserById(id);
        if (umsUserVo ==  null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(umsUserVo);
    }

    @Override
    @GetMapping("name")
    public ResponseEntity<UmsUserVo> getUserByName(
            @RequestParam(required = true) String name) {
        UmsUserVo result = userService.getUserByName(name);
        if (result ==  null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<UmsUserVo>> getUserByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
            UmsUser umsUser) {
        PageResult<UmsUserVo> result = userService.getUserByPage(page,rows,umsUser);
        return ResponseEntity.ok(result);
    }

    @Override
    @PutMapping("password")
    public ResponseEntity<Void> updateUserPassWord(
            @RequestParam(required = true) Integer id,
            @RequestParam(required = true) String oldPassword,
            @RequestParam(required = true) String newPassword) {
        int result = userService.updateUserPassWord(id,oldPassword,newPassword);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("role")
    public ResponseEntity<Void> addUserRole(
            @RequestParam(required = true) Integer userId,
            @RequestParam(required = true) Integer roleId) {
        int result = userService.addUserRole(userId,roleId);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("role")
    public ResponseEntity<Void> deleteUserRole(
            @RequestParam(required = true) Integer userId,
            @RequestParam(required = true) Integer roleId) {
        int result = userService.deleteUserRole(userId,roleId);
        if (result != 1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("userAndPass")
    public ResponseEntity<UmsUser> getByUsernameAndPassword(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {
        UmsUser result = userService.getByUsernameAndPassword(username,password);
        return ResponseEntity.ok(result);
    }
}
