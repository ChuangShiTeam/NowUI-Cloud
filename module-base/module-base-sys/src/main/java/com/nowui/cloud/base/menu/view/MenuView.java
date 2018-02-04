package com.nowui.cloud.base.menu.view;

import javax.validation.constraints.NotNull;

import com.nowui.cloud.annotation.KeyId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.view.BaseView;

/**
 * 菜单视图
 *
 * @author marcus
 *
 * 2018-02-04
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
    private String menuId;
    public static final String MENU_ID = "menuId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 父级ID
     */
    @Field
    @NotNull(message = "父级ID不能为空")
    private String menuParentId;
    public static final String MENU_PARENT_ID = "menuParentId";

    /**
     * 路径
     */
    @Field
    @NotNull(message = "路径不能为空")
    private String menuParentPath;
    public static final String MENU_PARENT_PATH = "menuParentPath";

    /**
     * 名称
     */
    @Field
    @NotNull(message = "名称不能为空")
    private String menuName;
    public static final String MENU_NAME = "menuName";

    /**
     * 图片
     */
    @Field
    @NotNull(message = "图片不能为空")
    private String menuImage;
    public static final String MENU_IMAGE = "menuImage";

    /**
     * 地址
     */
    @Field
    @NotNull(message = "地址不能为空")
    private String menuUrl;
    public static final String MENU_URL = "menuUrl";

    /**
     * 排序
     */
    @Field
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