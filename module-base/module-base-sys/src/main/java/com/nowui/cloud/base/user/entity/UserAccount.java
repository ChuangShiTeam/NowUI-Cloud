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
 * 用户账号
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "user_account_info")
@TableName(value = "user_account_info")
public class UserAccount extends BaseEntity {

    /**
     * 用户账号编号
     */
    @Id
    @TableId
    @NotNull(message = "用户账号编号不能为空")
    @Length(max = 32, message = "用户账号编号长度超出限制")
    private String userAccountId;
    public static final String USER_ACCOUNT_ID = "userAccountId";

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
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 用户账号
     */
    @Field
    @TableField
    @NotNull(message = "用户账号不能为空")
    @Length(max = 30, message = "用户账号长度超出限制")
    private String userAccount;
    public static final String USER_ACCOUNT = "userAccount";

    /**
     * 用户密码
     */
    @Field
    @TableField
    @NotNull(message = "用户密码不能为空")
    @Length(max = 128, message = "用户密码长度超出限制")
    private String userPassword;
    public static final String USER_PASSWORD = "userPassword";


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

    public String getUserPassword() {
        return getString(USER_PASSWORD);
    }
    
    public void setUserPassword(String userPassword) {
        put(USER_PASSWORD, userPassword);
    }


}