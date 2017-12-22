package com.nowui.cloud.app.app.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
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
 */
@Component(value = "appConfig")
@TableName(value = "app_config_info")
public class AppConfig extends BaseEntity {
    
    /**
     * 应用配置编号
     */
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
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getConfigCategoryId() {
        return configCategoryId;
    }

    public void setConfigCategoryId(String configCategoryId) {
        this.configCategoryId = configCategoryId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Boolean getConfigIsDisabled() {
        return configIsDisabled;
    }

    public void setConfigIsDisabled(Boolean configIsDisabled) {
        this.configIsDisabled = configIsDisabled;
    }

    public String getConfigDescription() {
        return configDescription;
    }

    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }

}
