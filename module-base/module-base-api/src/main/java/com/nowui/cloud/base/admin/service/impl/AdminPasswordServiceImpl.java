package com.nowui.cloud.base.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.AdminPassword;
import com.nowui.cloud.base.admin.mapper.AdminPasswordMapper;
import com.nowui.cloud.base.admin.repository.AdminPasswordRepository;
import com.nowui.cloud.base.admin.service.AdminPasswordService;
import com.nowui.cloud.base.admin.view.AdminPasswordView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 管理员密码业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminPasswordServiceImpl extends BaseServiceImpl<AdminPasswordMapper, AdminPassword, AdminPasswordRepository, AdminPasswordView> implements AdminPasswordService {

    @Override
    public Integer countForAdmin(String appId) {
        Criteria criteria = Criteria.where(AdminPasswordView.APP_ID).is(appId)
                .and(AdminPasswordView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<AdminPasswordView> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AdminPasswordView.APP_ID).is(appId)
                .and(AdminPasswordView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, AdminPasswordView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        List<AdminPasswordView> adminPasswordViewList = list(query, sort, pageIndex, pageSize);
        
        return adminPasswordViewList;
    }

}
