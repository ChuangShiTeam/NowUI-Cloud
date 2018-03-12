package com.nowui.cloud.base.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.mapper.AppConfigCategoryMapper;
import com.nowui.cloud.base.app.repository.AppConfigCategoryRepository;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import com.nowui.cloud.base.app.view.AppView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigCategoryServiceImpl extends BaseServiceImpl<AppConfigCategoryMapper, AppConfigCategory, AppConfigCategoryRepository, AppConfigCategoryView> implements AppConfigCategoryService {

    @Override
    public Integer countForAdmin(String appId, String configCategoryName, String configCategoryCode) {
        Criteria criteria = Criteria.where(AppConfigCategoryView.APP_ID).is(appId)
                .and(AppConfigCategoryView.CONFIG_CATEGORY_NAME).regex(".*?" + configCategoryName + ".*")
                .and(AppConfigCategoryView.CONFIG_CATEGORY_CODE).regex(".*?" + configCategoryCode + ".*")
                .and(AppConfigCategoryView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        return count(query);
    }

    @Override
    public List<AppConfigCategoryView> listForAdmin(String appId, String configCategoryName, String configCategoryCode,
            Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AppConfigCategoryView.APP_ID).is(appId)
                .and(AppConfigCategoryView.CONFIG_CATEGORY_NAME).regex(".*?" + configCategoryName + ".*")
                .and(AppConfigCategoryView.CONFIG_CATEGORY_CODE).regex(".*?" + configCategoryCode + ".*")
                .and(AppConfigCategoryView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, AppConfigCategoryView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AppConfigCategoryView> appConfigCategoryViewList = list(query, sort, pageIndex, pageSize);

        return appConfigCategoryViewList;
    }

    @Override
    public List<AppConfigCategoryView> appList(String appId) {
        Criteria criteria = Criteria.where(AppConfigCategoryView.APP_ID).is(appId)
                .and(AppConfigCategoryView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, AppConfigCategoryView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AppConfigCategoryView> appConfigCategoryViewList = list(query, sort);

        return appConfigCategoryViewList;
    }

    @Override
    public AppConfigCategoryView findByConfigCategoryCode(String appId, String configCategoryCode) {
        Criteria criteria = Criteria.where(AppConfigCategoryView.APP_ID).is(appId)
                .and(AppConfigCategoryView.CONFIG_CATEGORY_CODE).is(configCategoryCode)
                .and(AppConfigCategoryView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        AppConfigCategoryView AppConfigCategoryView = find(query);

        return AppConfigCategoryView;
    }

}
