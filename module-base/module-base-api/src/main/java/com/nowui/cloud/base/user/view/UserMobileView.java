package com.nowui.cloud.base.user.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 	用户手机号码视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "user_mobile_info")
public class UserMobileView extends BaseView {

    /**
     * 用户手机号码编号
     */
    @Id
    private String userMobileId;
    public static final String USER_MOBILE_ID = "userMobileId";

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
     * 手机号码
     */
    @Field
    private String userMobile;
    public static final String USER_MOBILE = "userMobile";


    public String getUserMobileId() {
        return getString(USER_MOBILE_ID);
    }

    public void setUserMobileId(String userMobileId) {
        put(USER_MOBILE_ID, userMobileId);
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

    public String getUserMobile() {
        return getString(USER_MOBILE);
    }

    public void setUserMobile(String userMobile) {
        put(USER_MOBILE, userMobile);
    }


}