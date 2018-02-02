package com.nowui.cloud.base.notify.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 消息视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "notify_info")
public class NotifyView extends BaseView {

    /**
     * 消息编号
     */
    @Id
    private String notifyId;
    public static final String NOTIFY_ID = "notifyId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 消息类型( 消息类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     */
    @Field
    private String notifyType;
    public static final String NOTIFY_TYPE = "notifyType";

    /**
     * 内容
     */
    @Field
    private String notifyContent;
    public static final String NOTIFY_CONTENT = "notifyContent";

    /**
     * 目标编号
     */
    @Field
    private String notifyTarget;
    public static final String NOTIFY_TARGET = "notifyTarget";

    /**
     * 订阅目标类型：文章，话题.
     */
    @Field
    private String notifyTargetType;
    public static final String NOTIFY_TARGET_TYPE = "notifyTargetType";

    /**
     * 提醒信息的动作类型
     */
    @Field
    private String notifyAction;
    public static final String NOTIFY_ACTION = "notifyAction";

    /**
     * 发送者编号
     */
    @Field
    private String notifySender;
    public static final String NOTIFY_SENDER = "notifySender";


    public String getNotifyId() {
        return getString(NOTIFY_ID);
    }

    public void setNotifyId(String notifyId) {
        put(NOTIFY_ID, notifyId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getNotifyType() {
        return getString(NOTIFY_TYPE);
    }

    public void setNotifyType(String notifyType) {
        put(NOTIFY_TYPE, notifyType);
    }

    public String getNotifyContent() {
        return getString(NOTIFY_CONTENT);
    }

    public void setNotifyContent(String notifyContent) {
        put(NOTIFY_CONTENT, notifyContent);
    }

    public String getNotifyTarget() {
        return getString(NOTIFY_TARGET);
    }

    public void setNotifyTarget(String notifyTarget) {
        put(NOTIFY_TARGET, notifyTarget);
    }

    public String getNotifyTargetType() {
        return getString(NOTIFY_TARGET_TYPE);
    }

    public void setNotifyTargetType(String notifyTargetType) {
        put(NOTIFY_TARGET_TYPE, notifyTargetType);
    }

    public String getNotifyAction() {
        return getString(NOTIFY_ACTION);
    }

    public void setNotifyAction(String notifyAction) {
        put(NOTIFY_ACTION, notifyAction);
    }

    public String getNotifySender() {
        return getString(NOTIFY_SENDER);
    }

    public void setNotifySender(String notifySender) {
        put(NOTIFY_SENDER, notifySender);
    }


}