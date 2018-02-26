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
 * 用户性别
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "user_sex_info")
public class UserSex extends BaseEntity {

    /**
     * 用户性别编号
     */
    @Id
    @TableId
    @NotNull(message = "用户性别编号不能为空")
    @Length(max = 32, message = "用户性别编号长度超出限制")
    private String userSexId;
    public static final String USER_SEX_ID = "userSexId";

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
     * 性别    （0：未知，1：男，2：女）
     */
    @TableField
    @NotNull(message = "性别不能为空")
    @Length(max = 1, message = "性别长度超出限制")
    private String userSex;
    public static final String USER_SEX = "userSex";

    public String getUserIdcardId() {
        return getString(USER_SEX_ID);
    }
    
    public void setUserIdcardId(String userSexId) {
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