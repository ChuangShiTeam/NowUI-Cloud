package com.nowui.cloud.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ModuleCMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleCMSApplication.class, args);
    }

}
