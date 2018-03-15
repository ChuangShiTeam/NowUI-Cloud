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
 * 角色视图
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component
@Document(collection = "role_info")
public class RoleView extends BaseView {

    /**
     * 角色编号
     */
    @KeyId
    @Field
    @NotNull(message = "角色编号不能为空")
    @Length(max = 32, message = "角色编号长度超出限制")
    private String roleId;
    public static final String ROLE_ID = "roleId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    @Length(max = 50, message = "名称长度超出限制")
    private String roleName;
    public static final String ROLE_NAME = "roleName";

    /**
     * 编码
     */
    @Field
    @NotNull(message = "编码不能为空")
    @Length(max = 25, message = "编码长度超出限制")
    private String roleCode;
    public static final String ROLE_CODE = "roleCode";

    /**
     * 描述
     */
    @Field
    @NotNull(message = "描述不能为空")
    @Length(max = 200, message = "描述长度超出限制")
    private String roleDescription;
    public static final String ROLE_DESCRIPTION = "roleDescription";

    /**
     * 排序
     */
    @Field
    @NotNull(message = "排序不能为空")
    @Digits(integer = 11, fraction = 0, message = "排序长度超出限制")
    private Integer roleSort;
    public static final String ROLE_SORT = "roleSort";


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