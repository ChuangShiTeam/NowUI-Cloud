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
     * @param User 用户信息
     */
    void sendSaveUser(User user);
}
