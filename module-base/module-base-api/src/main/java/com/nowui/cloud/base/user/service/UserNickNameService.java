package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.service.SuperService;

/**
 * 用户昵称业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserNickNameService extends SuperService<UserNickName> {
    
    /**
     * 根据用户编号查询用户昵称信息
     * 
     * @param userId 用户编号
     * @return UserNickName 用户昵称
     */
    UserNickName findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户昵称信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
