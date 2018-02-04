package com.nowui.cloud.base.app.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 应用配置
 * 
 * @author marcus
 *
 * 2017年12月25日
 */
@Component(value = "appConfig")
@TableName(value = "app_config_info")
public class AppConfig extends BaseEntity {
    
    /**
     * 应用配置编号
     */
    @Id
    @TableId
    @NotNull(message = "应用配置编号不能为空")
    @Length(max = 32, message = "应用配置编号长度超出限制")
    private String configId;
    public static final String CONFIG_ID = "configId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 应用配置分类编号
     */
    @TableField
    @NotNull(message = "应用配置分类编号不能为空")
    @Length(max = 32, message = "应用配置分类编号长度超出限制")
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";
    
    /**
     * 键
     */
    @TableField
    @NotNull(message = "键不能为空")
    @Length(max = 50, message = "键长度超出限制")
    private String configKey;
    public static final String CONFIG_KEY = "configKey";
    
    /**
     * 值
     */
    @TableField
    @NotNull(message = "值不能为空")
    @Length(max = 2000, message = "值长度超出限制")
    private String configValue;
    public static final String CONFIG_VALUE = "configValue";
    
    /**
     * 是否禁用
     */
    @TableField
    @NotNull(message = "是否禁用不能为空")
    private Boolean configIsDisabled;
    public static final String CONFIG_IS_DISABLED = "configIsDisabled";
    
    /**
     * 描述
     */
    @TableField
    @NotNull(message = "描述不能为空")
    @Length(max = 500, message = "描述长度超出限制")
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
