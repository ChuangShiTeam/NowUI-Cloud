package com.nowui.cloud.event.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 消息
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
@Document(indexName = "nowui", type = "event_info")
@TableName(value = "event_info")
public class Event extends BaseEntity {

    /**
     * 事件编号
     */
    @Id
    @TableId
    @NotNull(message = "事件编号不能为空")
    @Length(max = 32, message = "事件编号长度超出限制")
    private String eventId;
    public static final String EVENT_ID = "eventId";

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
     * 事件名称
     */
    @Field
    @TableField
    @NotNull(message = "事件名称不能为空")
    @Length(max = 200, message = "事件名称长度超出限制")
    private String eventRouting;
    public static final String EVENT_ROUTING = "eventRouting";

    /**
     * 事件内容
     */
    @Field
    @TableField
    @NotNull(message = "事件内容不能为空")
    @Length(max = 0, message = "事件内容长度超出限制")
    private String eventMessage;
    public static final String EVENT_MESSAGE = "eventMessage";


    public String getEventId() {
        return getString(EVENT_ID);
    }

    public void setEventId(String eventId) {
        put(EVENT_ID, eventId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getEventRouting() {
        return getString(EVENT_ROUTING);
    }

    public void setEventRouting(String eventRouting) {
        put(EVENT_ROUTING, eventRouting);
    }

    public String getEventMessage() {
        return getString(EVENT_MESSAGE);
    }

    public void setEventMessage(String eventMessage) {
        put(EVENT_MESSAGE, eventMessage);
    }

}