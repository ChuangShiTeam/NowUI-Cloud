package com.nowui.cloud.base.role.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 角色
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component

@TableName(value = "role_info")
public class Role extends BaseEntity {

    /**
     * 角色编号
     */
    @TableId
    @TableField
    private String roleId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 名称
     */
    @TableField
    private String roleName;

    /**
     * 编码
     */
    @TableField
    private String roleCode;

    /**
     * 描述
     */
    @TableField
    private String roleDescription;

    /**
     * 排序
     */
    @TableField
    private Integer roleSort;


    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Integer getRoleSort() {
        return roleSort;
    }
    
    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }


}