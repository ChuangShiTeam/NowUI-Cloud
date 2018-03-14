package com.nowui.cloud.base.admin.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 管理员密码
 *
 * @author marcus
 *
 * 2018-01-11
 */
@Component
@TableName(value = "admin_password_info")
public class AdminPassword extends BaseEntity {

    /**
     * 管理员密码编号
     */
    @TableId
    private String adminPasswordId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 管理员编号
     */
    @TableField
    private String adminId;

    /**
     * 管理员密码
     */
    @TableField
    private String adminPassword;

    public String getAdminPasswordId() {
        return adminPasswordId;
    }

    public void setAdminPasswordId(String adminPasswordId) {
        this.adminPasswordId = adminPasswordId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}