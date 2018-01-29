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
 * 用户身份证
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "user_idcard_info")
@TableName(value = "user_idcard_info")
public class UserIdcard extends BaseEntity {

    /**
     * 用户身份证编号
     */
    @Id
    @TableId
    @NotNull(message = "用户身份证编号不能为空")
    @Length(max = 32, message = "用户身份证编号长度超出限制")
    private String userIdcardId;
    public static final String USER_IDCARD_ID = "userIdcardId";

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
     * 真实姓名
     */
    @Field
    @TableField
    @NotNull(message = "真实姓名不能为空")
    @Length(max = 50, message = "真实姓名长度超出限制")
    private String userName;
    public static final String USER_NAME = "userName";
    
    /**
     * 性别    （0：未知，1：男，2：女）
     */
    @Field
    @TableField
    @NotNull(message = "性别不能为空")
    @Length(max = 1, message = "性别长度超出限制")
    private String userSex;
    public static final String USER_SEX = "userSex";

    /**
     * 身份证号码
     */
    @Field
    @TableField
    @NotNull(message = "身份证号码不能为空")
    @Length(max = 18, message = "身份证号码长度超出限制")
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