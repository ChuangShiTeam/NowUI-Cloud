package com.nowui.cloud.app.app.entity;

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
 */
@Component(value = "app")
@TableName(value = "app_info")
public class App extends BaseEntity {
    
    /**
     * 应用编号
     */
    @TableId
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号字数超出限制")
    private String appId;
    
    /**
     * 应用名称
     */
    @TableField
    @NotNull(message = "应用名称不能为空")
    @Length(max = 100, message = "应用名称字数超出限制")
    private String appName;

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
