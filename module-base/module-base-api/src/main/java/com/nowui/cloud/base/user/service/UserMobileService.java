package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.service.BaseService;

/**
 * 用户手机号码业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserMobileService extends BaseService<UserMobile> {
    
    /**
     * 根据用户编号查询用户手机号码信息
     * 
     * @param userId 用户编号
     * @return UserMobile 用户手机号码
     */
    UserMobile findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户手机号码信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
