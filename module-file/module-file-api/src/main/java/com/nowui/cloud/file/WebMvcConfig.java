package com.nowui.cloud.file;

import com.nowui.cloud.util.FileUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态资源访问
 *
 * @author ZhongYongQiang
 *
 * 2018-01-01
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
    }

}
