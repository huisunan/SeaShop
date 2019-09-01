package com.rsdtm.supermarket.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PtRegistry {
    public static void main(String[] args) {
        SpringApplication.run(PtRegistry.class,args);

    }
}
