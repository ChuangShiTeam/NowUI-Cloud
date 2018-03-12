package com.nowui.cloud.event.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
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

@TableName(value = "event_info")
public class Event extends BaseEntity {

    /**
     * 事件编号
     */
    @Id
    @TableId
    private String eventId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 事件名称
     */
    @TableField
    private String eventRouting;

    /**
     * 事件内容
     */
    @TableField
    private String eventMessage;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEventRouting() {
        return eventRouting;
    }

    public void setEventRouting(String eventRouting) {
        this.eventRouting = eventRouting;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}