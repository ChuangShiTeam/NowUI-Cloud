package com.nowui.cloud.view;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * baseView
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
public class BaseView extends JSONObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人编号
     */
    @Field
    private String systemCreateUserId;
    public static final String SYSTEM_CREATE_USER_ID = "systemCreateUserId";

    /**
     * 创建时间
     */
    @Field
    private Date systemCreateTime;
    public static final String SYSTEM_CREATE_TIME = "systemCreateTime";

    /**
     * 更新人编号
     */
    @Field
    private String systemUpdateUserId;
    public static final String SYSTEM_UPDATE_USER_ID = "systemUpdateUserId";

    /**
     * 更新时间
     */
    @Field
    private Date systemUpdateTime;
    public static final String SYSTEM_UPDATE_TIME = "systemUpdateTime";

    /**
     * 版本号
     */
    @Field
    private Integer systemVersion;
    public static final String SYSTEM_VERSION = "systemVersion";

    /**
     * 删除标识
     */
    @Field
    private Boolean systemStatus;
    public static final String SYSTEM_STATUS = "systemStatus";

    /**
     * 分页页数
     */
    @NotNull(message = "分页页数不能为空")
    @JsonIgnore
    private Integer pageIndex;
    public static final String PAGE_INDEX = "pageIndex";

    /**
     * 每页数量
     */
    @NotNull(message = "每页数量不能为空")
    @JsonIgnore
    private Integer pageSize;
    public static final String PAGE_SIZE = "pageSize";


    public String getSystemCreateUserId() {
        return getString(SYSTEM_CREATE_USER_ID);
    }

    public void setSystemCreateUserId(String systemCreateUserId) {
        put(SYSTEM_CREATE_USER_ID, systemCreateUserId);
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getSystemCreateTime() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystemCreateTime(Date systemCreateTime) {
        put(SYSTEM_CREATE_TIME, systemCreateTime);
    }

    public String getSystemUpdateUserId() {
        return getString(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystemUpdateUserId(String systemUpdateUserId) {
        put(SYSTEM_UPDATE_USER_ID, systemUpdateUserId);
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getSystemUpdateTime() {
        return getDate(SYSTEM_UPDATE_TIME);
    }

    public void setSystemUpdateTime(Date systemUpdateTime) {
        put(SYSTEM_UPDATE_TIME, systemUpdateTime);
    }

    public Integer getSystemVersion() {
        return getInteger(SYSTEM_VERSION);
    }

    public void setSystemVersion(Integer systemVersion) {
        put(SYSTEM_VERSION, systemVersion);
    }

    public Boolean getSystemStatus() {
        return getBoolean(SYSTEM_STATUS);
    }

    public void setSystemStatus(Boolean systemStatus) {
        put(SYSTEM_STATUS, systemStatus);
    }

    private String tableId;

    public String getTableId() {
        if (tableId == null) {
            java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                boolean isId = field.isAnnotationPresent(Id.class);
                if (isId) {
                    field.setAccessible(true);
                    tableId = field.getName();
                    return tableId;
                }
            }
        }
        return tableId;
    }
    
    public Integer getPageIndex() {
        return getInteger(PAGE_INDEX);
    }

    public void setPageIndex(Integer pageIndex) {
        put(PAGE_INDEX, pageIndex);
    }

    public Integer getPageSize() {
        return getInteger(PAGE_SIZE);
    }

    public void setPageSize(Integer pageSize) {
        put(PAGE_SIZE, pageSize);
    }

}
