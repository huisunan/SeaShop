package com.rsdtm.supermarket.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringCloudApplication
@EnableZuulProxy
@EnableFeignClients
public class PtGateway {
    public static void main(String[] args) {
        SpringApplication.run(PtGateway.class,args);
    }
}
