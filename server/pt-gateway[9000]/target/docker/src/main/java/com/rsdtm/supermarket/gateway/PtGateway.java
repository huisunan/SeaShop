package com.rsdtm.supermarket.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Classname PtGateway
 * @Description TODO
 * @Date 2019/6/21 16:27
 * @Created by huisunan
 */
@SpringCloudApplication
@EnableZuulProxy
public class PtGateway {
    public static void main(String[] args) {
        SpringApplication.run(PtGateway.class,args);
    }
}
