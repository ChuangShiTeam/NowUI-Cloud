package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.service.BaseService;

/**
 * 用户身份证业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserIdcardService extends BaseService<UserIdcard> {
    
    /**
     * 根据用户编号查询用户账号信息
     * 
     * @param userId 用户编号
     * @return UserIdcard 用户身份证信息
     */
    UserIdcard findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户身份证信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
