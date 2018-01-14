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

    public static final String USER_ACCOUNT = "userAccount";
    
    public static final String USER_PASSWORD = "userPassword";

    public static final String USER_NICK_NAME = "userNickName";

    public static final String USER_IDCARD = "userIdcard";

    public static final String USER_MOBILE = "userMobile";

    public static final String USER_EMAIL = "userEmail";

    public static final String USER_AVATAR = "userAvatar";
    
    public static final String USER_WECHAT = "userWechat";


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
    
}