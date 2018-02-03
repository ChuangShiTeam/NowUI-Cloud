package com.nowui.cloud.base.subscription.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 订阅视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "subscription_info")
public class SubscriptionView extends BaseView {

    /**
     * 订阅编号
     */
    @Field
    @NotNull(message = "订阅编号不能为空")
    private String subscriptionId;
    public static final String SUBSCRIPTION_ID = "subscriptionId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 目标编号
     */
    @Field
    @NotNull(message = "目标编号不能为空")
    private String subscriptionTarget;
    public static final String SUBSCRIPTION_TARGET = "subscriptionTarget";

    /**
     * 目标类型
     */
    @Field
    @NotNull(message = "目标类型不能为空")
    private String subscriptionTargetType;
    public static final String SUBSCRIPTION_TARGET_TYPE = "subscriptionTargetType";

    /**
     * 订阅动作（订阅动作：comment/like/post/update）
     */
    @Field
    @NotNull(message = "订阅动作（订阅动作：comment/like/post/update）不能为空")
    private String subscriptionAction;
    public static final String SUBSCRIPTION_ACTION = "subscriptionAction";

    /**
     * 订阅人
     */
    @Field
    @NotNull(message = "订阅人不能为空")
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