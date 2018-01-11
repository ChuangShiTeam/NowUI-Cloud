package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.service.BaseService;

/**
 * 用户微信业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserWechatService extends BaseService<UserWechat> {
    
    /**
     * 根据用户编号查询用户微信信息
     * 
     * @param userId 用户编号
     * @return UserWechat 用户微信
     */
    UserWechat findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户微信信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deletByUserId(String userId, String systemUpdateUserId);

}
