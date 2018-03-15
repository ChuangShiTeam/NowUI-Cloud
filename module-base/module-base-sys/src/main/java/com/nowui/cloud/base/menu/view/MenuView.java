package com.nowui.cloud.base.menu.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 菜单视图
 *
 * @author marcus
 *
 * 2018-03-14
 */
@Component
@Document(collection = "menu_info")
public class MenuView extends BaseView {

    /**
     * 菜单编号
     */
    @KeyId
    @Field
    @NotNull(message = "菜单编号不能为空")
    @Length(max = 32, message = "菜单编号长度超出限制")
    private String menuId;
    public static final String MENU_ID = "menuId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 父级ID
     */
    @Field
    @NotNull(message = "父级ID不能为空")
    @Length(max = 32, message = "父级ID长度超出限制")
    private String menuParentId;
    public static final String MENU_PARENT_ID = "menuParentId";

    /**
     * 路径
     */
    @Field
    @NotNull(message = "路径不能为空")
    @Length(max = 2000, message = "路径长度超出限制")
    private String menuParentPath;
    public static final String MENU_PARENT_PATH = "menuParentPath";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    @Length(max = 30, message = "名称长度超出限制")
    private String menuName;
    public static final String MENU_NAME = "menuName";

    /**
     * 图片
     */
    @Field
    @NotNull(message = "图片不能为空")
    @Length(max = 32, message = "图片长度超出限制")
    private String menuImage;
    public static final String MENU_IMAGE = "menuImage";
    
    /**
     * 地址
     */
    @Field
    @NotNull(message = "地址不能为空")
    @Length(max = 200, message = "地址长度超出限制")
    private String menuUrl;
    public static final String MENU_URL = "menuUrl";

    /**
     * 排序
     */
    @Field
    @NotNull(message = "排序不能为空")
    @Digits(integer = 11, fraction = 0, message = "排序长度超出限制")
    private Integer menuSort;
    public static final String MENU_SORT = "menuSort";


    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }
    
    public String getMenuParentPath() {
        return menuParentPath;
    }

    public void setMenuParentPath(String menuParentPath) {
        this.menuParentPath = menuParentPath;
    }
    
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
    
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    
    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }
    

}