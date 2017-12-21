package com.nowui.cloud.app;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.shop.product.rpc.ProductRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhongYongQiang
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.nowui.cloud"})
@ComponentScan(basePackages = {"com.nowui.cloud"})
@RestController
public class ModuleAppApplication {

    @Autowired
    private ProductRpc productRpc;

    @RequestMapping("/test")
    public String test() {
        System.out.println(JSON.toJSONString(productRpc.find("c01e2a21271e433dac70c561d06cfe9c")));

        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ModuleAppApplication.class, args);
    }

}
