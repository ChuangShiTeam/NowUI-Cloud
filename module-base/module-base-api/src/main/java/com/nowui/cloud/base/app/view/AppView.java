package com.nowui.cloud.base.app.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 应用视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "app_info")
public class AppView extends BaseView {

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 应用名称
     */
    @Field
    @NotNull(message = "应用名称不能为空")
    private String appName;
    public static final String APP_NAME = "appName";


    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getAppName() {
        return getString(APP_NAME);
    }

    public void setAppName(String appName) {
        put(APP_NAME, appName);
    }


}