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
    @NotNull(message = "创建人编号不能为空")
    @Length(max = 32, message = "创建人编号字数超出限制")
    private String systemCreateUserId;

    /**
     * 创建时间
     */
    @TableField(value = "systemCreateTime", fill = FieldFill.INSERT)
    @NotNull(message = "创建时间不能为空")
    private Date systemCreateTime;

    /**
     * 更新人编号
     */
    @TableField(value = "systemUpdateUserId")
    @NotNull(message = "更新人编号不能为空")
    @Length(max = 32, message = "更新人编号字数超出限制")
    private String systemUpdateUserId;

    /**
     * 更新时间
     */
    @TableField(value = "systemUpdateTime", fill = FieldFill.INSERT_UPDATE)
    @NotNull(message = "systemUpdateTime must not be null")
    private Date systemUpdateTime;

    /**
     * 版本号
     */
    @Version
    @TableField(value = "systemVersion", fill = FieldFill.INSERT)
    @NotNull(message = "版本号不能为空")
    @Max(11)
    private Integer systemVersion;

    /**
     * 删除标识
     */
    @TableField(value = "systemStatus", fill = FieldFill.INSERT)
    @NotNull(message = "删除标识不能为空")
    @TableLogic
    private Boolean systemStatus;

    /**
     * 分页页数
     */
    @TableField(exist=false)
    @NotNull(message = "分页页数不能为空")
    private Integer pageIndex;

    /**
     * 分页页数
     */
    @TableField(exist=false)
    @NotNull(message = "每页数量不能为空")
    private Integer pageSize;

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

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getM() {
        if (pageIndex > 0) {
            return (pageIndex - 1) * pageSize;
        } else {
            return 0;
        }
    }

    public Integer getN() {
        return pageSize > 0 ? pageSize : 0;
    }
}

