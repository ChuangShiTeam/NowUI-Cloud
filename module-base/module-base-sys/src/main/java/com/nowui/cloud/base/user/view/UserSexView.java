package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户性别视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_idcard_info")
public class UserSexView extends BaseView {

    /**
     * 用户性别编号
     */
    @Field
    @NotNull(message = "用户性别编号不能为空")
    private String userSexId;
    public static final String USER_SEX_ID = "userSexId";

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
     * 性别
     */
    @Field
    @NotNull(message = "性别不能为空")
    private String userSex;
    public static final String USER_SEX = "userSex";

    public String getUserSexId() {
        return getString(USER_SEX_ID);
    }

    public void setUserSexId(String userSexId) {
        put(USER_SEX_ID, userSexId);
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

    public String getUserSex() {
        return getString(USER_SEX);
    }

    public void setUserSex(String userSex) {
        put(USER_SEX, userSex);
    }

}