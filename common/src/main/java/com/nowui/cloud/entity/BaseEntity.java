package com.nowui.cloud.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 实体父类
 *
 * @author marcus
 */
public class BaseEntity extends HashMap<String, Object> implements Serializable {

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
    @TableField(value = "systemCreateTime")
    @NotNull(message = "创建时间不能为空")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss") 
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
    @TableField(value = "systemUpdateTime")
    @NotNull(message = "systemUpdateTime must not be null")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date systemUpdateTime;

    /**
     * 版本号
     */
    @Version
    @TableField(value = "systemVersion")
    @NotNull(message = "版本号不能为空")
    @Max(11)
    private Integer systemVersion;

    /**
     * 删除标识
     */
    @TableField(value = "systemStatus")
    @NotNull(message = "删除标识不能为空")
    @TableLogic
    private Boolean systemStatus;

    /**
     * 请求人编号
     */
    @TableField(exist = false)
    @NotNull(message = "请求人编号不能为空")
    @Length(max = 32, message = "请求人编号字数超出限制")
    private String systemRequestUserId;

    /**
     * 关键编号
     */
    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private String primary;

    /**
     * 分页页数
     */
    @TableField(exist = false)
    @NotNull(message = "分页页数不能为空")
    @JsonIgnore
    private Integer pageIndex;

    /**
     * 分页页数
     */
    @TableField(exist = false)
    @NotNull(message = "每页数量不能为空")
    @JsonIgnore
    private Integer pageSize;

    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private Integer m;

    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private Integer n;

    @TableField(exist = false)
    int threshold;

    @TableField(exist = false)
    float loadFactor;

    public String getSystemCreateUserId() {
        return get("systemCreateUserId").toString();
    }

    public void setSystemCreateUserId(String systemCreateUserId) {
        put("systemCreateUserId", systemCreateUserId);
    }

    public Date getSystemCreateTime() {
        return (Date) get("systemCreateTime");
    }

    public void setSystemCreateTime(Date systemCreateTime) {
        put("systemCreateTime", systemCreateTime);
    }

    public String getSystemUpdateUserId() {
        return get("systemUpdateUserId").toString();
    }

    public void setSystemUpdateUserId(String systemUpdateUserId) {
        put("systemUpdateUserId", systemUpdateUserId);
    }

    public Date getSystemUpdateTime() {
        return (Date) get("systemUpdateTime");
    }

    public void setSystemUpdateTime(Date systemUpdateTime) {
        put("systemUpdateTime", systemUpdateTime);
    }

    public Integer getSystemVersion() {
        return (Integer) get("systemVersion");
    }

    public void setSystemVersion(Integer systemVersion) {
        put("systemVersion", systemVersion);
    }

    public Boolean getSystemStatus() {
        return (Boolean) get("systemStatus");
    }

    public void setSystemStatus(Boolean systemStatus) {
        put("systemStatus", systemStatus);
    }

    public String getSystemRequestUserId() {
        return get("systemRequestUserId").toString();
    }

    public void setSystemRequestUserId(String systemRequestUserId) {
        put("systemRequestUserId", systemRequestUserId);
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

