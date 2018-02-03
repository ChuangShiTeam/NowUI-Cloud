package com.nowui.cloud.base.notify.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 消息
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Component
@TableName(value = "notify_info")
public class Notify extends BaseEntity {
    /**
     * 消息编号
     */
    @Id
    @TableId
    @NotNull(message = "消息编号不能为空")
    @Length(max = 32, message = "消息编号长度超出限制")
    private String notifyId;
    public static final String NOTIFY_ID = "notifyId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 消息内容( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     */
    @Field
    @TableField
    @NotNull(message = "消息内容不能为空")
    @Length(max = 500, message = "消息内容长度超出限制")
    private String notifyContent;
    public static final String NOTIFY_CONTENT = "notifyContent";

    /**
     * 目标编号
     */
    @Field
    @TableField
    @NotNull(message = "目标编号不能为空")
    @Length(max = 32, message = "目标编号长度超出限制")
    private String notifyTarget;
    public static final String NOTIFY_TARGET = "notifyTarget";

    /**
     * 目标类型(文章，话题)
     */
    @Field
    @TableField
    @NotNull(message = "目标类型不能为空")
    @Length(max = 32, message = "目标类型长度超出限制")
    private String notifyTargetType;
    public static final String NOTIFY_TARGET_TYPE = "notifyTargetType";

    /**
     * 消息类型( 消息的类型：1：公告 Announce, 2: 提醒 Remind, 3:信息 Message)
     */
    @Field
    @TableField
    @NotNull(message = "消息类型不能为空")
    @Length(max = 32, message = "消息类型长度超出限制")
    private String notifyType;
    public static final String NOTIFY_TYPE = "notifyType";

    /**
     * 提醒信息动作类型
     */
    @Field
    @TableField
    @NotNull(message = "提醒信息动作类型不能为空")
    @Length(max = 32, message = "提醒信息动作类型长度超出限制")
    private String notifyAction;
    public static final String NOTIFY_ACTION = "notifyAction";

    /**
     * 发送者编号
     */
    @Field
    @TableField
    @NotNull(message = " 发送者编号不能为空")
    @Length(max = 32, message = " 发送者编号长度超出限制")
    private String notifySender;
    public static final String NOTIFY_SENDER = "notifySender";


//    private String header;
//    public static final String header = "notifySender";


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

    public String getNotifyType() {
        return getString(NOTIFY_TYPE);
    }

    public void setNotifyType(String notifyType) {
        put(NOTIFY_TYPE, notifyType);
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