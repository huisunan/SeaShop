package com.rsdtm.supermarket.ums.controller;

import com.rsdtm.supermarket.mbg.model.UmsUser;
import com.rsdtm.supermarket.ums.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserService service;

    @Test
    public void getByUsernameAndPassword() {
        UmsUser huisunan = service.getByUsernameAndPassword("huisunan", "123");

        System.out.println(huisunan);
    }
}