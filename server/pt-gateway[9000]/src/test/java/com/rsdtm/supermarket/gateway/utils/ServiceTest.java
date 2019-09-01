package com.rsdtm.supermarket.gateway.utils;

import com.rsdtm.supermarket.gateway.client.UmsClient;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    UmsClient userClient;
    @Test
    public void UserTest(){
        ResponseEntity<UmsUserVo> userById = userClient.getUserById(2);
        UmsUserVo body = userById.getBody();
        System.out.println(body);
    }
}
