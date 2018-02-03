package com.nowui.cloud.base.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.repository.AdminRepository;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 管理员业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class AdminServiceImpl extends SuperServiceImpl<AdminMapper, Admin, AdminRepository, AdminView> implements AdminService {
    
    @Autowired
    private UserRpc userRpc;

    @Override
    public Integer countForAdmin(String appId) {
        return userRpc.countV1(appId, UserType.ADMIN.getKey());
    }

    @Override
    public List<AdminView> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
        List<AdminView> listForAdmin = new ArrayList<>();
        
        List<User> userList = userRpc.listV1(appId, UserType.ADMIN.getKey(), pageIndex, pageSize);
        
        for (User user : userList) {
            
            AdminView adminView = new AdminView();
            adminView.setAdminId(user.getObjectId());
            adminView.putAll(user);
            
            listForAdmin.add(adminView);
        }
        
        return listForAdmin;
    }
    
    @Override
    public AdminView find(String adminId) {
        AdminView adminView = super.find(adminId);
        
        if (!Util.isNullOrEmpty(adminView)) {
            User user = userRpc.findV1(adminView.getUserId());
            adminView.putAll(user);
        }
        
        return adminView;
    }

}