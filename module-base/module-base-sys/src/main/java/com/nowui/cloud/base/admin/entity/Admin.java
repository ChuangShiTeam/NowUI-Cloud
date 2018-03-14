package com.nowui.cloud.base.admin.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 管理员
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component
@TableName(value = "admin_info")
public class Admin extends BaseEntity {

    /**
     * 管理员编号
     */
    @TableId
    private String adminId;
    
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

}