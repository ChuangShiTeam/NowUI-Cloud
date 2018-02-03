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
 * 用户角色
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@TableName(value = "user_role_map")
public class UserRole extends BaseEntity {

    /**
     * 用户角色编号
     */
    @Id
    @TableId
    @NotNull(message = "用户角色编号不能为空")
    @Length(max = 32, message = "用户角色编号长度超出限制")
    private String userRoleId;
    public static final String USER_ROLE_ID = "userRoleId";

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
     * 角色编号
     */
    @Field
    @TableField
    @NotNull(message = "角色编号不能为空")
    @Length(max = 32, message = "角色编号长度超出限制")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 用户类型
     */
    @Field
    @TableField
    @NotNull(message = "用户类型不能为空")
    @Length(max = 25, message = "用户类型长度超出限制")
    private String userType;
    public static final String USER_TYPE = "userType";


    public String getUserRoleId() {
        return getString(USER_ROLE_ID);
    }

    public void setUserRoleId(String userRoleId) {
        put(USER_ROLE_ID, userRoleId);
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
    
    public String getRoleId() {
        return getString(ROLE_ID);
    }

    public void setRoleId(String roleId) {
        put(ROLE_ID, roleId);
    }
    
    public String getUserType() {
        return getString(USER_TYPE);
    }

    public void setUserType(String userType) {
        put(USER_TYPE, userType);
    }

}