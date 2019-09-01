package com.rsdtm.supermarket.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {
        "com.rsdtm.supermarket.oms",
        "com.rsdtm.supermarket.api",
        "com.rsdtm.supermarket.common"
})
@MapperScan("com.rsdtm.supermarket.oms.dao")
@EnableFeignClients
public class ServiceOmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOmsApplication.class,args);
    }
}
