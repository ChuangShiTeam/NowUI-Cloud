package com.nowui.cloud.base.role.view;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 角色视图
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
@Document(collection = "role_info")
public class RoleView extends BaseView {

    /**
     * 角色编号
     */
    @Field
    @NotNull(message = "角色编号不能为空")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    private String roleName;
    public static final String ROLE_NAME = "roleName";

    /**
     * 编码
     */
    @Field
    @NotNull(message = "编码不能为空")
    private String roleCode;
    public static final String ROLE_CODE = "roleCode";

    /**
     * 描述
     */
    @Field
    @NotNull(message = "描述不能为空")
    private String roleDescription;
    public static final String ROLE_DESCRIPTION = "roleDescription";

    /**
     * 排序
     */
    @Field
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