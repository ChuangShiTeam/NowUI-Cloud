package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.view.UserAccountView;
import com.nowui.cloud.service.BaseService;

/**
 * 用户账号业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserAccountService extends BaseService<UserAccount,UserAccountView> {

    /**
     * 根据用户编号查询用户账号信息
     *  
     * @param userId 用户编号
     * @return UserAccount 用户账号
     */
    UserAccount findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户账号信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);
    
    /**
     * 
     * 根据用户账号查询用户账号信息
     * 
     * @param appId 应用编号
     * @param userAccount 用户账号
     * @return UserAccount 用户账号信息
     */
    UserAccount findByUserAccount(String appId, String userAccount);
    
}