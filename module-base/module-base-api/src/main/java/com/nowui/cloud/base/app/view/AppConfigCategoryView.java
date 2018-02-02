package com.nowui.cloud.base.app.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 应用配置分类视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "app_config_category_info")
public class AppConfigCategoryView extends BaseView {

    /**
     * 应用配置分类编号
     */
    @Id
    private String configCategoryId;
    public static final String CONFIG_CATEGORY_ID = "configCategoryId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @Field
    private String configCategoryName;
    public static final String CONFIG_CATEGORY_NAME = "configCategoryName";

    /**
     * 编码
     */
    @Field
    private String configCategoryCode;
    public static final String CONFIG_CATEGORY_CODE = "configCategoryCode";

    /**
     * 描述
     */
    @Field
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