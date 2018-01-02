package com.nowui.cloud.base.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "管理员编号不能为空")
    @Length(max = 32, message = "管理员编号长度超出限制")
    private String adminId;
    public static final String ADMIN_ID = "adminId";

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