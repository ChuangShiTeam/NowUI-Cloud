package com.nowui.cloud.base.subscriptioninfo.entity;

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
 * 用户订阅表
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component
@Document(indexName = "nowui", type = "subscription_info")
@TableName(value = "subscription_info")
public class Subscription extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @TableId
    @NotNull(message = "主键不能为空")
    @Length(max = 32, message = "主键长度超出限制")
    private String subscriptionId;
    public static final String SUBSCRIPTION_ID = "subscriptionId";

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
     * 目标的Id
     */
    @Field
    @TableField
    @NotNull(message = "目标的Id不能为空")
    @Length(max = 32, message = "目标的Id长度超出限制")
    private String subscriptionTarget;
    public static final String SUBSCRIPTION_TARGET = "subscriptionTarget";

    /**
     * 目标的类型
     */
    @Field
    @TableField
    @NotNull(message = "目标的类型不能为空")
    @Length(max = 32, message = "目标的类型长度超出限制")
    private String subscriptionTargetType;
    public static final String SUBSCRIPTION_TARGET_TYPE = "subscriptionTargetType";

    /**
     * 订阅动作（订阅动作：comment/like/post/update）
     */
    @Field
    @TableField
    @NotNull(message = "订阅动作（订阅动作：comment/like/post/update）不能为空")
    @Length(max = 32, message = "订阅动作（订阅动作：comment/like/post/update）长度超出限制")
    private String subscriptionAction;
    public static final String SUBSCRIPTION_ACTION = "subscriptionAction";

    /**
     * 订阅人
     */
    @Field
    @TableField
    @NotNull(message = "订阅人不能为空")
    @Length(max = 32, message = "订阅人长度超出限制")
    private String subscriptionUser;
    public static final String SUBSCRIPTION_USER = "subscriptionUser";


    public String getSubscriptionId() {
        return getString(SUBSCRIPTION_ID);
    }
    
    public void setSubscriptionId(String subscriptionId) {
        put(SUBSCRIPTION_ID, subscriptionId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getSubscriptionTarget() {
        return getString(SUBSCRIPTION_TARGET);
    }
    
    public void setSubscriptionTarget(String subscriptionTarget) {
        put(SUBSCRIPTION_TARGET, subscriptionTarget);
    }

    public String getSubscriptionTargetType() {
        return getString(SUBSCRIPTION_TARGET_TYPE);
    }
    
    public void setSubscriptionTargetType(String subscriptionTargetType) {
        put(SUBSCRIPTION_TARGET_TYPE, subscriptionTargetType);
    }

    public String getSubscriptionAction() {
        return getString(SUBSCRIPTION_ACTION);
    }
    
    public void setSubscriptionAction(String subscriptionAction) {
        put(SUBSCRIPTION_ACTION, subscriptionAction);
    }

    public String getSubscriptionUser() {
        return getString(SUBSCRIPTION_USER);
    }
    
    public void setSubscriptionUser(String subscriptionUser) {
        put(SUBSCRIPTION_USER, subscriptionUser);
    }


}