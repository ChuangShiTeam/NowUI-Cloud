package com.nowui.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ZhongYongQiang
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerEurekaApplication.class, args);
    }

}