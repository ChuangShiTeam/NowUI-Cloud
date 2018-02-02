package com.nowui.cloud.base.user.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户身份证视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "user_idcard_info")
public class UserIdcardView extends BaseView {

    /**
     * 用户身份证编号
     */
    @Id
    private String userIdcardId;
    public static final String USER_IDCARD_ID = "userIdcardId";

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
     * 真实姓名
     */
    @Field
    private String userName;
    public static final String USER_NAME = "userName";

    /**
     * 性别
     */
    @Field
    private String userSex;
    public static final String USER_SEX = "userSex";

    /**
     * 身份证号码
     */
    @Field
    private String userIdcardNumber;
    public static final String USER_IDCARD_NUMBER = "userIdcardNumber";


    public String getUserIdcardId() {
        return getString(USER_IDCARD_ID);
    }

    public void setUserIdcardId(String userIdcardId) {
        put(USER_IDCARD_ID, userIdcardId);
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

    public String getUserName() {
        return getString(USER_NAME);
    }

    public void setUserName(String userName) {
        put(USER_NAME, userName);
    }

    public String getUserSex() {
        return getString(USER_SEX);
    }

    public void setUserSex(String userSex) {
        put(USER_SEX, userSex);
    }

    public String getUserIdcardNumber() {
        return getString(USER_IDCARD_NUMBER);
    }

    public void setUserIdcardNumber(String userIdcardNumber) {
        put(USER_IDCARD_NUMBER, userIdcardNumber);
    }


}