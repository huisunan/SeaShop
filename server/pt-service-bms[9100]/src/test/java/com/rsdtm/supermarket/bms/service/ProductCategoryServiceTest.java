package com.rsdtm.supermarket.bms.service;


import com.rsdtm.supermarket.mbg.model.BmsProductCategory;
import com.rsdtm.supermarket.mbg.vo.ProductCategoryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {

    @Autowired
    ProductCategoryService service;

    @Test
    public void getProductCategory() {
        BmsProductCategory productCategory = service.getProductCategory(1);
        System.out.println(productCategory);
    }
    @Test
    public void testTree(){
        ProductCategoryVo productCategoryTree = service.getProductCategoryTree(0);
        System.out.println(productCategoryTree);
    }
}