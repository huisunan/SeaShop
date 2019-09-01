package com.rsdtm.supermarket.gateway.client;


import com.rsdtm.supermarket.mbg.model.UmsUser;
import com.rsdtm.supermarket.mbg.vo.UmsMenuVo;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ums-service")
public interface UmsClient {
    @GetMapping("user")
    ResponseEntity<UmsUserVo> getUserById(@RequestParam("id") Integer id);

    @GetMapping("user/userAndPass")
    ResponseEntity<UmsUser> getUserByNameAndPassword(
            @RequestParam(required = true,value = "username") String username,
            @RequestParam(required = true,value = "password") String password);

    @GetMapping("user/name")
    ResponseEntity<UmsUserVo> getUserByName(@RequestParam(value = "name",required = true) String name);

    @GetMapping("menu/all")
    ResponseEntity<List<UmsMenuVo>> getAll();

}
