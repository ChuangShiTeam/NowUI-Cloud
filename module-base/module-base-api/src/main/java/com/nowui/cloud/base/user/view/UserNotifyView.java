package com.nowui.cloud.base.user.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户消息视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "user_notify_info")
public class UserNotifyView extends BaseView {

    /**
     * 主键
     */
    @Id
    private String userNotifyId;
    public static final String USER_NOTIFY_ID = "userNotifyId";

    /**
     * 应用Id
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 是否已读
     */
    @Field
    private Boolean userNotifyIsRead;
    public static final String USER_NOTIFY_IS_READ = "userNotifyIsRead";

    /**
     * 用户消息所属者
     */
    @Field
    private String userNotifyOwerId;
    public static final String USER_NOTIFY_OWER_ID = "userNotifyOwerId";

    /**
     * 消息Id
     */
    @Field
    private String notifyId;
    public static final String NOTIFY_ID = "notifyId";


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