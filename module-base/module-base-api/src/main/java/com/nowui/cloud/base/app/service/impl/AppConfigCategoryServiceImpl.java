package com.nowui.cloud.base.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.mapper.AppConfigCategoryMapper;
import com.nowui.cloud.base.app.repository.AppConfigCategoryRepository;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigCategoryServiceImpl extends SuperServiceImpl<AppConfigCategoryMapper, AppConfigCategory, AppConfigCategoryRepository, AppConfigCategoryView> implements AppConfigCategoryService {

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
    public List<AppConfigCategoryView> listForAdmin(String appId, String configCategoryName, String configCategoryCode,
            Integer m, Integer n) {
        // TODO: 暂时无法判断错误原因.

        return null;
//        List<AppConfigCategory> appConfigCategoryList = list(
//                new BaseWrapper<AppConfigCategory>()
//                        .eq(AppConfigCategory.APP_ID, appId)
//                        .like(AppConfigCategory.CONFIG_CATEGORY_NAME, configCategoryName)
//                        .like(AppConfigCategory.CONFIG_CATEGORY_CODE, configCategoryCode)
//                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME)),
//                m,
//                n
//        );
        
//        return appConfigCategoryList;
    }

    @Override
    public List<AppConfigCategoryView> appList(String appId) {

        // TODO: 暂时无法判断错误原因.

        return null;
//        List<AppConfigCategory> appConfigCategoryList = list(
//                new BaseWrapper<AppConfigCategory>()
//                        .eq(AppConfigCategory.APP_ID, appId)
//                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME))
//        );
//
//        return appConfigCategoryList;
    }

    @Override
    public AppConfigCategoryView findByConfigCategoryCode(String appId, String configCategoryCode) {

        // TODO: 暂时无法判断错误原因.
        return null;
//        AppConfigCategory appConfigCategory = find(
////                new BaseWrapper<AppConfigCategory>()
////                        .eq(AppConfigCategory.APP_ID, appId)
////                        .eq(AppConfigCategory.CONFIG_CATEGORY_CODE, configCategoryCode)
////                        .eq(AppConfigCategory.SYSTEM_STATUS, true)
////                        .orderDesc(Arrays.asList(AppConfigCategory.SYSTEM_CREATE_TIME))
////        );
//        return appConfigCategory;
    }

}
