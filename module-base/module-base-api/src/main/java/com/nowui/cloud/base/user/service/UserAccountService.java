package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.service.BaseService;

/**
 * 用户账号业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserAccountService extends BaseService<UserAccount> {

    /**
     * 根据用户编号查询用户账号信息
     * 
     * @param userId
     * @return UserAccount
     */
    UserAccount findByUserId(String userId);
    
}