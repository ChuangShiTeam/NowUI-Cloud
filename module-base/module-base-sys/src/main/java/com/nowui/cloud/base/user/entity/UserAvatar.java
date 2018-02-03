package com.nowui.cloud.base.user.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
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
    @Field
    @TableId
    @NotNull(message = "用户头像编号不能为空")
    @Length(max = 32, message = "用户头像编号长度超出限制")
    private String userAvatarId;
    public static final String USER_AVATAR_ID = "userAvatarId";

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
     * 用户编号
     */
    @Id
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 头像文件编号
     */
    @Field
    @TableField
    @NotNull(message = "头像文件编号不能为空")
    @Length(max = 32, message = "头像文件编号长度超出限制")
    private String userAvatar;
    public static final String USER_AVATAR = "userAvatar";


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

    public String getUserAvatar() {
        return getString(USER_AVATAR);
    }
    
    public void setUserAvatar(String userAvatar) {
        put(USER_AVATAR, userAvatar);
    }


}