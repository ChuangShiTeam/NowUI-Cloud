package com.nowui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.nowui.cloud"})
@ComponentScan(basePackages = {"com.nowui.cloud"})
public class ModuleHospitalApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ModuleHospitalApplication.class, args);
    }

}
