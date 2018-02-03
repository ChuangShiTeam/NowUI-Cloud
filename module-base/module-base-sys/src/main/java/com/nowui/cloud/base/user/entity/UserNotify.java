package com.nowui.cloud.base.user.entity;

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
 * 用户消息队列表
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component
@TableName(value = "user_notify_info")
public class UserNotify extends BaseEntity {

    /**
     * 消息编号
     */
    @Id
    @TableId
    @NotNull(message = "消息编号不能为空")
    @Length(max = 32, message = "消息编号长度超出限制")
    private String userNotifyId;
    public static final String USER_NOTIFY_ID = "userNotifyId";

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
     * 是否已读
     */
    @Field
    @TableField
    @NotNull(message = "是否已读不能为空")
    @Length(max = 1, message = "是否已读长度超出限制")
    private Boolean userNotifyIsRead;
    public static final String USER_NOTIFY_IS_READ = "userNotifyIsRead";

    /**
     * 用户消息所属者
     */
    @Field
    @TableField
    @NotNull(message = "用户消息所属者不能为空")
    @Length(max = 32, message = "用户消息所属者长度超出限制")
    private String userNotifyOwerId;
    public static final String USER_NOTIFY_OWER_ID = "userNotifyOwerId";

    /**
     * 消息编号
     */
    @Field
    @TableField
    @NotNull(message = "消息编号不能为空")
    @Length(max = 32, message = "消息编号长度超出限制")
    private String notifyId;
    public static final String NOTIFY_ID = "notifyId";

    /**
     * 消息Id集合
     */
    public static final String NOTIFYIDS = "notifyids";

    /**
     * 用户头像
     */
    public static final String HEADER = "header";

    /*
    *  用户姓名
    * */
    public static final String USERNAME = "userName";

    /*
    * 动作类型
     */
    public static final String ACTION = "action";

    /*
    * 目标类型
    * */
    public static final String TARGETTYPE = "targetType";

    /*
     * 目标ID
     * */
    public static final String TARGETID = "targetId";

    /*
    *  目标内容
    * */
    public static final String TARGETCONTENT = "targetContent";

    /*
    *  发送的消息
    * */
    public static final String MESSAGE = "message";

    public String getUserNotifyId() {
        return getString(USER_NOTIFY_ID);
    }
    
    public void setUserNotifyId(String userNotifyId) {
        put(USER_NOTIFY_ID, userNotifyId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public Boolean getUserNotifyIsRead() {
        return getBoolean(USER_NOTIFY_IS_READ);
    }
    
    public void setUserNotifyIsRead(Boolean userNotifyIsRead) {
        put(USER_NOTIFY_IS_READ, userNotifyIsRead);
    }

    public String getUserNotifyOwerId() {
        return getString(USER_NOTIFY_OWER_ID);
    }
    
    public void setUserNotifyOwerId(String userNotifyOwerId) {
        put(USER_NOTIFY_OWER_ID, userNotifyOwerId);
    }

    public String getNotifyId() {
        return getString(NOTIFY_ID);
    }
    
    public void setNotifyId(String notifyId) {
        put(NOTIFY_ID, notifyId);
    }


}