package com.nowui.cloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ModuleProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleProductApplication.class, args);
    }

}
