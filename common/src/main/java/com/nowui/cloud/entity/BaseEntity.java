package com.nowui.cloud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * 实体父类
 * @author marcus
 *
 * @param
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    /**
//     * 创建人id
//     */
//    @TableField("systemCreateUserId")
//    protected String systemCreateUserId;
//    /**
//     * 创建时间
//     */
//    @TableField(value = "systemCreateTime", fill = FieldFill.INSERT)
//    protected Date systemCreateTime;
//    /**
//     * 更新人
//     */
//    @TableField("systemUpdateUserId")
//    protected String systemUpdateUserId;
//    /**
//     * 更新时间
//     */
//    @TableField(value = "systemUpdateTime", fill = FieldFill.INSERT_UPDATE)
//    protected Date systemUpdateTime;
//    /**
//     * 版本号
//     */
//    @TableField(value = "systemVersion", fill = FieldFill.INSERT)
//    @Version
//    protected Integer systemVersion;
//    /**
//     * 是否删除
//     */
//    @TableField(value = "systemStatus", fill = FieldFill.INSERT)
//    @TableLogic
//    protected Boolean systemStatus;
//
//    public String getSystemCreateUserId() {
//        return systemCreateUserId;
//    }
//
//    public void setSystemCreateUserId(String systemCreateUserId) {
//        this.systemCreateUserId = systemCreateUserId;
//    }
//
//    public Date getSystemCreateTime() {
//        return systemCreateTime;
//    }
//
//    public void setSystemCreateTime(Date systemCreateTime) {
//        this.systemCreateTime = systemCreateTime;
//    }
//
//    public String getSystemUpdateUserId() {
//        return systemUpdateUserId;
//    }
//
//    public void setSystemUpdateUserId(String systemUpdateUserId) {
//        this.systemUpdateUserId = systemUpdateUserId;
//    }
//
//    public Date getSystemUpdateTime() {
//        return systemUpdateTime;
//    }
//
//    public void setSystemUpdateTime(Date systemUpdateTime) {
//        this.systemUpdateTime = systemUpdateTime;
//    }
//
//    public Integer getSystemVersion() {
//        return systemVersion;
//    }
//
//    public void setSystemVersion(Integer systemVersion) {
//        this.systemVersion = systemVersion;
//    }
//
//    public Boolean getSystemStatus() {
//        return systemStatus;
//    }
//
//    public void setSystemStatus(Boolean systemStatus) {
//        this.systemStatus = systemStatus;
//    }
//
//    public static final String SYSTEM_CREATE_USER_ID = "systemCreateUserId";
//
//    public static final String SYSTEM_CREATE_TIME = "systemCreateTime";
//
//    public static final String SYSTEM_UPDATE_USER_ID = "systemUpdateUserId";
//
//    public static final String SYSTEM_UPDATE_TIME = "systemUpdateTime";
//
//    public static final String SYSTEM_VERSION = "systemVersion";
//
//    public static final String SYSTEM_STATUS = "systemStatus";
    
}

