package com.nowui.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ZhongYongQiang
 */
@SpringBootApplication
@EnableConfigServer
public class ServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerConfigApplication.class, args);
    }

}
