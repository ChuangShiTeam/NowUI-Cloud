package com.nowui.cloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ZhongYongQiang
 */
@SpringBootApplication
@EnableEurekaClient
public class ModuleAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAppApplication.class, args);
    }

}
