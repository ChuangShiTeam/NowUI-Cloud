package com.nowui.cloud.base.app.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 应用配置分类视图
 *
 * @author marcus
 *
 * 2018-03-14
 */
@Component
@Document(collection = "app_config_category_info")
public class AppConfigCategoryView extends BaseView {

    /**
     * 应用配置分类编号
     */
    @KeyId
    @Field
    @NotNull(message = "应用配置分类编号不能为空")
    @Length(max = 32, message = "应用配置分类编号长度超出限制")
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    @Length(max = 50, message = "名称长度超出限制")
    private String configCategoryName;
    public static final String CONFIG_CATEGORY_NAME = "configCategoryName";

    /**
     * 编码
     */
    @Field
    @NotNull(message = "编码不能为空")
    @Length(max = 50, message = "编码长度超出限制")
    private String configCategoryCode;
    public static final String CONFIG_CATEGORY_CODE = "configCategoryCode";

    /**
     * 描述
     */
    @Field
    @NotNull(message = "描述不能为空")
    @Length(max = 500, message = "描述长度超出限制")
    private String configCategoryDescription;
    public static final String CONFIG_CATEGORY_DESCRIPTION = "configCategoryDescription";


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