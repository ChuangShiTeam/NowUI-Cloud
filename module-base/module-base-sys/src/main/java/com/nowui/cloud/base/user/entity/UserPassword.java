package com.nowui.cloud.base.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 用户密码
 *
 * @author marcus
 *
 * 2018-01-11
 */
@Component
@TableName(value = "user_password_info")
public class UserPassword extends BaseEntity {

    /**
     * 用户密码编号
     */
    @Id
    @TableId
    @NotNull(message = "用户密码编号不能为空")
    @Length(max = 32, message = "用户密码编号长度超出限制")
    private String userPasswordId;
    public static final String USER_PASSWORD_ID = "userPasswordId";

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
     * 用户密码
     */
    @TableField
    @NotNull(message = "用户密码不能为空")
    @Length(max = 128, message = "用户密码长度超出限制")
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