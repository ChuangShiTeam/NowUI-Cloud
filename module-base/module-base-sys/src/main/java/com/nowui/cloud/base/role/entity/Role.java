package com.nowui.cloud.base.role.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 角色
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@TableName(value = "role_info")
public class Role extends BaseEntity {

    /**
     * 角色编号
     */
    @Id
    @TableId
    @NotNull(message = "角色编号不能为空")
    @Length(max = 32, message = "角色编号长度超出限制")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @TableField
    @NotNull(message = "名称不能为空")
    @Length(max = 50, message = "名称长度超出限制")
    private String roleName;
    public static final String ROLE_NAME = "roleName";

    /**
     * 编码
     */
    @TableField
    @NotNull(message = "编码不能为空")
    @Length(max = 25, message = "编码长度超出限制")
    private String roleCode;
    public static final String ROLE_CODE = "roleCode";

    /**
     * 描述
     */
    @TableField
    @NotNull(message = "描述不能为空")
    @Length(max = 200, message = "描述长度超出限制")
    private String roleDescription;
    public static final String ROLE_DESCRIPTION = "roleDescription";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    private Integer roleSort;
    public static final String ROLE_SORT = "roleSort";


    public String getRoleId() {
        return getString(ROLE_ID);
    }

    public void setRoleId(String roleId) {
        put(ROLE_ID, roleId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getRoleName() {
        return getString(ROLE_NAME);
    }

    public void setRoleName(String roleName) {
        put(ROLE_NAME, roleName);
    }
    
    public String getRoleCode() {
        return getString(ROLE_CODE);
    }

    public void setRoleCode(String roleCode) {
        put(ROLE_CODE, roleCode);
    }
    
    public String getRoleDescription() {
        return getString(ROLE_DESCRIPTION);
    }

    public void setRoleDescription(String roleDescription) {
        put(ROLE_DESCRIPTION, roleDescription);
    }
    
    public Integer getRoleSort() {
        return getInteger(ROLE_SORT);
    }

    public void setRoleSort(Integer roleSort) {
        put(ROLE_SORT, roleSort);
    }

}