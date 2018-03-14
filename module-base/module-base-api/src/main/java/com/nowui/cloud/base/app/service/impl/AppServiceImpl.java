package com.nowui.cloud.base.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.mapper.AppMapper;
import com.nowui.cloud.base.app.repository.AppRepository;
import com.nowui.cloud.base.app.service.AppService;
import com.nowui.cloud.base.app.view.AppView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App, AppRepository, AppView> implements AppService {

    @Override
    public Integer countForAdmin(String appName) {
    	Criteria criteria = Criteria.where(AppView.APP_NAME).regex(".*?" + appName + ".*")
                .and(AppView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<AppView> listForAdmin(String appName, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AppView.APP_NAME).regex(".*?" + appName + ".*")
        		.and(AppView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, AppView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AppView> appViewList = list(query, sort, pageIndex, pageSize);
        
        return appViewList;
    }

    @Override
    public Boolean checkName(String appName) {
    	Criteria criteria = Criteria.where(AppView.APP_NAME).regex(".*?" + appName + ".*")
                .and(AppView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        Integer count = count(query);
        return count > 0;
    }

    @Override
    public Boolean checkName(String appId, String appName) {
        AppView appView = find(appId);
        
        if (appName.equals(appView.getAppName())) {
            return false;
        }
        
        return checkName(appName);
    }

}
