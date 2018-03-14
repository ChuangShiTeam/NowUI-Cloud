package com.nowui.cloud.base.admin.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 管理员视图
 *
 * @author marcus
 *
 * 2018-02-03
 */
@Component
@Document(collection = "admin_info")
public class AdminView extends BaseView {

    /**
     * 管理员编号
     */
    @KeyId
    @Field
    @NotNull(message = "管理员编号不能为空")
    @Length(max = 32, message = "管理员编号长度超出限制")
    private String adminId;
    public static final String ADMIN_ID = "adminId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";
    
    /**
     * 管理员账号
     */
    @Field
    @NotNull(message = "管理员账号不能为空")
    @Length(max = 30, message = "管理员账号长度超出限制")
    private String adminAccount;
    public static final String ADMIN_ACCOUNT = "adminAccount";
    
    /**
     * 管理员密码
     */
    @Field
    @NotNull(message = "管理员密码不能为空")
    @Length(max = 128, message = "管理员密码长度超出限制")
    private String adminPassword;
    public static final String ADMIN_PASSWORD = "adminPassword";
    
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
    
    public String getAdminAccount() {
        return adminAccount;
    }
    
    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }
    
    public String getAdminPassword() {
        return adminPassword;
    }
    
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}