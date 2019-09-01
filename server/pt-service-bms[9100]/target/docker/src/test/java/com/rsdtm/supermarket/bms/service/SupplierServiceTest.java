package com.rsdtm.supermarket.bms.service;

import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceTest {
    @Autowired
    SupplierService supplierService;

    @Test
    public void addSupplier() {
        BmsSupplier bmsSupplier = new BmsSupplier();
        bmsSupplier.setId(111);
        bmsSupplier.setCreateTime(new Date());
        bmsSupplier.setAddress("江苏省盐城市阜宁县城管大队");
        bmsSupplier.setLeader("惠苏南");
        bmsSupplier.setPhone("15751613461");

        int i = supplierService.addSupplier(bmsSupplier);
        System.out.println(i);
    }

    @Test
    public void deleteSupplier() {
    }

    @Test
    public void updateSupplier() {
    }

    @Test
    public void getSupplierById() {
    }

    @Test
    public void querySupplierByPage() {
    }
}