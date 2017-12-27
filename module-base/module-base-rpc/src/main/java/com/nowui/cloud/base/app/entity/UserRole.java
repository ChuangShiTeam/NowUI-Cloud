package com.nowui.cloud.base.app.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 用户角色
 * 
 * @author marcus
 *
 */
@Component(value = "userRole")
@TableName(value = "user_role_map")
public class UserRole extends BaseEntity {

    /**
     * 用户角色编号
     */
    @TableId
    @NotNull(message = "应用配置编号不能为空")
    @Length(max = 32, message = "应用配置编号长度超出限制")
    private String userRoleId;
    public static final String USER_ROLE_ID = "userRoleId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
}
