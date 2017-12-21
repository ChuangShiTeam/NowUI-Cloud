package com.nowui.cloud.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 实体父类
 *
 * @author marcus
 */
public class BaseEntity implements Serializable {

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
     * 请求人编号
     */
    @TableField(exist=false)
    @NotNull(message = "请求人编号不能为空")
    @Length(max = 32, message = "请求人编号字数超出限制")
    private String systemRequestUserId;

    /**
     * 关键编号
     */
    @TableField(exist=false)
    @JSONField(serialize=false)
    @JsonIgnore
    private String primary;

    /**
     * 分页页数
     */
    @TableField(exist=false)
    @NotNull(message = "分页页数不能为空")
    @JsonIgnore
    private Integer pageIndex;

    /**
     * 分页页数
     */
    @TableField(exist=false)
    @NotNull(message = "每页数量不能为空")
    @JsonIgnore
    private Integer pageSize;

    @TableField(exist=false)
    @JSONField(serialize=false)
    @JsonIgnore
    private Integer m;

    @TableField(exist=false)
    @JSONField(serialize=false)
    @JsonIgnore
    private Integer n;

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

    public String getSystemRequestUserId() {
        return systemRequestUserId;
    }

    public void setSystemRequestUserId(String systemRequestUserId) {
        this.systemRequestUserId = systemRequestUserId;
    }

    public String getPrimary() {
        if (primary == null) {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean isPrimary = field.isAnnotationPresent(TableId.class);
                if (isPrimary) {
                    field.setAccessible(true);
                    return field.getName();
                }
            }
        }
        return primary;
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
        int index = 0;
        if (getPageIndex() != null) {
            index = getPageIndex();
        }

        int size = 0;
        if (getPageSize() != null) {
            size = getPageSize();
        }


        if (index > 0) {
            return (index - 1) * size;
        } else {
            return 0;
        }
    }

    public Integer getN() {
        int size = 0;
        if (getPageSize() != null) {
            size = getPageSize();
        }

        return size > 0 ? size : 0;
    }
}

