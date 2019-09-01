package com.rsdtm.supermarket.bms.controller;

import com.rsdtm.supermarket.api.bms.UserControllerApi;
import com.rsdtm.supermarket.bms.service.UserService;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsUser;
import com.rsdtm.supermarket.mbg.query.BmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;



    @Override
    @PostMapping
    public ResponseEntity<Void> addUser(
            @RequestBody BmsUser bmsUser) {
        int result = userService.addUser(bmsUser);
        if (result!=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(
            @RequestParam(required = true) Integer id) {

        int result =  userService.deleteUserById(id);
        if (result!=1)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> updateUser(
             BmsUser bmsUser) {
        int result = userService.updateUser(bmsUser);
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<BmsUserVo> getUserById(
            @RequestParam(required = true) Integer id) {
        BmsUserVo result = userService.getUserById(id);
        if (result == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("page")
    public ResponseEntity<PageResult<BmsUserVo>> getUserByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int rows,
             BmsUserParamVo paramVo) {
        PageResult<BmsUserVo> result = userService.getUserByPage(page,rows,paramVo);
        return ResponseEntity.ok(result);
    }
}
