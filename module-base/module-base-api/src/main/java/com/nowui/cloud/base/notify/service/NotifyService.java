package com.nowui.cloud.base.notify.service;
import java.util.List;

import com.nowui.cloud.base.notify.entity.Notify;
import com.nowui.cloud.base.notify.view.NotifyView;
import com.nowui.cloud.service.SuperService;

/**
 * 消息业务接口
 *
 * @author shawn
 *
 * 2018-01-28
 */
public interface NotifyService extends SuperService<Notify,NotifyView> {

    /**
     * 消息统计
     *
     * @param appId 应用编号
     * @param notifyContent 消息的内容( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     * @param notifyTarget 目标的ID
     * @param notifyTargetType 目标的类型( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     * @param notifyAction 提醒信息的动作类型
     * @return Integer 消息统计
     */
    Integer countForAdmin(String appId, String notifyContent, String notifyTarget, String notifyTargetType, String notifyAction);

    /**
     * 消息列表
     *
     * @param appId 应用编号
     * @param notifyContent 消息内容( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     * @param notifyTarget 目标编号
     * @param notifyTargetType 目标类型( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     * @param notifyAction 提醒信息的动作类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Notify> 消息列表
     */
    List<Notify> listForAdmin(String appId, String notifyContent, String notifyTarget, String notifyTargetType, String notifyAction, Integer pageIndex, Integer pageSize);


    // TODO: 拉取公告时无法确认是公告还是提醒.
    Notify getNewNotify(String appId);


    // 拉取最新公告
    List<Notify> getAnnounce(String appId,String lastTime);

    // 通过每一条的订阅记录的target、targetType、action、createdAt去查询Notify表，获取订阅的Notify记录。（注意订阅时间必须早于提醒创建时间）
    List<Notify> getNotifyInfoBySubscription(String appId,String target,String targetType,String action,String userId,String subscriptionTime);
}
