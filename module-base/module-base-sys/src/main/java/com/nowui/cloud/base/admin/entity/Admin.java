package com.nowui.cloud.base.admin.entity;

import org.springframework.data.annotation.Id;
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
    @Id
    @TableId
    private String adminId;
    public static final String ADMIN_ID = "adminId";

    /**
     * 应用编号
     */
    @TableField
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @TableField
    private String userId;
    public static final String USER_ID = "userId";


    public String getAdminId() {
        return getString(ADMIN_ID);
    }

    public void setAdminId(String adminId) {
        put(ADMIN_ID, adminId);
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

}