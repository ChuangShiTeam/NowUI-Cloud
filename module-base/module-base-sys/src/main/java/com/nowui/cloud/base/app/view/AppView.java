package com.nowui.cloud.base.app.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 应用视图
 *
 * @author marcus
 *
 * 2018-03-14
 */
@Component
@Document(collection = "app_info")
public class AppView extends BaseView {

    /**
     * 应用编号
     */
    @KeyId
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 应用名称
     */
    @Field
    @NotNull(message = "应用名称不能为空")
    @Length(max = 100, message = "应用名称长度超出限制")
    private String appName;
    public static final String APP_NAME = "appName";


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}