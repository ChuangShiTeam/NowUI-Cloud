package com.nowui.cloud.base.admin.mq;

import com.nowui.cloud.base.user.entity.User;

/**
 * 管理员消息发送接口
 * 
 * @author marcus
 *
 * 2018年1月7日
 */
public interface AdminMq {
    
    /**
     * 发送保存用户信息消息到队列
     * 
     * @param user 用户信息
     */
    void sendSaveUser(User user);
    
    /**
     * 发送更新用户信息到队列
     * 
     * @param user 用户信息
     */
    void sendUpdateUser(User user);
    
    /**
     * 发送删除逻辑删除用户信息到队列
     * @param adminId 管理员编号
     * @param systemRequestUserId 请求用户编号
     */
    void sendDeleteUser(String adminId, String systemRequestUserId);
}
