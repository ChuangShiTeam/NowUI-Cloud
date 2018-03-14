package com.nowui.cloud.base.app.entity;

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
 * 2018-03-14
 */
@Component

@TableName(value = "app_config_category_info")
public class AppConfigCategory extends BaseEntity {

    /**
     * 应用配置分类编号
     */
    @TableId
    @TableField
    private String configCategoryId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 名称
     */
    @TableField
    private String configCategoryName;

    /**
     * 编码
     */
    @TableField
    private String configCategoryCode;

    /**
     * 描述
     */
    @TableField
    private String configCategoryDescription;


    public String getConfigCategoryId() {
        return configCategoryId;
    }
    
    public void setConfigCategoryId(String configCategoryId) {
        this.configCategoryId = configCategoryId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getConfigCategoryName() {
        return configCategoryName;
    }
    
    public void setConfigCategoryName(String configCategoryName) {
        this.configCategoryName = configCategoryName;
    }

    public String getConfigCategoryCode() {
        return configCategoryCode;
    }
    
    public void setConfigCategoryCode(String configCategoryCode) {
        this.configCategoryCode = configCategoryCode;
    }

    public String getConfigCategoryDescription() {
        return configCategoryDescription;
    }
    
    public void setConfigCategoryDescription(String configCategoryDescription) {
        this.configCategoryDescription = configCategoryDescription;
    }


}