package com.nowui.cloud.view;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.SystemException;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * baseView
 *
 * @author ZhongYongQiang
 * <p>
 * 2018-01-29
 */
@Component
public class BaseView implements Serializable {

    /**
     * 创建人编号
     */
    @Field
    @NotNull(message = "创建人编号不能为空")
    @Length(max = 32, message = "创建人编号长度超出限制")
    private String systemCreateUserId;
    public static final String SYSTEM_CREATE_USER_ID = "systemCreateUserId";

    /**
     * 创建时间
     */
    @Field
    @NotNull(message = "创建时间不能为空")
    private Date systemCreateTime;
    public static final String SYSTEM_CREATE_TIME = "systemCreateTime";

    /**
     * 更新人编号
     */
    @Field
    @NotNull(message = "更新人编号不能为空")
    @Length(max = 32, message = "更新人编号长度超出限制")
    private String systemUpdateUserId;
    public static final String SYSTEM_UPDATE_USER_ID = "systemUpdateUserId";

    /**
     * 更新时间
     */
    @Field
    @NotNull(message = "更新时间不能为空")
    private Date systemUpdateTime;
    public static final String SYSTEM_UPDATE_TIME = "systemUpdateTime";

    /**
     * 版本号
     */
    @Field
    @NotNull(message = "版本号不能为空")
    private Integer systemVersion;
    public static final String SYSTEM_VERSION = "systemVersion";

    /**
     * 删除标识
     */
    @Field
    @NotNull(message = "删除标识不能为空")
    private Boolean systemStatus;
    public static final String SYSTEM_STATUS = "systemStatus";

    /**
     * 请求人编号
     */
    @NotNull(message = "请求人编号不能为空")
    @Length(max = 32, message = "请求人编号长度超出限制")
    @JsonIgnore
    private String systemRequestUserId;
    public static final String SYSTEM_REQUEST_USER_ID = "systemRequestUserId";

    /**
     * 分页页数
     */
    @NotNull(message = "分页页数不能为空")
    @Digits(integer = 11, fraction = 0, message = "分页页数长度超出限制")
    @JsonIgnore
    private Integer pageIndex;
    public static final String PAGE_INDEX = "pageIndex";

    /**
     * 每页数量
     */
    @NotNull(message = "每页数量不能为空")
    @Digits(integer = 11, fraction = 0, message = "分页页数长度超出限制")
    @JsonIgnore
    private Integer pageSize;
    public static final String PAGE_SIZE = "pageSize";

    private String tableId;

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

    public String tableId() {
        if (tableId == null) {
            java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                boolean isKeyId = field.isAnnotationPresent(KeyId.class);
                if (isKeyId) {
                    field.setAccessible(true);
                    tableId = field.getName();
                    return tableId;
                }
            }
        }
        return tableId;
    }

    public void putEntry(BaseEntity baseEntity) {
        try {
            Class sourCls = baseEntity.getClass();
            //遍历源属性
            do {
                //源属性集
                java.lang.reflect.Field[] sourFlds = sourCls.getDeclaredFields();
                //遍历源所有属性
                for (int i = 0; i < sourFlds.length; i++) {
                    java.lang.reflect.Field sf = sourFlds[i];
                    sf.setAccessible(true);
                    //遍历目标所有属性
                    Class toCls = this.getClass();
                    do {
                        //源属性集
                        java.lang.reflect.Field[] toFlds = toCls.getDeclaredFields();
                        //遍历源所有属性
                        for (int j = 0; j < toFlds.length; j++) {
                            java.lang.reflect.Field tof = toFlds[j];
                            tof.setAccessible(true);
                            TableField tableField = (TableField) sf.getAnnotation(TableField.class);
                            Boolean isExist = false;
                            if (tableField != null) {
                                isExist = tableField.exist();
                            }
                            //属性名字相同
                            if (sf.getName().equals(tof.getName()) && isExist) {
                                //得到此属性的类型
                                String type = tof.getType().toString();
                                if (type.endsWith("String")) {
                                    tof.set(this, (String) sf.get(baseEntity));
                                } else if (type.endsWith("int") || type.endsWith("Integer")) {
                                    tof.set(this, (Integer) sf.get(baseEntity));
                                } else if (type.endsWith("Date")) {
                                    tof.set(this, (Date) sf.get(baseEntity));
                                } else if (type.endsWith("long") || type.endsWith("Long")) {
                                    tof.set(this, (Long) sf.get(baseEntity));
                                } else if (type.endsWith("short") || type.endsWith("Short")) {
                                    tof.set(this, (Long) sf.get(baseEntity));
                                } else if (type.endsWith("Boolean")) {
                                    tof.set(this, (Boolean) sf.get(baseEntity));
                                } else {
                                    throw new SystemException("类型转换失败！");
                                }
                            }
                        }
                        toCls = toCls.getSuperclass();

                    } while (toCls != Object.class);
                }
                sourCls = sourCls.getSuperclass();
            } while (sourCls != Object.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
