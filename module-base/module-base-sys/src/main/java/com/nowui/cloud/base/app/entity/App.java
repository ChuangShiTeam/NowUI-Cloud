package com.nowui.cloud.base.app.entity;

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
 * 2018-03-14
 */
@Component

@TableName(value = "app_info")
public class App extends BaseEntity {

    /**
     * 应用编号
     */
    @TableId
    @TableField
    private String appId;

    /**
     * 应用名称
     */
    @TableField
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