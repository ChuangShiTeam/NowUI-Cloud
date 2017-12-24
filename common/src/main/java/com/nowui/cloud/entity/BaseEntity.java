package com.nowui.cloud.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.*;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 实体父类
 *
 * @author marcus
 */
public abstract class BaseEntity extends JSONObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人编号
     */
    @TableField
    @NotNull(message = "创建人编号不能为空")
    @Length(max = 32, message = "创建人编号长度超出限制")
    private String systemCreateUserId;
    public static final String SYSTEM_CREATE_USER_ID = "systemCreateUserId";

    /**
     * 创建时间
     */
    @TableField
    @NotNull(message = "创建时间不能为空")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date systemCreateTime;
    public static final String SYSTEM_CREATE_TIME = "systemCreateTime";

    /**
     * 更新人编号
     */
    @TableField(value = SYSTEM_UPDATE_USER_ID)
    @NotNull(message = "更新人编号不能为空")
    @Length(max = 32, message = "更新人编号长度超出限制")
    private String systemUpdateUserId;
    public static final String SYSTEM_UPDATE_USER_ID = "systemUpdateUserId";

    /**
     * 更新时间
     */
    @TableField(value = SYSTEM_UPDATE_TIME)
    @NotNull(message = "systemUpdateTime must not be null")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date systemUpdateTime;
    public static final String SYSTEM_UPDATE_TIME = "systemUpdateTime";

    /**
     * 版本号
     */
    @Version
    @TableField(value = SYSTEM_VERSION)
    @NotNull(message = "版本号不能为空")
    @Max(11)
    private Integer systemVersion;
    public static final String SYSTEM_VERSION = "systemVersion";

    /**
     * 删除标识
     */
    @TableField(value = SYSTEM_STATUS)
    @NotNull(message = "删除标识不能为空")
    private Boolean systemStatus;
    public static final String SYSTEM_STATUS = "systemStatus";

    /**
     * 请求人编号
     */
    @TableField(exist = false)
    @NotNull(message = "请求人编号不能为空")
    @Length(max = 32, message = "请求人编号长度超出限制")
    private String systemRequestUserId;
    public static final String SYSTEM_REQUEST_USER_ID = "systemRequestUserId";

    /**
     * 数据库名称
     */
    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private String tableName;
    public static final String TABLE_NAME = "tableName";

    /**
     * 关键编号
     */
    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private String tableId;
    public static final String TABLE_ID = "tableId";

    /**
     * 关键编号
     */
    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private List<String> tableFieldList;
    public static final String TABLE_FIELD_LIST = "tableFieldList";

    /**
     * 分页页数
     */
    @TableField(exist = false)
    @NotNull(message = "分页页数不能为空")
    @JsonIgnore
    private Integer pageIndex;
    public static final String PAGE_INDEX = "pageIndex";

    /**
     * 分页页数
     */
    @TableField(exist = false)
    @NotNull(message = "每页数量不能为空")
    @JsonIgnore
    private Integer pageSize;
    public static final String PAGE_SIZE = "pageSize";

    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private Integer m;

    @TableField(exist = false)
    @JSONField(serialize = false)
    @JsonIgnore
    private Integer n;

    @TableField(exist = false)
    private final Map<String, Object> map = null;

    public String getSystemCreateUserId() {
        return getString(SYSTEM_CREATE_USER_ID);
    }

    public void setSystemCreateUserId(String systemCreateUserId) {
        put(SYSTEM_CREATE_USER_ID, systemCreateUserId);
    }

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

    public String getSystemRequestUserId() {
        return getString(SYSTEM_REQUEST_USER_ID);
    }

    public void setSystemRequestUserId(String systemRequestUserId) {
        put(SYSTEM_REQUEST_USER_ID, systemRequestUserId);
    }

    public String getTableName() {
        if (tableName == null) {
            TableName table = this.getClass().getAnnotation(TableName.class);
            if (table != null) {
                tableName = table.value();
                return tableName;
            }
        }
        return tableName;
    }

    public String getTableId() {
        if (tableId == null) {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
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

    public List<String> getTableFieldList() {
        if (tableFieldList == null) {
            tableFieldList = new ArrayList<String>();

            tableFieldList.add(getTableId());

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean isTableField = field.isAnnotationPresent(TableField.class);
                if (isTableField) {
                    TableField tableField = field.getAnnotation(TableField.class);
                    if (tableField.exist()) {
                        field.setAccessible(true);
                        tableFieldList.add(field.getName());
                    }
                }
            }
        }
        return tableFieldList;
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

    public void keepTableFieldValue() {
        Iterator<Entry<String, Object>> iterator = this.getInnerMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            Boolean isExit = false;

            for (String tableField : getTableFieldList()) {

                if (tableField.equals(entry.getKey())) {
                    isExit = true;

                    break;
                }
            }

            if (!isExit) {
//                iterator.remove();
            }
        }
    }

}

