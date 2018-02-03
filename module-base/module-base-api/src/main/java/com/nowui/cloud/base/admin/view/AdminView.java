package com.nowui.cloud.base.admin.view;

import com.nowui.cloud.view.BaseView;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
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
    @Field
    @NotNull(message = "管理员编号不能为空")
    private String adminId;
    public static final String ADMIN_ID = "adminId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
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