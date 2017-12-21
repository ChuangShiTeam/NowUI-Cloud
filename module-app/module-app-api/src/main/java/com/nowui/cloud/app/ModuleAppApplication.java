package com.nowui.cloud.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.nowui.cloud.shop.product.rpc.ProductRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
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
    
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

}
