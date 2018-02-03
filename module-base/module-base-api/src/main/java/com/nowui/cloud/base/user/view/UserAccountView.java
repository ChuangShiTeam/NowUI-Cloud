package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 用户账号视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_account_info")
public class UserAccountView extends BaseView {

    /**
     * 用户账号编号
     */
    @Field
    @NotNull(message = "用户账号编号不能为空")
    private String userAccountId;
    public static final String USER_ACCOUNT_ID = "userAccountId";

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

    /**
     * 用户账号
     */
    @Field
    @NotNull(message = "用户账号不能为空")
    private String userAccount;
    public static final String USER_ACCOUNT = "userAccount";

    public String getUserAccountId() {
        return getString(USER_ACCOUNT_ID);
    }

    public void setUserAccountId(String userAccountId) {
        put(USER_ACCOUNT_ID, userAccountId);
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

    public String getUserAccount() {
        return getString(USER_ACCOUNT);
    }

    public void setUserAccount(String userAccount) {
        put(USER_ACCOUNT, userAccount);
    }


}