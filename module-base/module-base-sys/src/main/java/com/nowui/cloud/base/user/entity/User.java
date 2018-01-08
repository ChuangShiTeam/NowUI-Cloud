package com.nowui.cloud.base.user.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 用户
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@Document(indexName = "nowui", type = "user_info")
@TableName(value = "user_info")
public class User extends BaseEntity {

    /**
     * 用户编号
     */
    @Id
    @TableId
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户主体编号（会员、管理员、员工等编号）
     */
    @Field
    @TableField
    @NotNull(message = "用户主体编号（会员、管理员、员工等编号）不能为空")
    @Length(max = 32, message = "用户主体编号（会员、管理员、员工等编号）长度超出限制")
    private String objectId;
    public static final String OBJECT_ID = "objectId";

    /**
     * 类型
     */
    @Field
    @TableField
    @NotNull(message = "类型不能为空")
    @Length(max = 25, message = "类型长度超出限制")
    private String userType;
    public static final String USER_TYPE = "userType";

    /**
     * 账号
     */
    @Field
    @TableField
    @NotNull(message = "账号不能为空")
    @Length(max = 30, message = "账号长度超出限制")
    private String userAccount;
    public static final String USER_ACCOUNT = "userAccount";

    /**
     * 密码
     */
    @Field
    @TableField
    @NotNull(message = "密码不能为空")
    @Length(max = 128, message = "密码长度超出限制")
    private String userPassword;
    public static final String USER_PASSWORD = "userPassword";

    /**
     * 昵称
     */
    @Field
    @TableField
    @NotNull(message = "昵称不能为空")
    @Length(max = 100, message = "昵称长度超出限制")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";

    /**
     * 姓名
     */
    @Field
    @TableField
    @NotNull(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度超出限制")
    private String userName;
    public static final String USER_NAME = "userName";

    /**
     * 手机号码
     */
    @Field
    @TableField
    @NotNull(message = "手机号码不能为空")
    @Length(max = 11, message = "手机号码长度超出限制")
    private String userMobile;
    public static final String USER_MOBILE = "userMobile";

    /**
     * 邮箱
     */
    @Field
    @TableField
    @NotNull(message = "邮箱不能为空")
    @Length(max = 20, message = "邮箱长度超出限制")
    private String userEmail;
    public static final String USER_EMAIL = "userEmail";

    /**
     * 头像
     */
    @Field
    @TableField
    @NotNull(message = "头像不能为空")
    @Length(max = 32, message = "头像长度超出限制")
    private String userAvatar;
    public static final String USER_AVATAR = "userAvatar";

    /**
     * 微信openID
     */
    @Field
    @TableField
    @NotNull(message = "微信openID不能为空")
    @Length(max = 50, message = "微信openID长度超出限制")
    private String weixinOpenId;
    public static final String WEIXIN_OPEN_ID = "weixinOpenId";

    /**
     * 微信unionID
     */
    @Field
    @TableField
    @NotNull(message = "微信unionID不能为空")
    @Length(max = 50, message = "微信unionID长度超出限制")
    private String weixinUnionId;
    public static final String WEIXIN_UNION_ID = "weixinUnionId";


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
    
    public String getUserPassword() {
        return getString(USER_PASSWORD);
    }

    public void setUserPassword(String userPassword) {
        put(USER_PASSWORD, userPassword);
    }
    
    public String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
    }
    
    public String getUserName() {
        return getString(USER_NAME);
    }

    public void setUserName(String userName) {
        put(USER_NAME, userName);
    }
    
    public String getUserMobile() {
        return getString(USER_MOBILE);
    }

    public void setUserMobile(String userMobile) {
        put(USER_MOBILE, userMobile);
    }
    
    public String getUserEmail() {
        return getString(USER_EMAIL);
    }

    public void setUserEmail(String userEmail) {
        put(USER_EMAIL, userEmail);
    }
    
    public String getUserAvatar() {
        return getString(USER_AVATAR);
    }

    public void setUserAvatar(String userAvatar) {
        put(USER_AVATAR, userAvatar);
    }
    
    public String getWeixinOpenId() {
        return getString(WEIXIN_OPEN_ID);
    }

    public void setWeixinOpenId(String weixinOpenId) {
        put(WEIXIN_OPEN_ID, weixinOpenId);
    }
    
    public String getWeixinUnionId() {
        return getString(WEIXIN_UNION_ID);
    }

    public void setWeixinUnionId(String weixinUnionId) {
        put(WEIXIN_UNION_ID, weixinUnionId);
    }

}