package com.nowui.cloud.base.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.mapper.AppConfigMapper;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfigMapper, AppConfig> implements AppConfigService {

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;
    
    @Override
    public Integer adminCount(String appId, String configCategoryId, String configKey, Boolean configIsDisabled) {
        Integer count = count(
                new BaseWrapper<AppConfig>()
                        .eq(AppConfig.APP_ID, appId)
                        .eqAllowEmpty(AppConfig.CONFIG_CATEGORY_ID, configCategoryId)
                        .like(AppConfig.CONFIG_KEY, configKey)
                        .eqAllowEmpty(AppConfig.CONFIG_IS_DISABLED, configIsDisabled)
                        .eq(AppConfig.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<AppConfig> adminList(String appId, String configCategoryId, String configKey, Boolean configIsDisabled,
            Integer m, Integer n) {
        List<AppConfig> appConfigList = list(
                new BaseWrapper<AppConfig>()
                        .eq(AppConfig.APP_ID, appId)
                        .eqAllowEmpty(AppConfig.CONFIG_CATEGORY_ID, configCategoryId)
                        .like(AppConfig.CONFIG_KEY, configKey)
                        .eqAllowEmpty(AppConfig.CONFIG_IS_DISABLED, configIsDisabled)
                        .eq(AppConfig.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfig.SYSTEM_CREATE_TIME)),
                        m, 
                        n
        );
        for (AppConfig appConfig : appConfigList) {
            appConfig.put(AppConfigCategory.CONFIG_CATEGORY_NAME, appConfigCategoryService.find(appConfig.getConfigCategoryId()).getConfigCategoryName());
        }
        return appConfigList;
    }

}
