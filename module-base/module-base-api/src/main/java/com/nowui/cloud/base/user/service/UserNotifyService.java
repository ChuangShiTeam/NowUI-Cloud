package com.nowui.cloud.base.user.service;

import java.util.List;

import com.nowui.cloud.base.user.entity.UserNotify;
import com.nowui.cloud.service.SuperService;

/**
 * 用户消息队列表业务接口
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
public interface UserNotifyService extends SuperService<UserNotify> {

    /**
     * 用户消息队列表统计
     *
     * @param appId            应用编号
     * @param userNotifyIsRead 是否已读
     * @param userNotifyOwerId 用户消息所属者
     * @return Integer 用户消息队列表统计
     */
    Integer countForAdmin(String appId, Boolean userNotifyIsRead, String userNotifyOwerId);

    /**
     * 用户消息队列表列表
     *
     * @param appId            应用编号
     * @param userNotifyIsRead 是否已读
     * @param userNotifyOwerId 用户消息所属者
     * @param pageIndex        页码
     * @param pageSize         每页个数
     * @return List<UserNotify> 用户消息队列表列表
     */
    List<UserNotify> listForAdmin(String appId, Boolean userNotifyIsRead, String userNotifyOwerId, Integer pageIndex, Integer pageSize);


    /*
     * 获取UserNotify 记录.
     */
    UserNotify getNewNotify(String appId);


    List<UserNotify> getUserNotifyByUserId(String userNotifyOwerId, String appId,String notifyType);
}
