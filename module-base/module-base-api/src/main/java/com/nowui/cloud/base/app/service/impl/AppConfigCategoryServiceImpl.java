package com.nowui.cloud.base.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.mapper.AppConfigCategoryMapper;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigCategoryServiceImpl extends BaseServiceImpl<AppConfigCategoryMapper, AppConfigCategory> implements AppConfigCategoryService {

    @Override
    public Integer countForAdmin(String appId, String configCategoryName, String configCategoryCode) {
        Integer count = count(
                new BaseWrapper<AppConfigCategory>()
                        .eq(AppConfigCategory.APP_ID, appId)
                        .like(AppConfigCategory.CONFIG_CATEGORY_NAME, configCategoryName)
                        .like(AppConfigCategory.CONFIG_CATEGORY_CODE, configCategoryCode)
                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<AppConfigCategory> listForAdmin(String appId, String configCategoryName, String configCategoryCode,
            Integer m, Integer n) {
        List<AppConfigCategory> appConfigCategoryList = list(
                new BaseWrapper<AppConfigCategory>()
                        .eq(AppConfigCategory.APP_ID, appId)
                        .like(AppConfigCategory.CONFIG_CATEGORY_NAME, configCategoryName)
                        .like(AppConfigCategory.CONFIG_CATEGORY_CODE, configCategoryCode)
                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME)),
                m,
                n
        );
        
        return appConfigCategoryList;
    }

    @Override
    public List<AppConfigCategory> appList(String appId) {
        List<AppConfigCategory> appConfigCategoryList = list(
                new BaseWrapper<AppConfigCategory>()
                        .eq(AppConfigCategory.APP_ID, appId)
                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME))
        );
        
        return appConfigCategoryList;
    }

    @Override
    public AppConfigCategory findByConfigCategoryCode(String appId, String configCategoryCode) {
        AppConfigCategory appConfigCategory = find(
                new BaseWrapper<AppConfigCategory>()
                        .eq(AppConfigCategory.APP_ID, appId)
                        .eq(AppConfigCategory.CONFIG_CATEGORY_CODE, configCategoryCode)
                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME))
        );
        return appConfigCategory;
    }

}
