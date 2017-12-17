package com.nowui.cloud.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.nowui.cloud"})
public class ModuleCMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleCMSApplication.class, args);
    }
}
