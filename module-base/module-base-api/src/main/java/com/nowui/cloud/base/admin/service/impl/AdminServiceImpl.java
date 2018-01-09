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
    public Integer countForAdmin(String appId) {
        return userRpc.countV1(appId, UserType.ADMIN.getKey());
    }

    @Override
    public List<Admin> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
        List<Admin> listForAdmin = new ArrayList<>();
        
        List<User> userList = userRpc.listV1(appId, UserType.ADMIN.getKey(), pageIndex, pageSize);
        
        for (User user : userList) {
            
            Admin admin = new Admin();
            admin.setAdminId(user.getObjectId());
            admin.putAll(user);
            
            listForAdmin.add(admin);
        }
        
        return listForAdmin;
    }
    
    @Override
    public Admin find(String adminId) {
        Admin admin = super.find(adminId);
        
        if (!Util.isNullOrEmpty(admin)) {
            User user = userRpc.findV1(admin.getUserId());
            admin.putAll(user);
        }
        
        return admin;
    }

}