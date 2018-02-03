package com.nowui.cloud.base.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.mapper.AppConfigMapper;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigServiceImpl extends SuperServiceImpl<AppConfigMapper, AppConfig> implements AppConfigService {

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;
    
    @Override
    public Integer countForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled) {
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
    public List<AppConfig> listForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled,
            Integer pageIndex, Integer pageSize) {
        List<AppConfig> appConfigList = list(
                new BaseWrapper<AppConfig>()
                        .eq(AppConfig.APP_ID, appId)
                        .eqAllowEmpty(AppConfig.CONFIG_CATEGORY_ID, configCategoryId)
                        .like(AppConfig.CONFIG_KEY, configKey)
                        .eqAllowEmpty(AppConfig.CONFIG_IS_DISABLED, configIsDisabled)
                        .eq(AppConfig.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfig.SYSTEM_CREATE_TIME)),
                pageIndex, 
                pageSize
        );
        for (AppConfig appConfig : appConfigList) {
            appConfig.put(AppConfigCategory.CONFIG_CATEGORY_NAME, appConfigCategoryService.find(appConfig.getConfigCategoryId()).getConfigCategoryName());
        }
        return appConfigList;
    }

    @Override
    public List<AppConfig> abledListByConfigCategoryId(String appId, String configCategoryId) {
        List<AppConfig> appConfigList = list(
                new BaseWrapper<AppConfig>()
                        .eq(AppConfig.APP_ID, appId)
                        .eqAllowEmpty(AppConfig.CONFIG_CATEGORY_ID, configCategoryId)
                        .eqAllowEmpty(AppConfig.CONFIG_IS_DISABLED, false)
                        .eq(AppConfig.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfig.SYSTEM_CREATE_TIME))
        );
        return appConfigList;
    }

}
