package com.nowui.cloud.base.menu.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 菜单
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component
@TableName(value = "menu_info")
public class Menu extends BaseEntity {

    /**
     * 菜单编号
     */
    @TableId
    @NotNull(message = "菜单编号不能为空")
    @Length(max = 32, message = "菜单编号长度超出限制")
    private String menuId;
    public static final String MENU_ID = "menuId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 父级ID
     */
    @TableField
    @NotNull(message = "父级ID不能为空")
    @Length(max = 32, message = "父级ID长度超出限制")
    private String menuParentId;
    public static final String MENU_PARENT_ID = "menuParentId";

    /**
     * 路径
     */
    @TableField
    @NotNull(message = "路径不能为空")
    @Length(max = 2000, message = "路径长度超出限制")
    private String menuParentPath;
    public static final String MENU_PARENT_PATH = "menuParentPath";

    /**
     * 名称
     */
    @TableField
    @NotNull(message = "名称不能为空")
    @Length(max = 30, message = "名称长度超出限制")
    private String menuName;
    public static final String MENU_NAME = "menuName";

    /**
     * 图片
     */
    @TableField
    @NotNull(message = "图片不能为空")
    @Length(max = 32, message = "图片长度超出限制")
    private String menuImage;
    public static final String MENU_IMAGE = "menuImage";

    /**
     * 地址
     */
    @TableField
    @NotNull(message = "地址不能为空")
    @Length(max = 200, message = "地址长度超出限制")
    private String menuUrl;
    public static final String MENU_URL = "menuUrl";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    private Integer menuSort;
    public static final String MENU_SORT = "menuSort";


    public String getMenuId() {
        return getString(MENU_ID);
    }

    public void setMenuId(String menuId) {
        put(MENU_ID, menuId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getMenuParentId() {
        return getString(MENU_PARENT_ID);
    }

    public void setMenuParentId(String menuParentId) {
        put(MENU_PARENT_ID, menuParentId);
    }
    
    public String getMenuParentPath() {
        return getString(MENU_PARENT_PATH);
    }

    public void setMenuParentPath(String menuParentPath) {
        put(MENU_PARENT_PATH, menuParentPath);
    }
    
    public String getMenuName() {
        return getString(MENU_NAME);
    }

    public void setMenuName(String menuName) {
        put(MENU_NAME, menuName);
    }
    
    public String getMenuImage() {
        return getString(MENU_IMAGE);
    }

    public void setMenuImage(String menuImage) {
        put(MENU_IMAGE, menuImage);
    }
    
    public String getMenuUrl() {
        return getString(MENU_URL);
    }

    public void setMenuUrl(String menuUrl) {
        put(MENU_URL, menuUrl);
    }
    
    public Integer getMenuSort() {
        return getInteger(MENU_SORT);
    }

    public void setMenuSort(Integer menuSort) {
        put(MENU_SORT, menuSort);
    }

}