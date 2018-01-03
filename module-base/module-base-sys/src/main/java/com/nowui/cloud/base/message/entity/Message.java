package com.nowui.cloud.base.message.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 消息
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component
@TableName(value = "message_info")
public class Message extends BaseEntity {

    /**
     * 消息编号
     */
    @TableId
    @NotNull(message = "消息编号不能为空")
    @Length(max = 32, message = "消息编号长度超出限制")
    private String messageId;
    public static final String MESSAGE_ID = "messageId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 业务编号
     */
    @TableField
    @NotNull(message = "业务编号不能为空")
    @Length(max = 32, message = "业务编号长度超出限制")
    private String objectId;
    public static final String OBJECT_ID = "objectId";

    /**
     * 标题
     */
    @TableField
    @NotNull(message = "标题不能为空")
    @Length(max = 200, message = "标题长度超出限制")
    private String messageTitle;
    public static final String MESSAGE_TITLE = "messageTitle";

    /**
     * 类型
     */
    @TableField
    @NotNull(message = "类型不能为空")
    @Length(max = 25, message = "类型长度超出限制")
    private String messageType;
    public static final String MESSAGE_TYPE = "messageType";

    /**
     * 内容
     */
    @TableField
    @NotNull(message = "内容不能为空")
    @Length(max = 0, message = "内容长度超出限制")
    private String messageContent;
    public static final String MESSAGE_CONTENT = "messageContent";


    public String getMessageId() {
        return getString(MESSAGE_ID);
    }

    public void setMessageId(String messageId) {
        put(MESSAGE_ID, messageId);
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
    
    public String getMessageTitle() {
        return getString(MESSAGE_TITLE);
    }

    public void setMessageTitle(String messageTitle) {
        put(MESSAGE_TITLE, messageTitle);
    }
    
    public String getMessageType() {
        return getString(MESSAGE_TYPE);
    }

    public void setMessageType(String messageType) {
        put(MESSAGE_TYPE, messageType);
    }
    
    public String getMessageContent() {
        return getString(MESSAGE_CONTENT);
    }

    public void setMessageContent(String messageContent) {
        put(MESSAGE_CONTENT, messageContent);
    }

}