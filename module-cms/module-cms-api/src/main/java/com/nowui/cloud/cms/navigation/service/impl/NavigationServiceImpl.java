package com.nowui.cloud.cms.navigation.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.mapper.NavigationMapper;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 导航栏业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class NavigationServiceImpl extends BaseServiceImpl<NavigationMapper, Navigation> implements NavigationService {

    @Override
    public Integer adminCount(String appId, String navigationCategoryCode, String navigationCode, String navigationName) {
        Integer count = count(
                new BaseWrapper<Navigation>()
                        .eq(Navigation.APP_ID, appId)
                        .likeAllowEmpty(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_CODE, navigationCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_NAME, navigationName)
                        .eq(Navigation.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Navigation> adminList(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n) {
        List<Navigation> navigationList = list(
                new BaseWrapper<Navigation>()
                        .eq(Navigation.APP_ID, appId)
                        .likeAllowEmpty(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_CODE, navigationCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_NAME, navigationName)
                        .eq(Navigation.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Navigation.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return navigationList;
    }

}