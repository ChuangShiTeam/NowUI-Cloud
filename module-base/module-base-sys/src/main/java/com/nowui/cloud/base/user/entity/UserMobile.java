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
 * 用户手机号码
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "user_mobile_info")
public class UserMobile extends BaseEntity {

    /**
     * 用户手机号码编号
     */
    @Id
    @TableId
    @NotNull(message = "用户手机号码编号不能为空")
    @Length(max = 32, message = "用户手机号码编号长度超出限制")
    private String userMobileId;
    public static final String USER_MOBILE_ID = "userMobileId";

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
     * 手机号码
     */
    @TableField
    @NotNull(message = "手机号码不能为空")
    @Length(max = 11, message = "手机号码长度超出限制")
    private String userMobile;
    public static final String USER_MOBILE = "userMobile";


    public String getUserMobileId() {
        return getString(USER_MOBILE_ID);
    }
    
    public void setUserMobileId(String userMobileId) {
        put(USER_MOBILE_ID, userMobileId);
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

    public String getUserMobile() {
        return getString(USER_MOBILE);
    }
    
    public void setUserMobile(String userMobile) {
        put(USER_MOBILE, userMobile);
    }


}