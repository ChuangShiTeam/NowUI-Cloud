package com.nowui.cloud.base.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.mapper.AdminMapper;
import com.nowui.cloud.base.admin.repository.AdminRepository;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.view.UserView;
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
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin, AdminRepository, AdminView> implements AdminService {
    
    @Autowired
    private UserRpc userRpc;

    @Override
    public Integer countForAdmin(String appId) {
        return 0;
    }

    @Override
    public List<AdminView> listForAdmin(String appId, Integer pageIndex, Integer pageSize) {
//        List<AdminView> listForAdmin = new ArrayList<>();
//        
//        List<User> userList = userRpc.listV1(appId, UserType.ADMIN.getKey(), pageIndex, pageSize);
//        
//        for (User user : userList) {
//            
//            AdminView adminView = new AdminView();
//            adminView.setAdminId(user.getObjectId());
//            adminView.putAll(user);
//            
//            listForAdmin.add(adminView);
//        }
        
        return null;
    }
    
    @Override
    public AdminView find(String adminId) {
        AdminView adminView = super.find(adminId);
        
//        if (!Util.isNullOrEmpty(adminView)) {
//            UserView userView = userRpc.findV1(adminView.getUserId());
//            adminView.putAll(userView);
//        }
        
        return adminView;
    }

}