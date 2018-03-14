package com.nowui.cloud.base.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.repository.AdminRepository;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 管理员业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin, AdminRepository, AdminView> implements AdminService {
    
    @Override
    public Integer countForAdmin(String appId) {
        Criteria criteria = Criteria.where(AdminView.APP_ID).is(appId)
                .and(AdminView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<AdminView> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AdminView.APP_ID).is(appId)
                .and(AdminView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, AdminView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        List<AdminView> adminViewList = list(query, sort, pageIndex, pageSize);
        
        return adminViewList;
    }

}