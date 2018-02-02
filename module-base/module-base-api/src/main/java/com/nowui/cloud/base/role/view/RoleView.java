package com.nowui.cloud.base.role.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 角色视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "role_info")
public class RoleView extends BaseView {

    /**
     * 角色编号
     */
    @Id
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @Field
    private String roleName;
    public static final String ROLE_NAME = "roleName";

    /**
     * 编码
     */
    @Field
    private String roleCode;
    public static final String ROLE_CODE = "roleCode";

    /**
     * 描述
     */
    @Field
    private String roleDescription;
    public static final String ROLE_DESCRIPTION = "roleDescription";

    /**
     * 排序
     */
    @Field
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