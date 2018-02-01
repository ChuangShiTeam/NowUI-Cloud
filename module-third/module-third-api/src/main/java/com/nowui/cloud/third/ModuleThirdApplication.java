package com.nowui.cloud.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.nowui.cloud"})
@ComponentScan(basePackages = {"com.nowui.cloud"})
@RestController
public class ModuleThirdApplication {

    public static void main(String[] args) {
//        try {
//            ApplicationContext app = SpringApplication.run(ModuleBaseApplication.class, args);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        SpringApplication.run(ModuleThirdApplication.class, args);
    }

}
