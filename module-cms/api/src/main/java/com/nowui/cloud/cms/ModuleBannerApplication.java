package com.nowui.cloud.cms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ModuleBannerApplication {
    public static void main(String[] args){
        SpringApplication.run(ModuleBannerApplication.class,args);
    }
}
