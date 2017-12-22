package com.nowui.cloud.app.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.app.app.entity.App;
import com.nowui.cloud.app.app.mapper.AppMapper;
import com.nowui.cloud.app.app.service.AppService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
//@CacheConfig(cacheNames = "appService")
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App> implements AppService {

    @Override
    public Integer adminCount(String appName) {
        Integer count = mapper.selectCount(
                new EntityWrapper<App>()
                        .like(App.APP_NAME, appName)
                        .eq(App.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<App> adminList(String appName, Integer m, Integer n) {
        List<App> appList = mapper.selectPage(
                new Page<App>(m, n),
                new EntityWrapper<App>()
                        .setSqlSelect(App.APP_ID)
                        .like(App.APP_NAME, appName)
                        .eq(App.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(App.SYSTEM_CREATE_TIME))
        );
        
        List<App> resultList = new ArrayList<App>();
        for (App app : appList) {
            resultList.add(find(app.getAppId()));
        }
        return resultList;
    }

}