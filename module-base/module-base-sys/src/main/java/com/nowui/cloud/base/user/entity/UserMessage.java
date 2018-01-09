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
 * 用户消息
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "user_message_info")
@TableName(value = "user_message_info")
public class UserMessage extends BaseEntity {

    /**
     * 用户消息编号
     */
    @Id
    @TableId
    @NotNull(message = "用户消息编号不能为空")
    @Length(max = 32, message = "用户消息编号长度超出限制")
    private String userMessageId;
    public static final String USER_MESSAGE_ID = "userMessageId";

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
     * 消息编号
     */
    @Field
    @TableField
    @NotNull(message = "消息编号不能为空")
    @Length(max = 32, message = "消息编号长度超出限制")
    private String messageId;
    public static final String MESSAGE_ID = "messageId";

    /**
     * 是否已读
     */
    @Field
    @TableField
    @NotNull(message = "是否已读不能为空")
    @Length(max = 1, message = "是否已读长度超出限制")
    private Boolean userMessageIsRead;
    public static final String USER_MESSAGE_IS_READ = "userMessageIsRead";


    public String getUserMessageId() {
        return getString(USER_MESSAGE_ID);
    }
    
    public void setUserMessageId(String userMessageId) {
        put(USER_MESSAGE_ID, userMessageId);
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

    public String getMessageId() {
        return getString(MESSAGE_ID);
    }
    
    public void setMessageId(String messageId) {
        put(MESSAGE_ID, messageId);
    }

    public Boolean getUserMessageIsRead() {
        return getBoolean(USER_MESSAGE_IS_READ);
    }
    
    public void setUserMessageIsRead(Boolean userMessageIsRead) {
        put(USER_MESSAGE_IS_READ, userMessageIsRead);
    }


}