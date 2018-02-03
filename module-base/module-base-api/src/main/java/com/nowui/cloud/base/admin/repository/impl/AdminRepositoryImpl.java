package com.nowui.cloud.base.admin.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.base.admin.repository.AdminRepository;
import com.nowui.cloud.base.admin.view.AdminView;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-03
 */
@Component
public class AdminRepositoryImpl extends BaseRepositoryImpl<AdminView> implements AdminRepository {

    @Override
    public Integer countForAdmin(String appId, String userId) {
        Criteria criteria = Criteria.where(AdminView.APP_ID).is(appId)
                .and(AdminView.USER_ID).regex(".*?\\" + userId + ".*")
                .and(AdminView.SYSTEM_STATUS).is(true);

        Integer count = count(criteria);

        return count;
    }

    @Override
    public List<AdminView> listForAdmin(String appId, String userId, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AdminView.APP_ID).is(appId)
                .and(AdminView.USER_ID).regex(".*?\\" + userId + ".*")
                .and(AdminView.SYSTEM_STATUS).is(true);

        List<AdminView> adminViewList = list(criteria, pageIndex, pageSize);

        return adminViewList;
    }

}
