package com.nowui.cloud.entity;

import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.annotations.*;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 实体父类
 *
 * @author marcus
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 创建人编号
     */
    @TableField
    private String systemCreateUserId;

    /**
     * 创建时间
     */
    @TableField
    private Date systemCreateTime;

    /**
     * 更新人编号
     */
    @TableField
    private String systemUpdateUserId;

    /**
     * 更新时间
     */
    @TableField
    private Date systemUpdateTime;

    /**
     * 版本号
     */
    @Version
    @TableField
    private Integer systemVersion;

    /**
     * 删除标识
     */
    @TableField
    private Boolean systemStatus;

    /**
     * 关键编号
     */
    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private String tableId;

    public Integer getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(Integer systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getSystemCreateUserId() {
        return systemCreateUserId;
    }

    public void setSystemCreateUserId(String systemCreateUserId) {
        this.systemCreateUserId = systemCreateUserId;
    }

    public Date getSystemCreateTime() {
        return systemCreateTime;
    }

    public void setSystemCreateTime(Date systemCreateTime) {
        this.systemCreateTime = systemCreateTime;
    }

    public String getSystemUpdateUserId() {
        return systemUpdateUserId;
    }

    public void setSystemUpdateUserId(String systemUpdateUserId) {
        this.systemUpdateUserId = systemUpdateUserId;
    }

    public Date getSystemUpdateTime() {
        return systemUpdateTime;
    }

    public void setSystemUpdateTime(Date systemUpdateTime) {
        this.systemUpdateTime = systemUpdateTime;
    }

    public Boolean getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Boolean systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String tableId() {
        if (tableId == null) {
            java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                boolean isTableId = field.isAnnotationPresent(TableId.class);
                if (isTableId) {
                    field.setAccessible(true);
                    tableId = field.getName();
                    return tableId;
                }
            }
        }
        return tableId;
    }

}

