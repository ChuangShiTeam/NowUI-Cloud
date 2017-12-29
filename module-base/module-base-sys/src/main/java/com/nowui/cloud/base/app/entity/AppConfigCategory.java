package com.nowui.cloud.base.app.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 应用配置分类
 * 
 * @author marcus
 *
 * 2017年12月25日
 */
@Component(value = "appConfigCategory")
@TableName(value = "app_config_category_info")
public class AppConfigCategory extends BaseEntity {
    
    /**
     * 应用配置分类编号
     */
    @TableId
    @NotNull(message = "应用配置分类编号不能为空")
    @Length(max = 32, message = "应用配置分类编号长度超出限制")
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 名称
     */
    @TableField
    @NotNull(message = "名称不能为空")
    @Length(max = 50, message = "名称长度超出限制")
    private String configCategoryName;
    public static final String CONFIG_CATEGORY_NAME = "configCategoryName";
    
    /** 
     * 编码
     */
    @TableField
    @NotNull(message = "编码不能为空")
    @Length(max = 50, message = "编码长度超出限制")
    private String configCategoryCode;
    public static final String CONFIG_CATEGORY_CODE = "configCategoryCode";
    
    /** 
     * 描述
     */
    @TableField
    @NotNull(message = "描述不能为空")
    @Length(max = 500, message = "描述长度超出限制")
    private String configCategoryDescription;
    public static final String CONFIG_CATEGORY_DESCRIPTION = "configCategoryDescription";

    public String getConfigCategoryId() {
        return getString(CONFIG_CATEGORY_ID);
    }

    public void setConfigCategoryId(String configCategoryId) {
        put(CONFIG_CATEGORY_ID, configCategoryId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getConfigCategoryName() {
        return getString(CONFIG_CATEGORY_NAME);
    }

    public void setConfigCategoryName(String configCategoryName) {
        put(CONFIG_CATEGORY_NAME, configCategoryName);
    }

    public String getConfigCategoryCode() {
        return getString(CONFIG_CATEGORY_CODE);
    }

    public void setConfigCategoryCode(String configCategoryCode) {
        put(CONFIG_CATEGORY_CODE, configCategoryCode);
    }

    public String getConfigCategoryDescription() {
        return getString(CONFIG_CATEGORY_DESCRIPTION);
    }

    public void setConfigCategoryDescription(String configCategoryDescription) {
        put(CONFIG_CATEGORY_DESCRIPTION, configCategoryDescription);
    }

}
