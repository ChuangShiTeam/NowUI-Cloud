package com.nowui.cloud.base.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.repository.UserRepository;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserRepository, UserView> implements UserService {
	
	
    @Override
    public Integer count(String appId, String userType) {
        Criteria criteria = Criteria.where(UserView.APP_ID).is(appId)
                .and(UserView.USER_TYPE).is(userType)
                .and(UserView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return count(query);
    }

    @Override
    public List<UserView> list(String appId, String userType, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(UserView.APP_ID).is(appId)
                .and(UserView.USER_TYPE).is(userType)
                .and(UserView.SYSTEM_STATUS).is(true);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, UserView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        return list(query, sort, pageIndex, pageSize);
    }
    
}