package com.nowui.cloud.base.app.entity;

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
 * 2018-03-14
 */
@Component

@TableName(value = "app_config_info")
public class AppConfig extends BaseEntity {

    /**
     * 配置编号
     */
    @TableId
    @TableField
    private String configId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 配置分类编号
     */
    @TableField
    private String configCategoryId;

    /**
     * 键
     */
    @TableField
    private String configKey;

    /**
     * 值
     */
    @TableField
    private String configValue;

    /**
     * 是否禁用
     */
    @TableField
    private Boolean configIsDisabled;

    /**
     * 描述
     */
    @TableField
    private String configDescription;


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