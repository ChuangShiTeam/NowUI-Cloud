package com.nowui.cloud.base.notify.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.notify.repository.NotifyRepository;
import com.nowui.cloud.base.notify.view.NotifyView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.notify.entity.Notify;
import com.nowui.cloud.base.notify.mapper.NotifyMapper;
import com.nowui.cloud.base.notify.service.NotifyService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 消息表业务实现
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Service
public class NotifyServiceImpl extends SuperServiceImpl<NotifyMapper, Notify,NotifyRepository,NotifyView> implements NotifyService {

    public static final String NOTIFY_ANNOUNCE_RECORD_BY_APP_ID = "notify_announce_record_by_app_id";

    @Override
    public Integer countForAdmin(String appId, String notifyContent, String notifyTarget, String notifyTargetType, String notifyAction) {

        Integer count = count(
                new BaseWrapper<Notify>()
                        .eq(Notify.APP_ID, appId)
                        .likeAllowEmpty(Notify.NOTIFY_CONTENT, notifyContent)
                        .likeAllowEmpty(Notify.NOTIFY_TARGET, notifyTarget)
                        .likeAllowEmpty(Notify.NOTIFY_TARGET_TYPE, notifyTargetType)
                        .likeAllowEmpty(Notify.NOTIFY_ACTION, notifyAction)
                        .eq(Notify.SYSTEM_STATUS, true)
        );

        return count;
    }

    @Override
    public List<Notify> listForAdmin(String appId, String notifyContent, String notifyTarget, String notifyTargetType, String notifyAction, Integer pageIndex, Integer pageSize) {
        List<Notify> notifyList = list(
                new BaseWrapper<Notify>()
                        .eq(Notify.APP_ID, appId)
                        .likeAllowEmpty(Notify.NOTIFY_CONTENT, notifyContent)
                        .likeAllowEmpty(Notify.NOTIFY_TARGET, notifyTarget)
                        .likeAllowEmpty(Notify.NOTIFY_TARGET_TYPE, notifyTargetType)
                        .likeAllowEmpty(Notify.NOTIFY_ACTION, notifyAction)
                        .eq(Notify.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Notify.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return notifyList;
    }


    // 从UserNotify中获取最近的一条公告信息的创建时间: SYSTEM_CREATE_TIME。
    @Override
    public Notify getNewNotify(String appId) {

        List<Notify> notifys = list(
                new BaseWrapper<Notify>()
                        .eq(Notify.APP_ID, appId)
                        .eq(Notify.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Notify.SYSTEM_CREATE_TIME))
        );

        return notifys == null ? new Notify() : notifys.get(0);
    }

    // TODO: 系统d
    @Override
    public List<Notify> getAnnounce(String appId, String lastTime) {
        List<Notify> notifies = list(
                new BaseWrapper<Notify>()
                        .eq(Notify.APP_ID, appId)
                        .eq(Notify.NOTIFY_TARGET_TYPE,"")
                        .eq(Notify.SYSTEM_STATUS, true)
                        .gt(Notify.SYSTEM_CREATE_TIME, lastTime)
        );
        return notifies;
    }

    @Override
    public List<Notify> getNotifyInfoBySubscription(String appId,String target, String targetType, String action, String userId, String subscriptionTime) {
        List<Notify> notifies = list(
                new BaseWrapper<Notify>()
                .eq(Notify.APP_ID,appId)
                .eq(Notify.NOTIFY_TARGET,target)
                .eq(Notify.NOTIFY_TARGET_TYPE,targetType)
                .eq(Notify.NOTIFY_ACTION,action)
                .eq(Notify.NOTIFY_SENDER,userId)
                .eq(Notify.SYSTEM_STATUS,true)
                .lt(Notify.SYSTEM_CREATE_TIME,subscriptionTime)
        );

        return notifies;
    }

}