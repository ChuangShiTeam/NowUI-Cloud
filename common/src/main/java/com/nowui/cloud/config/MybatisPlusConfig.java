package com.nowui.cloud.config;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;

/**
 * MybatisPlus插件配置
 * @author marcus
 *
 */
@Configuration
@MapperScan("com.nowui.cloud.**.**.mapper")
public class MybatisPlusConfig {
    
    /**
     * mybatis-plus 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】 设置 dev test 环境开启
     * @return
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        
        //maxTime SQL 执行最大时长，超过自动停止运行，有助于发现问题
        performanceInterceptor.setMaxTime(1000); 
        //format SQL SQL是否格式化，默认false
        performanceInterceptor.setFormat(true); 
        
        return performanceInterceptor;
    }

    /**
     * 
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        
        List<ISqlParser> sqlParserList = new ArrayList<>();
        
        paginationInterceptor.setSqlParserList(sqlParserList);
        
        return paginationInterceptor;
    }

}
