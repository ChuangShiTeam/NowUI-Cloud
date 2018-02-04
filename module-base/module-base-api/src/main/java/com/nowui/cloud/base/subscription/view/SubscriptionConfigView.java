package com.nowui.cloud.base.subscription.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 订阅配置表视图
 *
 * @author shawn
 *
 * 2018-02-04
 */
@Component
@Document(collection = "subscription_config_info")
public class SubscriptionConfigView extends BaseView {

    /**
     * 订阅配置Id
     */
    @Field
    @NotNull(message = "订阅配置Id不能为空")
    private String subscriptionConfigId;
    public static final String SUBSCRIPTION_CONFIG_ID = "subscriptionConfigId";

    /**
     * 应用Id
     */
    @Field
    @NotNull(message = "应用Id不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户的设置
     */
    @Field
    @NotNull(message = "用户的设置不能为空")
    private String subscriptionAction;
    public static final String SUBSCRIPTION_ACTION = "subscriptionAction";

    /**
     * 订阅用户Id
     */
    @Field
    @NotNull(message = "订阅用户Id不能为空")
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