package com.nowui.cloud.cms.navigation.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.mapper.NavigationMapper;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

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
    public Integer countForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName) {
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
    public List<Navigation> listForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n) {
        List<Navigation> navigationList = list(
                new BaseWrapper<Navigation>()
                        .eq(Navigation.APP_ID, appId)
                        .likeAllowEmpty(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_CODE, navigationCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_NAME, navigationName)
                        .eq(Navigation.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(Navigation.NAVIGATION_SORT)),
                m,
                n
        );
        
        return navigationList;
    }

	@Override
	public List<Navigation> mobileList(String appId, String navigationCategoryCode) {
		
		List<Navigation> resultList = list(new BaseWrapper<Navigation>()
				.eq(Navigation.APP_ID, appId)
				.eq(Navigation.SYSTEM_STATUS, true)
				.eq(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
				.orderAsc(Arrays.asList(Navigation.NAVIGATION_SORT))
			);
		
		return resultList;
	}

    @Override
    public List<Navigation> listByCategoryCode(String appId, String navigationCategoryCode) {
        
        List<Navigation> resultList = list(new BaseWrapper<Navigation>()
                .eq(Navigation.APP_ID, appId)
                .eq(Navigation.SYSTEM_STATUS, true)
                .eq(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                .orderAsc(Arrays.asList(Navigation.NAVIGATION_SORT))
            );
        
        return resultList;
    }

}