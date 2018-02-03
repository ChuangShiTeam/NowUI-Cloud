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
 * 用户邮箱
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "user_email_info")
public class UserEmail extends BaseEntity {

    /**
     * 用户邮箱编号
     */
    @Id
    @TableId
    @NotNull(message = "用户邮箱编号不能为空")
    @Length(max = 32, message = "用户邮箱编号长度超出限制")
    private String userEmailId;
    public static final String USER_EMAIL_ID = "userEmailId";

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
     * 用户邮箱
     */
    @Field
    @TableField
    @NotNull(message = "用户邮箱不能为空")
    @Length(max = 50, message = "用户邮箱长度超出限制")
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