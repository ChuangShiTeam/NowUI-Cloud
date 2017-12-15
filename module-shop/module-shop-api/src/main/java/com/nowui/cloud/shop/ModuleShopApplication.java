package com.nowui.cloud.shop;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZhongYongQiang
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.nowui.cloud"}, excludeFilters = @ComponentScan.Filter(BaseMapper.class))
public class ModuleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleShopApplication.class, args);
    }

}
