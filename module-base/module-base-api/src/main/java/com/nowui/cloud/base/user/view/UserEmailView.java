package com.nowui.cloud.base.user.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户邮箱视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "user_email_info")
public class UserEmailView extends BaseView {

    /**
     * 用户邮箱编号
     */
    @Id
    private String userEmailId;
    public static final String USER_EMAIL_ID = "userEmailId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 用户邮箱
     */
    @Field
    private String userEmail;
    public static final String USER_EMAIL = "userEmail";


    public String getUserEmailId() {
        return getString(USER_EMAIL_ID);
    }

    public void setUserEmailId(String userEmailId) {
        put(USER_EMAIL_ID, userEmailId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getUserEmail() {
        return getString(USER_EMAIL);
    }

    public void setUserEmail(String userEmail) {
        put(USER_EMAIL, userEmail);
    }


}