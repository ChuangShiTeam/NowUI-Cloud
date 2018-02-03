package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户消息视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_notify_info")
public class UserNotifyView extends BaseView {

    /**
     * 用户消息编号
     */
    @Field
    @NotNull(message = "用户消息编号不能为空")
    private String userNotifyId;
    public static final String USER_NOTIFY_ID = "userNotifyId";

    /**
     * 应用Id
     */
    @Field
    @NotNull(message = "应用Id不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 消息编号
     */
    @Field
    @NotNull(message = "消息编号不能为空")
    private String notifyId;
    public static final String NOTIFY_ID = "notifyId";

    /**
     * 用户消息所属者
     */
    @Field
    @NotNull(message = "用户消息所属者不能为空")
    private String userNotifyOwerId;
    public static final String USER_NOTIFY_OWER_ID = "userNotifyOwerId";

    /**
     * 是否已读
     */
    @Field
    @NotNull(message = "是否已读不能为空")
    private Boolean userNotifyIsRead;
    public static final String USER_NOTIFY_IS_READ = "userNotifyIsRead";


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

    public String getNotifyId() {
        return getString(NOTIFY_ID);
    }

    public void setNotifyId(String notifyId) {
        put(NOTIFY_ID, notifyId);
    }

    public String getUserNotifyOwerId() {
        return getString(USER_NOTIFY_OWER_ID);
    }

    public void setUserNotifyOwerId(String userNotifyOwerId) {
        put(USER_NOTIFY_OWER_ID, userNotifyOwerId);
    }

    public Boolean getUserNotifyIsRead() {
        return getBoolean(USER_NOTIFY_IS_READ);
    }

    public void setUserNotifyIsRead(Boolean userNotifyIsRead) {
        put(USER_NOTIFY_IS_READ, userNotifyIsRead);
    }


}