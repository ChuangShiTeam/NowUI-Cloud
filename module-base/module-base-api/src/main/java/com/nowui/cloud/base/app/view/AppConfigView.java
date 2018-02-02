package com.nowui.cloud.base.app.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 应用配置视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "app_config_info")
public class AppConfigView extends BaseView {

    /**
     * 配置编号
     */
    @Id
    private String configId;
    public static final String CONFIG_ID = "configId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 配置分类编号
     */
    @Field
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";

    /**
     * 键
     */
    @Field
    private String configKey;
    public static final String CONFIG_KEY = "configKey";

    /**
     * 值
     */
    @Field
    private String configValue;
    public static final String CONFIG_VALUE = "configValue";

    /**
     * 是否禁用
     */
    @Field
    private Boolean configIsDisabled;
    public static final String CONFIG_IS_DISABLED = "configIsDisabled";

    /**
     * 描述
     */
    @Field
    private String configDescription;
    public static final String CONFIG_DESCRIPTION = "configDescription";


    public String getConfigId() {
        return getString(CONFIG_ID);
    }

    public void setConfigId(String configId) {
        put(CONFIG_ID, configId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getConfigCategoryId() {
        return getString(CONFIG_CATEGORY_ID);
    }

    public void setConfigCategoryId(String configCategoryId) {
        put(CONFIG_CATEGORY_ID, configCategoryId);
    }

    public String getConfigKey() {
        return getString(CONFIG_KEY);
    }

    public void setConfigKey(String configKey) {
        put(CONFIG_KEY, configKey);
    }

    public String getConfigValue() {
        return getString(CONFIG_VALUE);
    }

    public void setConfigValue(String configValue) {
        put(CONFIG_VALUE, configValue);
    }

    public Boolean getConfigIsDisabled() {
        return getBoolean(CONFIG_IS_DISABLED);
    }

    public void setConfigIsDisabled(Boolean configIsDisabled) {
        put(CONFIG_IS_DISABLED, configIsDisabled);
    }

    public String getConfigDescription() {
        return getString(CONFIG_DESCRIPTION);
    }

    public void setConfigDescription(String configDescription) {
        put(CONFIG_DESCRIPTION, configDescription);
    }


}