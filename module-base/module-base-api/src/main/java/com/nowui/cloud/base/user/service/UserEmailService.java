package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.service.SuperService;

/**
 * 用户邮箱业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserEmailService extends SuperService<UserEmail> {
    
    /**
     * 根据用户编号查询用户邮箱信息
     * 
     * @param userId 用户编号
     * @return UserEmail 用户邮箱
     */
    UserEmail findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户邮箱信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
