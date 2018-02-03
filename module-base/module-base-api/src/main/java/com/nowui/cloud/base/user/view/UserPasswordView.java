package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户密码视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_password_info")
public class UserPasswordView extends BaseView {

    /**
     * 用户密码编号
     */
    @Field
    @NotNull(message = "用户密码编号不能为空")
    private String userPasswordId;
    public static final String USER_PASSWORD_ID = "userPasswordId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 用户密码
     */
    @Field
    @NotNull(message = "用户密码不能为空")
    private String userPassword;
    public static final String USER_PASSWORD = "userPassword";


    public String getUserPasswordId() {
        return getString(USER_PASSWORD_ID);
    }

    public void setUserPasswordId(String userPasswordId) {
        put(USER_PASSWORD_ID, userPasswordId);
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

    public String getUserPassword() {
        return getString(USER_PASSWORD);
    }

    public void setUserPassword(String userPassword) {
        put(USER_PASSWORD, userPassword);
    }


}