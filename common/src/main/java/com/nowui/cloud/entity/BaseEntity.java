package com.nowui.cloud.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 实体父类
 *
 * @author marcus
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人编号
     */
    @TableField("systemCreateUserId")
    @NotNull(message = "systemCreateUserId must not be null")
    @Length(max = 32, message = "systemCreateUserId length must not exceed 32")
    protected String systemCreateUserId;

    /**
     * 创建时间
     */
    @TableField(value = "systemCreateTime", fill = FieldFill.INSERT)
    protected Date systemCreateTime;

    /**
     * 更新人编号
     */
    @TableField(value = "systemUpdateUserId")
    @NotNull(message = "systemUpdateUserId must not be null")
    @Length(max = 32, message = "systemUpdateUserId length must not exceed 32")
    protected String systemUpdateUserId;

    /**
     * 更新时间
     */
    @TableField(value = "systemUpdateTime", fill = FieldFill.INSERT_UPDATE)
    @NotNull(message = "systemUpdateTime must not be null")
    protected Date systemUpdateTime;

    /**
     * 版本号
     */
    @Version
    @TableField(value = "systemVersion", fill = FieldFill.INSERT)
    @NotNull(message = "systemVersion must not be null")
    @Max(11)
    protected Integer systemVersion;

    /**
     * 删除标识
     */
    @TableField(value = "systemStatus", fill = FieldFill.INSERT)
    @NotNull(message = "systemStatus must not be null")
    @TableLogic
    protected Boolean systemStatus;

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

    public Integer getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(Integer systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Boolean getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Boolean systemStatus) {
        this.systemStatus = systemStatus;
    }

}

