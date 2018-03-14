package com.nowui.cloud.base.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.mapper.AppConfigMapper;
import com.nowui.cloud.base.app.repository.AppConfigRepository;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.base.app.view.AppConfigView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfigMapper, AppConfig, AppConfigRepository, AppConfigView> implements AppConfigService {

    @Override
    public Integer countForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled) {
    	Criteria criteria = Criteria.where(AppConfigView.APP_ID).is(appId)
    			.and(AppConfigView.CONFIG_CATEGORY_ID).is(configCategoryId)
    			.and(AppConfigView.CONFIG_KEY).regex(".*?" + configKey + ".*")
    			.and(AppConfigView.CONFIG_IS_DISABLED).is(configIsDisabled)
                .and(AppConfigView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        Integer count = count(query);
    	
        return count;
    }

    @Override
    public List<AppConfigView> listForAdmin(String appId, String configCategoryId, String configKey, Boolean configIsDisabled,
            Integer pageIndex, Integer pageSize) {
    	Criteria criteria = Criteria.where(AppConfigView.APP_ID).is(appId)
    			.and(AppConfigView.CONFIG_CATEGORY_ID).is(configCategoryId)
    			.and(AppConfigView.CONFIG_KEY).regex(".*?" + configKey + ".*")
    			.and(AppConfigView.CONFIG_IS_DISABLED).is(configIsDisabled)
        		.and(AppConfigView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, AppConfigView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AppConfigView> appConfigViewList = list(query, sort, pageIndex, pageSize);
       
        return appConfigViewList;
    }

    @Override
    public List<AppConfigView> abledListByConfigCategoryId(String appId, String configCategoryId) {
    	Criteria criteria = Criteria.where(AppConfigView.APP_ID).is(appId)
    			.and(AppConfigView.CONFIG_CATEGORY_ID).is(configCategoryId)
    			.and(AppConfigView.CONFIG_IS_DISABLED).is(false)
        		.and(AppConfigView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, AppConfigView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AppConfigView> appConfigViewList = list(query, sort);

    	
        return appConfigViewList;
    }

}
