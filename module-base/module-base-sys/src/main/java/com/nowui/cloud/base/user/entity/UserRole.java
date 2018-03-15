package com.nowui.cloud.base.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 用户角色
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component

@TableName(value = "user_role_map")
public class UserRole extends BaseEntity {

    /**
     * 用户角色编号
     */
    @TableId
    @TableField
    private String userRoleId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 用户编号
     */
    @TableField
    private String userId;

    /**
     * 角色编号
     */
    @TableField
    private String roleId;

    /**
     * 用户类型
     */
    @TableField
    private String userType;


    public String getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }


}