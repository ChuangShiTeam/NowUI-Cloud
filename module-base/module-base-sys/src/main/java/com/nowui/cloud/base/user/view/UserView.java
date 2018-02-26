package com.nowui.cloud.base.user.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 用户视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "user_info")
public class UserView extends BaseView {

    /**
     * 用户编号
     */
    @KeyId
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户主体编号（会员、管理员、员工等编号）
     */
    @Field
    @NotNull(message = "用户主体编号不能为空")
    private String objectId;
    public static final String OBJECT_ID = "objectId";

    /**
     * 类型
     */
    @Field
    @NotNull(message = "类型不能为空")
    private String userType;
    public static final String USER_TYPE = "userType";
    
    /**
     * 用户账号
     */
    @Field
    private String userAccount;
    public static final String USER_ACCOUNT = "userAccount";
    
    /**
     * 用户头像文件编号
     */
    @Field
    private String userAvatar;
    public static final String USER_AVATAR = "userAvatar";
    
    /**
     * 用户头像文件路径
     */
    @Field
    private String userAvatarPath;
    public static final String USER_AVATAR_PATH = "userAvatarPath";
    
    /**
     * 用户邮箱
     */
    @Field
    private String userEmail;
    public static final String USER_EMAIL = "userEmail";
    
    /**
     * 用户性别
     */
    @Field
    private String userSex;
    public static final String USER_SEX = "userSex";
    
    /**
     * 用户手机号码
     */
    @Field
    private String userMobile;
    public static final String USER_MOBILE = "userMobile";
    
    /**
     * 用户昵称
     */
    @Field
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 用户密码
     */
    @Field
    private String userPassword;
    public static final String USER_PASSWORD = "userPassword";
    
    /**
     * 微信用户唯一标识
     */
    @Field
    private String wechatOpenId;
    public static final String WECHAT_OPEN_ID = "wechatOpenId";

    /**
     * 微信多应用用户共享唯一标识
     */
    @Field
    private String wechatUnionId;
    public static final String WECHAT_UNION_ID = "wechatUnionId";

    /**
     * 微信昵称
     */
    @Field
    private String wechatNickName;
    public static final String WECHAT_NICK_NAME = "wechatNickName";

    /**
     * 微信性别
     */
    @Field
    private String wechatSex;
    public static final String WECHAT_SEX = "wechatSex";

    /**
     * 微信国家
     */
    @Field
    private String wechatCountry;
    public static final String WECHAT_COUNTRY = "wechatCountry";

    /**
     * 微信省
     */
    @Field
    private String wechatProvince;
    public static final String WECHAT_PROVINCE = "wechatProvince";

    /**
     * 微信市
     */
    @Field
    private String wechatCity;
    public static final String WECHAT_CITY = "wechatCity";

    /**
     * 微信头像本地文件ID
     */
    @Field
    private String wechatHeadImgFileId;
    public static final String WECHAT_HEAD_IMG_FILE_ID = "wechatHeadImgFileId";

    /**
     * 微信头像地址
     */
    @Field
    private String wechatHeadImgUrl;
    public static final String WECHAT_HEAD_IMG_URL = "wechatHeadImgUrl";

    /**
     * 微信用户特权
     */
    @Field
    private String wehchatPrivilege;
    public static final String WEHCHAT_PRIVILEGE = "wehchatPrivilege";

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getObjectId() {
        return getString(OBJECT_ID);
    }

    public void setObjectId(String objectId) {
        put(OBJECT_ID, objectId);
    }

    public String getUserType() {
        return getString(USER_TYPE);
    }

    public void setUserType(String userType) {
        put(USER_TYPE, userType);
    }

    public String getUserAccount() {
        return getString(USER_ACCOUNT);
    }

    public void setUserAccount(String userAccount) {
        put(USER_ACCOUNT, userAccount);
    }

    public String getUserAvatar() {
        return getString(USER_AVATAR);
    }

    public void setUserAvatar(String userAvatar) {
        put(USER_AVATAR, userAvatar);
    }
    
    public String getUserAvatarPath() {
        return getString(USER_AVATAR_PATH);
    }
    
    public void setUserAvatarPath(String userAvatarPath) {
        put(USER_AVATAR_PATH, userAvatarPath);
    }

    public String getUserEmail() {
        return getString(USER_EMAIL);
    }

    public void setUserEmail(String userEmail) {
        put(USER_EMAIL, userEmail);
    }

    public String getUserSex() {
        return getString(USER_SEX);
    }

    public void setUserSex(String userSex) {
        put(USER_SEX, userSex);
    }

    public String getUserMobile() {
        return getString(USER_MOBILE);
    }

    public void setUserMobile(String userMobile) {
        put(USER_MOBILE, userMobile);
    }

    public String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
    }

    public String getUserPassword() {
        return getString(USER_PASSWORD);
    }

    public void setUserPassword(String userPassword) {
        put(USER_PASSWORD, userPassword);
    }
    
    public String getWechatOpenId() {
        return getString(WECHAT_OPEN_ID);
    }

    public void setWechatOpenId(String wechatOpenId) {
        put(WECHAT_OPEN_ID, wechatOpenId);
    }

    public String getWechatUnionId() {
        return getString(WECHAT_UNION_ID);
    }

    public void setWechatUnionId(String wechatUnionId) {
        put(WECHAT_UNION_ID, wechatUnionId);
    }

    public String getWechatNickName() {
        return getString(WECHAT_NICK_NAME);
    }

    public void setWechatNickName(String wechatNickName) {
        put(WECHAT_NICK_NAME, wechatNickName);
    }

    public String getWechatSex() {
        return getString(WECHAT_SEX);
    }

    public void setWechatSex(String wechatSex) {
        put(WECHAT_SEX, wechatSex);
    }

    public String getWechatCountry() {
        return getString(WECHAT_COUNTRY);
    }

    public void setWechatCountry(String wechatCountry) {
        put(WECHAT_COUNTRY, wechatCountry);
    }

    public String getWechatProvince() {
        return getString(WECHAT_PROVINCE);
    }

    public void setWechatProvince(String wechatProvince) {
        put(WECHAT_PROVINCE, wechatProvince);
    }

    public String getWechatCity() {
        return getString(WECHAT_CITY);
    }

    public void setWechatCity(String wechatCity) {
        put(WECHAT_CITY, wechatCity);
    }

    public String getWechatHeadImgFileId() {
        return getString(WECHAT_HEAD_IMG_FILE_ID);
    }

    public void setWechatHeadImgFileId(String wechatHeadImgFileId) {
        put(WECHAT_HEAD_IMG_FILE_ID, wechatHeadImgFileId);
    }

    public String getWechatHeadImgUrl() {
        return getString(WECHAT_HEAD_IMG_URL);
    }

    public void setWechatHeadImgUrl(String wechatHeadImgUrl) {
        put(WECHAT_HEAD_IMG_URL, wechatHeadImgUrl);
    }

    public String getWehchatPrivilege() {
        return getString(WEHCHAT_PRIVILEGE);
    }

    public void setWehchatPrivilege(String wehchatPrivilege) {
        put(WEHCHAT_PRIVILEGE, wehchatPrivilege);
    }

}