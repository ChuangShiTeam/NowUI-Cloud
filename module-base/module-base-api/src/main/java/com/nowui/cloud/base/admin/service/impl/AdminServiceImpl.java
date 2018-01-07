package com.nowui.cloud.base.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 管理员业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements AdminService {
    
    @Autowired
    private UserRpc userRpc;

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
    public List<Admin> adminList(String appId, String userId, Integer pageIndex, Integer pageSize) {
        List<Admin> adminList = list(
                new BaseWrapper<Admin>()
                        .eq(Admin.APP_ID, appId)
                        .likeAllowEmpty(Admin.USER_ID, userId)
                        .eq(Admin.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Admin.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
        
        return adminList;
    }

}