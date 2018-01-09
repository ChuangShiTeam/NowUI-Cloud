package com.nowui.cloud.base.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

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
    public Integer countForAdmin(String appId, String userAccount, String userNickName, String userMobile) {
        return userRpc.count(appId, UserType.ADMIN.getKey(), userAccount, userNickName, "", userMobile);
    }

    @Override
    public List<Admin> listForAdmin(String appId, String userAccount, String userNickName, String userMobile, Integer pageIndex, Integer pageSize) {
        List<Admin> adminList = new ArrayList<>();
        
        List<User> userList = userRpc.list(appId, UserType.ADMIN.getKey(), userAccount, userNickName, "", userMobile, pageIndex, pageSize);
        
        for (User user : userList) {
            
            Admin admin = new Admin();
            admin.setAdminId(user.getObjectId());
            admin.putAll(user);
            
            adminList.add(admin);
        }
        
        return adminList;
    }
    
    @Override
    public Admin find(String adminId) {
        Admin admin = super.find(adminId);
        
        if (!Util.isNullOrEmpty(admin)) {
            User user = userRpc.find(admin.getUserId());
            admin.putAll(user);
        }
        
        return admin;
    }

}