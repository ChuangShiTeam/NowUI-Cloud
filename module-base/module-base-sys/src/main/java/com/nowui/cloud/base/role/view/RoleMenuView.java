package com.nowui.cloud.base.role.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 角色菜单视图
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component
@Document(collection = "role_menu_map")
public class RoleMenuView extends BaseView {

    /**
     * 角色菜单编号
     */
    @KeyId
    @Field
    @NotNull(message = "角色菜单编号不能为空")
    @Length(max = 32, message = "角色菜单编号长度超出限制")
    private String roleMenuId;
    public static final String ROLE_MENU_ID = "roleMenuId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 角色编号
     */
    @Field
    @NotNull(message = "角色编号不能为空")
    @Length(max = 32, message = "角色编号长度超出限制")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 菜单编号
     */
    @Field
    @NotNull(message = "菜单编号不能为空")
    @Length(max = 32, message = "菜单编号长度超出限制")
    private String menuId;
    public static final String MENU_ID = "menuId";


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