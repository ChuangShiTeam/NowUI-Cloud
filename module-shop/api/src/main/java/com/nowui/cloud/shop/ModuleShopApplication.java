package com.nowui.cloud.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhongYongQiang
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ModuleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleShopApplication.class, args);
    }

}
