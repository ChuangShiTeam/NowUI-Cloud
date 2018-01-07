package com.nowui.cloud.cms.navigationBar.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.navigationBar.entity.NavigationBar;
import com.nowui.cloud.cms.navigationBar.mapper.NavigationBarMapper;
import com.nowui.cloud.cms.navigationBar.service.NavigationBarService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 导航栏业务实现
 *
 * @author shawn
 *
 * 2017-12-31
 */
@Service
public class NavigationBarServiceImpl extends BaseServiceImpl<NavigationBarMapper, NavigationBar> implements NavigationBarService {

    @Override
    public Integer adminCount(String appId, String navigationBarCategoryCode, String navigationBarCode, String navigationBarName) {
        Integer count = count(
                new BaseWrapper<NavigationBar>()
                        .eq(NavigationBar.APP_ID, appId)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_CATEGORY_CODE, navigationBarCategoryCode)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_CODE, navigationBarCode)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_NAME, navigationBarName)
                        .eq(NavigationBar.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<NavigationBar> adminList(String appId, String navigationBarCategoryCode, String navigationBarCode, String navigationBarName, Integer m, Integer n) {
        List<NavigationBar> navigationBarList = list(
                new BaseWrapper<NavigationBar>()
                        .eq(NavigationBar.APP_ID, appId)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_CATEGORY_CODE, navigationBarCategoryCode)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_CODE, navigationBarCode)
                        .likeAllowEmpty(NavigationBar.NAVIGATION_BAR_NAME, navigationBarName)
                        .eq(NavigationBar.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(NavigationBar.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return navigationBarList;
    }

}