package com.nowui.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.nowui.cloud.elasticsearch.ElasticSearchConfig;
import com.nowui.cloud.filter.HttpServletRequestFilter;
import com.nowui.cloud.rabbit.RabbitConfig;
import com.nowui.cloud.redis.RedisConfig;

/**
 * @author ZhongYongQiang
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, ElasticsearchAutoConfiguration.class, RabbitAutoConfiguration.class, RedisAutoConfiguration.class})
@ComponentScan(basePackages = {"com.nowui.cloud"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ElasticSearchConfig.class, RedisConfig.class, RabbitConfig.class, HttpServletRequestFilter.class})})
@EnableEurekaClient
@EnableZuulProxy
public class ServerZuulApplication {

    public static void main(String[] args) {
///    	try {
//    		SpringApplication.run(ServerZuulApplication.class, args);
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}

        SpringApplication.run(ServerZuulApplication.class, args);
    }
    
}
