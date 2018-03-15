package com.nowui.cloud.base.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.menu.view.MenuView;
import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.mapper.RoleMapper;
import com.nowui.cloud.base.role.repository.RoleRepository;
import com.nowui.cloud.base.role.service.RoleService;
import com.nowui.cloud.base.role.view.RoleView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 角色业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role,RoleRepository,RoleView> implements RoleService {

    @Override
    public Integer countForAdmin(String appId, String roleName, String roleCode) {
        Criteria criteria = Criteria.where(RoleView.APP_ID).is(appId)
                .and(RoleView.ROLE_NAME).regex(".*?" + roleName + ".*")
                .and(RoleView.ROLE_CODE).regex(".*?" + roleCode + ".*")
                .and(RoleView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return count(query);
    }

    @Override
    public List<RoleView> listForAdmin(String appId, String roleName, String roleCode, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(RoleView.APP_ID).is(appId)
                .and(RoleView.ROLE_NAME).regex(".*?" + roleName + ".*")
                .and(RoleView.ROLE_CODE).regex(".*?" + roleCode + ".*")
                .and(MenuView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, RoleView.ROLE_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        return list(query, sort, pageIndex, pageSize);
    }

}