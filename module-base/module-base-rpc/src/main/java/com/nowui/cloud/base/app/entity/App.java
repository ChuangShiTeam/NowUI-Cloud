package com.nowui.cloud.base.app.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 应用
 * 
 * @author marcus
 *
 * 2017年12月25日
 */
@Component(value = "app")
@TableName(value = "app_info")
public class App extends BaseEntity {
    
    /**
     * 应用编号
     */
    @TableId
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 应用名称
     */
    @TableField
    @NotNull(message = "应用名称不能为空")
    @Length(max = 100, message = "应用名称长度超出限制")
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
