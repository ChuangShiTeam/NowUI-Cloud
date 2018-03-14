package com.nowui.cloud.base.admin.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 管理员密码视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "admin_password_info")
public class AdminPasswordView extends BaseView {
    
    /**
     * 管理员账号编号
     */
    @KeyId
    @Field
    @NotNull(message = "管理员账号编号不能为空")
    @Length(max = 32, message = "管理员账号编号长度超出限制")
    private String adminAccountId;
    public static final String ADMIN_ACCOUNT_ID = "adminAccountId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 管理员编号
     */
    @Field
    @NotNull(message = "管理员编号不能为空")
    @Length(max = 32, message = "管理员编号长度超出限制")
    private String adminId;
    public static final String ADMIN_ID = "adminId";

    /**
     * 管理员密码
     */
    @Field
    @NotNull(message = "管理员密码不能为空")
    @Length(max = 128, message = "管理员密码长度超出限制")
    private String adminPassword;
    public static final String ADMIN_PASSWORD = "adminPassword";
    
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
    
    public String getAdminPassword() {
        return adminPassword;
    }
    
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
