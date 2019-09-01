package com.rsdtm.supermarket.gateway.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RsaUtilsTest {

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey("F:\\实验\\javaee\\pt-parent\\pt-gateway[9000]\\src\\main\\resources\\pub.key"
        ,"F:\\实验\\javaee\\pt-parent\\pt-gateway[9000]\\src\\main\\resources\\pri.key",
                "98115");
    }

    @Test
    public void getPublicKey() throws Exception {
        PublicKey publicKey = RsaUtils.getPublicKey("pub.key");
        System.out.println(publicKey);
    }

    @Test
    public void getPrivateKey() throws Exception {
        PrivateKey privateKey = RsaUtils.getPrivateKey("pri.key");
        System.out.println(privateKey);
    }
}