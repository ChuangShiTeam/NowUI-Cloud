package com.nowui.cloud.view;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.SystemException;
import com.nowui.cloud.util.Util;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Field;

import static org.bouncycastle.crypto.tls.BulkCipherAlgorithm.des;

/**
 * baseView
 *
 * @author ZhongYongQiang
 * <p>
 * 2018-01-29
 */
//@Component
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

    public void copy(BaseEntity baseEntity) {
        BeanUtils.copyProperties(this, baseEntity);
    }

}
