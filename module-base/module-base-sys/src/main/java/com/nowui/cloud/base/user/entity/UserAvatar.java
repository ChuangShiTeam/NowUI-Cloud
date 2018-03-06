package com.nowui.cloud.base.user.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 用户头像
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "user_avatar_info")
public class UserAvatar extends BaseEntity {

    /**
     * 用户头像编号
     */
    @Id
    @TableId
    @NotNull(message = "用户头像编号不能为空")
    @Length(max = 32, message = "用户头像编号长度超出限制")
    private String userAvatarId;
    public static final String USER_AVATAR_ID = "userAvatarId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 头像文件编号
     */
    @TableField
    @NotNull(message = "头像文件编号不能为空")
    @Length(max = 32, message = "头像文件编号长度超出限制")
    private String userAvatarFileId;
    public static final String USER_AVATAR_FILE_ID = "userAvatarFileId";

    /**
     * 用户头像路径
     */
    @TableField(exist = false)
    @NotNull(message = "用户头像路径不能为空")
    @Length(max = 200, message = "用户头像路径长度超出限制")
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