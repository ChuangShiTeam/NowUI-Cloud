package com.nowui.cloud.base.subscriptionconfiginfo.entity;

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
 * 订阅配置
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component
@Document(indexName = "nowui", type = "subscription_config_info")
@TableName(value = "subscription_config_info")
public class SubscriptionConfig extends BaseEntity {

    /**
     * 订阅配置Id
     */
    @Id
    @TableId
    @NotNull(message = "订阅配置Id不能为空")
    @Length(max = 32, message = "订阅配置Id长度超出限制")
    private String subscriptionConfigId;
    public static final String SUBSCRIPTION_CONFIG_ID = "subscriptionConfigId";

    /**
     * 应用Id
     */
    @Field
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户的设置
     */
    @Field
    @TableField
    @NotNull(message = "用户的设置不能为空")
    @Length(max = 32, message = "用户的设置长度超出限制")
    private String subscriptionAction;
    public static final String SUBSCRIPTION_ACTION = "subscriptionAction";

    /**
     * 订阅用户Id
     */
    @Field
    @TableField
    @NotNull(message = "订阅用户Id不能为空")
    @Length(max = 32, message = "订阅用户Id长度超出限制")
    private String subscriptionUserId;
    public static final String SUBSCRIPTION_USER_ID = "subscriptionUserId";


    public String getSubscriptionConfigId() {
        return getString(SUBSCRIPTION_CONFIG_ID);
    }
    
    public void setSubscriptionConfigId(String subscriptionConfigId) {
        put(SUBSCRIPTION_CONFIG_ID, subscriptionConfigId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getSubscriptionAction() {
        return getString(SUBSCRIPTION_ACTION);
    }
    
    public void setSubscriptionAction(String subscriptionAction) {
        put(SUBSCRIPTION_ACTION, subscriptionAction);
    }

    public String getSubscriptionUserId() {
        return getString(SUBSCRIPTION_USER_ID);
    }
    
    public void setSubscriptionUserId(String subscriptionUserId) {
        put(SUBSCRIPTION_USER_ID, subscriptionUserId);
    }


}