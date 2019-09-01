package com.rsdtm.supermarket.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {
        "com.rsdtm.supermarket.ums",
        "com.rsdtm.supermarket.api",
        "com.rsdtm.supermarket.common"
})
@MapperScan("com.rsdtm.supermarket.ums.dao")
public class ServiceUmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUmsApplication.class,args);
    }
}
