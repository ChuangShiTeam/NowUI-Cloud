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
 * 用户昵称
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "user_nick_name_info")
public class UserNickName extends BaseEntity {

    /**
     * 用户昵称编号
     */
    @Id
    @TableId
    @NotNull(message = "用户昵称编号不能为空")
    @Length(max = 32, message = "用户昵称编号长度超出限制")
    private String userNickNameId;
    public static final String USER_NICK_NAME_ID = "userNickNameId";

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
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 用户昵称
     */
    @Field
    @TableField
    @NotNull(message = "用户昵称不能为空")
    @Length(max = 200, message = "用户昵称长度超出限制")
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