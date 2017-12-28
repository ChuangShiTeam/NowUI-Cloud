package com.nowui.cloud.base.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.mapper.AppMapper;
import com.nowui.cloud.base.app.service.AppService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App> implements AppService {

    @Override
    public Integer adminCount(String appName) {
        Integer count = count(
                new BaseWrapper<App>()
                        .like(App.APP_NAME, appName)
                        .eq(App.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<App> adminList(String appName, Integer m, Integer n) {
        List<App> appList = list(
                new BaseWrapper<App>()
                        .like(App.APP_NAME, appName)
                        .eq(App.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(App.SYSTEM_CREATE_TIME))
                ,m
                ,n
        );
        
        return appList;
    }

    @Override
    public Boolean checkName(String appName) {
        Integer count = mapper.selectCount(
                new EntityWrapper<App>()
                        .eq(App.APP_NAME, appName)
                        .eq(App.SYSTEM_STATUS, true)
        );
        return count > 0;
    }

    @Override
    public Boolean checkName(String appId, String appName) {
        App app = find(appId);
        
        if (appName.equals(app.getAppName())) {
            return false;
        }
        
        return checkName(appName);
    }

}
