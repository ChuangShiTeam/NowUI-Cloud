package com.nowui.cloud.base.menu.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 菜单
 *
 * @author marcus
 *
 * 2018-03-14
 */
@Component

@TableName(value = "menu_info")
public class Menu extends BaseEntity {

    /**
     * 菜单编号
     */
    @TableId
    @TableField
    private String menuId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 父级ID
     */
    @TableField
    private String menuParentId;

    /**
     * 路径
     */
    @TableField
    private String menuParentPath;

    /**
     * 名称
     */
    @TableField
    private String menuName;

    /**
     * 图片
     */
    @TableField
    private String menuImage;

    /**
     * 地址
     */
    @TableField
    private String menuUrl;

    /**
     * 排序
     */
    @TableField
    private Integer menuSort;


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