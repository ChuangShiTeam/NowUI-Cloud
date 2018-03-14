package com.nowui.cloud.base.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.AdminAccount;
import com.nowui.cloud.base.admin.mapper.AdminAccountMapper;
import com.nowui.cloud.base.admin.repository.AdminAccountRepository;
import com.nowui.cloud.base.admin.service.AdminAccountService;
import com.nowui.cloud.base.admin.view.AdminAccountView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 管理员账号业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminAccountServiceImpl extends BaseServiceImpl<AdminAccountMapper, AdminAccount, AdminAccountRepository, AdminAccountView> implements AdminAccountService {
    @Override
    public Integer countForAdmin(String appId) {
        Criteria criteria = Criteria.where(AdminAccountView.APP_ID).is(appId)
                .and(AdminAccountView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<AdminAccountView> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(AdminAccountView.APP_ID).is(appId)
                .and(AdminAccountView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, AdminAccountView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        List<AdminAccountView> adminAccountViewList = list(query, sort, pageIndex, pageSize);
        
        return adminAccountViewList;
    }

    @Override
    public void deleteByAdminId(String adminId, String systemRequestUserId) {
        List<AdminAccount> adminAccountList = list(
                new BaseWrapper<AdminAccount>()
                        .eq(AdminAccountView.ADMIN_ID, adminId)
                        .eq(AdminAccountView.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(AdminAccountView.SYSTEM_CREATE_TIME))
        );
        
        if (!Util.isNullOrEmpty(adminAccountList)) {
            for (AdminAccount adminAccount : adminAccountList) {
                delete(adminAccount.getAdminAccountId(), systemRequestUserId, adminAccount.getSystemVersion());
            }
        }
    }

    @Override
    public AdminAccount findByAdminId(String adminId) {
        AdminAccount adminAccount = find(
                new BaseWrapper<AdminAccount>()
                        .eq(AdminAccountView.ADMIN_ID, adminId)
                        .eq(AdminAccountView.SYSTEM_STATUS, true)
        );
        
        return adminAccount;
    }
}
