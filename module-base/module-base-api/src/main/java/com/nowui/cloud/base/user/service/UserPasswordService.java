package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.service.BaseService;

/**
 * 用户密码业务接口
 *
 * @author marcus
 *
 * 2018-01-11
 */
public interface UserPasswordService extends BaseService<UserPassword> {

    /**
     * 根据用户编号查询用户密码信息
     *  
     * @param userId 用户编号
     * @return UserPassword 用户密码
     */
    UserPassword findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户密码信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);
}
