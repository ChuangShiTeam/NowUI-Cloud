package com.nowui.cloud.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.nowui.cloud.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus插件配置
 *
 * @author marcus
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.nowui.cloud.**.**.mapper"}, markerInterface = BaseMapper.class)
public class MybatisPlusConfig {

    /**
     * mybatis-plus 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】 设置 dev test 环境开启
     *
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

        //maxTime SQL 执行最大时长，超过自动停止运行，有助于发现问题
        performanceInterceptor.setMaxTime(1000);
        //format SQL SQL是否格式化，默认false
        performanceInterceptor.setFormat(true);

        return performanceInterceptor;
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(3);
        return conf;
    }

//    /**
//     * mybatis-plus分页插件<br>
//     * 文档：http://mp.baomidou.com<br>
//     *
//     * @return
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//
//        // 开启 PageHelper 的支持
//        paginationInterceptor.setLocalPage(true);
//
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//
//        paginationInterceptor.setSqlParserList(sqlParserList);
//
//        return paginationInterceptor;
//    }

}
