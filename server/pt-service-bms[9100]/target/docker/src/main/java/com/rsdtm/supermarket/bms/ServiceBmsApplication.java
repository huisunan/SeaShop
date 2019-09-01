package com.rsdtm.supermarket.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {
        "com.rsdtm.supermarket.bms",
        "com.rsdtm.supermarket.api",
        "com.rsdtm.supermarket.common"
})
@MapperScan("com.rsdtm.supermarket.bms.dao")
public class ServiceBmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBmsApplication.class,args);
    }
}
