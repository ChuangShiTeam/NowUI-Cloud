package com.nowui.cloud.base.admin.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 管理员账号
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "admin_account_info")
public class AdminAccount extends BaseEntity {

    /**
     * 管理员账号编号
     */
    @TableId
    @TableField
    private String adminAccountId;

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
     * 管理员账号
     */
    @TableField
    private String adminAccount;

    public String getAdminAccountId() {
        return adminAccountId;
    }

    public void setAdminAccountId(String adminAccountId) {
        this.adminAccountId = adminAccountId;
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

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

}