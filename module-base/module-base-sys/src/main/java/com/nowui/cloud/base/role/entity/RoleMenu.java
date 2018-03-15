package com.nowui.cloud.base.role.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 角色菜单
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component

@TableName(value = "role_menu_map")
public class RoleMenu extends BaseEntity {

    /**
     * 角色菜单编号
     */
    @TableId
    @TableField
    private String roleMenuId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 角色编号
     */
    @TableField
    private String roleId;

    /**
     * 菜单编号
     */
    @TableField
    private String menuId;


    public String getRoleMenuId() {
        return roleMenuId;
    }
    
    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


}