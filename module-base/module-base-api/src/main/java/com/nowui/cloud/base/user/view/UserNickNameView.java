package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户昵称视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_nick_name_info")
public class UserNickNameView extends BaseView {

    /**
     * 用户昵称编号
     */
    @Field
    @NotNull(message = "用户昵称编号不能为空")
    private String userNickNameId;
    public static final String USER_NICK_NAME_ID = "userNickNameId";

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
     * 用户昵称
     */
    @Field
    @NotNull(message = "用户昵称不能为空")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";


    public String getUserNickNameId() {
        return getString(USER_NICK_NAME_ID);
    }

    public void setUserNickNameId(String userNickNameId) {
        put(USER_NICK_NAME_ID, userNickNameId);
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

    public String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
    }


}