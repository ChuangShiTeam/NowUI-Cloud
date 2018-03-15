package com.nowui.cloud.base.user.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户角色视图
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component
@Document(collection = "user_role_map")
public class UserRoleView extends BaseView {

    /**
     * 用户角色编号
     */
    @KeyId
    @Field
    @NotNull(message = "用户角色编号不能为空")
    @Length(max = 32, message = "用户角色编号长度超出限制")
    private String userRoleId;
    public static final String USER_ROLE_ID = "userRoleId";

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
     * 角色编号
     */
    @Field
    @NotNull(message = "角色编号不能为空")
    @Length(max = 32, message = "角色编号长度超出限制")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 用户类型
     */
    @Field
    @NotNull(message = "用户类型不能为空")
    @Length(max = 25, message = "用户类型长度超出限制")
    private String userType;
    public static final String USER_TYPE = "userType";


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