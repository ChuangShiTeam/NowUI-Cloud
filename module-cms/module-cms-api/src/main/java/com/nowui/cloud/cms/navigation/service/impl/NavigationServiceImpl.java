package com.nowui.cloud.cms.navigation.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.mapper.NavigationMapper;
import com.nowui.cloud.cms.navigation.repository.NavigationRepository;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.cms.navigation.view.NavigationView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 导航栏业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class NavigationServiceImpl extends BaseServiceImpl<NavigationMapper, Navigation, NavigationRepository, NavigationView> implements NavigationService {
	
    @Override
    public Integer countForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName) {
        Criteria criteria = Criteria.where(NavigationView.APP_ID).is(appId)
                .and(NavigationView.NAVIGATION_CATEGORY_CODE).regex(".*?" + navigationCategoryCode + ".*")
                .and(NavigationView.NAVIGATION_CODE).regex(".*?" + navigationCode + ".*")
                .and(NavigationView.NAVIGATION_NAME).regex(".*?" + navigationName + ".*")
                .and(NavigationView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<NavigationView> listForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n) {
        Criteria criteria = Criteria.where(NavigationView.APP_ID).is(appId)
                .and(NavigationView.NAVIGATION_CATEGORY_CODE).regex(".*?" + navigationCategoryCode + ".*")
                .and(NavigationView.NAVIGATION_CODE).regex(".*?" + navigationCode + ".*")
                .and(NavigationView.NAVIGATION_NAME).regex(".*?" + navigationName + ".*")
                .and(NavigationView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, NavigationView.NAVIGATION_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<NavigationView> navigationViews = list(query, sort, m, n);
        
        return navigationViews;
    }

	@Override
	public List<NavigationView> mobileList(String appId, String navigationCategoryCode) {
		Criteria criteria = Criteria.where(NavigationView.APP_ID).is(appId)
                .and(NavigationView.NAVIGATION_CATEGORY_CODE).regex(".*?" + navigationCategoryCode + ".*")
                .and(NavigationView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, NavigationView.NAVIGATION_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<NavigationView> navigationViews = list(query, sort);
        
        return navigationViews;
	}

    @Override
    public List<NavigationView> listByCategoryCode(String appId, String navigationCategoryCode) {
        Criteria criteria = Criteria.where(NavigationView.APP_ID).is(appId)
                .and(NavigationView.NAVIGATION_CATEGORY_CODE).regex(".*?" + navigationCategoryCode + ".*")
                .and(NavigationView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, NavigationView.NAVIGATION_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<NavigationView> navigationViews = list(query, sort);
        
        return navigationViews;
    }

}