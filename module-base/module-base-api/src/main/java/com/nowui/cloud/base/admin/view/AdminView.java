package com.nowui.cloud.base.admin.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 管理员视图
 *
 * @author ZhongYongQiang
 *
 * 2018-02-02
 */
@Component
@Document(collection = "admin_info")
public class AdminView extends BaseView {

    /**
     * 管理员编号
     */
    @Id
    private String adminId;
    public static final String ADMIN_ID = "adminId";

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