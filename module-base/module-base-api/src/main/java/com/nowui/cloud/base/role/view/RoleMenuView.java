package com.nowui.cloud.base.role.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 角色菜单视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "role_menu_map")
public class RoleMenuView extends BaseView {

    /**
     * 角色菜单编号
     */
    @Id
    private String roleMenuId;
    public static final String ROLE_MENU_ID = "roleMenuId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 角色编号
     */
    @Field
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 菜单编号
     */
    @Field
    private String menuId;
    public static final String MENU_ID = "menuId";


    public String getRoleMenuId() {
        return getString(ROLE_MENU_ID);
    }

    public void setRoleMenuId(String roleMenuId) {
        put(ROLE_MENU_ID, roleMenuId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getRoleId() {
        return getString(ROLE_ID);
    }

    public void setRoleId(String roleId) {
        put(ROLE_ID, roleId);
    }

    public String getMenuId() {
        return getString(MENU_ID);
    }

    public void setMenuId(String menuId) {
        put(MENU_ID, menuId);
    }


}