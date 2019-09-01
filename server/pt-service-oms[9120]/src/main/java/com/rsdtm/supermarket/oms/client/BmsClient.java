package com.rsdtm.supermarket.oms.client;

import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("bms-service")
public interface BmsClient {
    @GetMapping("staff")
    ResponseEntity<StaffVo> queryStaffById(@RequestParam(required = true, name = "id") Integer id);

    @GetMapping("user")
    ResponseEntity<BmsUserVo> getUserById(@RequestParam(required = true, name = "id") Integer id);

    @GetMapping("product")
    ResponseEntity<ProductVo> getProductById(
            @RequestParam(required = true, name = "id") Integer id);

    @GetMapping("vipProduct/productVip")
     ResponseEntity<VipProductVo> getVipProductVoByProductIdAndUserId(
            @RequestParam(required = true, name = "productId")Integer productId,
            @RequestParam(required = true, name = "vipId")Integer vipId);
}
