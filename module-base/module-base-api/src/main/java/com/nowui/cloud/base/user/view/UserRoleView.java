package com.nowui.cloud.base.user.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 	用户角色视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "user_role_map")
public class UserRoleView extends BaseView {

    /**
     * 用户角色编号
     */
    @Id
    private String userRoleId;
    public static final String USER_ROLE_ID = "userRoleId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 角色编号
     */
    @Field
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 用户类型
     */
    @Field
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