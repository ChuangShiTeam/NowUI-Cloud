package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户头像视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_avatar_info")
public class UserAvatarView extends BaseView {

    /**
     * 用户头像编号
     */
    @Field
    @NotNull(message = "用户头像编号不能为空")
    private String userAvatarId;
    public static final String USER_AVATAR_ID = "userAvatarId";

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
     * 头像文件编号
     */
    @Field
    @NotNull(message = "头像文件编号不能为空")
    private String userAvatarFileId;
    public static final String USER_AVATAR_FILE_ID = "userAvatarFileId";
    
    /**
     * 头像文件路径
     */
    @Field
    @NotNull(message = "头像文件路径不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    public String getUserAvatarId() {
        return getString(USER_AVATAR_ID);
    }

    public void setUserAvatarId(String userAvatarId) {
        put(USER_AVATAR_ID, userAvatarId);
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

    public String getUserAvatarFileId() {
        return getString(USER_AVATAR_FILE_ID);
    }

    public void setUserAvatarFileId(String userAvatarFileId) {
        put(USER_AVATAR_FILE_ID, userAvatarFileId);
    }
    
    public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
    }

    public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
    }
    
}