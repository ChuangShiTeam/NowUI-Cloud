package com.nowui.cloud.base.admin.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 管理员业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Integer adminCount(String appId, String userId) {
        Integer count = count(
                new BaseWrapper<Admin>()
                        .eq(Admin.APP_ID, appId)
                        .likeAllowEmpty(Admin.USER_ID, userId)
                        .eq(Admin.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Admin> adminList(String appId, String userId, Integer m, Integer n) {
        List<Admin> adminList = list(
                new BaseWrapper<Admin>()
                        .eq(Admin.APP_ID, appId)
                        .likeAllowEmpty(Admin.USER_ID, userId)
                        .eq(Admin.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Admin.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return adminList;
    }

}