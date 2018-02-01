package com.nowui.cloud.zuul;

import com.nowui.cloud.elasticsearch.ElasticSearchConfig;
import com.nowui.cloud.event.config.EventConfig;
import com.nowui.cloud.event.mapper.EventMapper;
import com.nowui.cloud.filter.HttpServletRequestFilter;
import com.nowui.cloud.rabbit.RabbitConfig;
import com.nowui.cloud.rabbit.RabbitSender;
import com.nowui.cloud.redis.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Application
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, ElasticsearchAutoConfiguration.class, RabbitAutoConfiguration.class, RedisAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackages = {"com.nowui.cloud.zuul"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ElasticSearchConfig.class, RedisConfig.class, EventConfig.class, EventMapper.class, RabbitConfig.class, RabbitSender.class, HttpServletRequestFilter.class})})
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
