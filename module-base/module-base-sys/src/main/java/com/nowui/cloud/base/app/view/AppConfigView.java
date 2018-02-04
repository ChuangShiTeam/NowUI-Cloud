package com.nowui.cloud.base.app.view;

import javax.validation.constraints.NotNull;

import com.nowui.cloud.annotation.KeyId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 应用配置视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "app_config_info")
public class AppConfigView extends BaseView {

    /**
     * 配置编号
     */
    @KeyId
    @Field
    @NotNull(message = "配置编号不能为空")
    private String configId;
    public static final String CONFIG_ID = "configId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 配置分类编号
     */
    @Field
    @NotNull(message = "配置分类编号不能为空")
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";

    /**
     * 键
     */
    @Field
    @NotNull(message = "键不能为空")
    private String configKey;
    public static final String CONFIG_KEY = "configKey";

    /**
     * 值
     */
    @Field
    @NotNull(message = "值不能为空")
    private String configValue;
    public static final String CONFIG_VALUE = "configValue";

    /**
     * 是否禁用
     */
    @Field
    @NotNull(message = "是否禁用不能为空")
    private Boolean configIsDisabled;
    public static final String CONFIG_IS_DISABLED = "configIsDisabled";

    /**
     * 描述
     */
    @Field
    @NotNull(message = "描述不能为空")
    private String configDescription;
    public static final String CONFIG_DESCRIPTION = "configDescription";
    
    /**
     * 分类名称
     */
    @Field
    @NotNull(message = "分类名称不能为空")
    private String configCategoryName;
    public static final String CONFIG_CATEGORY_NAME = "configCategoryName";


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

    public String getConfigCategoryName() {
        return getString(CONFIG_CATEGORY_NAME);
    }

    public void setConfigCategoryName(String configCategoryName) {
        put(CONFIG_CATEGORY_NAME, configCategoryName);
    }

}