package com.nowui.cloud.cms.navigationBar.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 导航栏
 *
 * @author shawn
 *
 * 2017-12-31
 */
@Component
@TableName(value = "navigation_bar_info")
public class NavigationBar extends BaseEntity {

    /**
     * 导航栏编号
     */
    @TableId
    @NotNull(message = "导航栏编号不能为空")
    @Length(max = 32, message = "导航栏编号长度超出限制")
    private String navigationBarId;
    public static final String NAVIGATION_BAR_ID = "navigationBarId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 导航栏分类编码
     */
    @TableField
    @NotNull(message = "导航栏分类编码不能为空")
    @Length(max = 50, message = "导航栏分类编码长度超出限制")
    private String navigationBarCategoryCode;
    public static final String NAVIGATION_BAR_CATEGORY_CODE = "navigationBarCategoryCode";

    /**
     * 导航栏编码
     */
    @TableField
    @NotNull(message = "导航栏编码不能为空")
    @Length(max = 50, message = "导航栏编码长度超出限制")
    private String navigationBarCode;
    public static final String NAVIGATION_BAR_CODE = "navigationBarCode";

    /**
     * 导航栏名称
     */
    @TableField
    @NotNull(message = "导航栏名称不能为空")
    @Length(max = 50, message = "导航栏名称长度超出限制")
    private String navigationBarName;
    public static final String NAVIGATION_BAR_NAME = "navigationBarName";

    /**
     * 导航栏图片
     */
    @TableField
    @NotNull(message = "导航栏图片不能为空")
    @Length(max = 32, message = "导航栏图片长度超出限制")
    private String navigationBarImage;
    public static final String NAVIGATION_BAR_IMAGE = "navigationBarImage";

    /**
     * 导航栏链接
     */
    @TableField
    @NotNull(message = "导航栏链接不能为空")
    @Length(max = 200, message = "导航栏链接长度超出限制")
    private String navigationBarUrl;
    public static final String NAVIGATION_BAR_URL = "navigationBarUrl";

    /**
     * 导航栏位置
     */
    @TableField
    @NotNull(message = "导航栏位置不能为空")
    @Length(max = 200, message = "导航栏位置长度超出限制")
    private String navigationBarPosition;
    public static final String NAVIGATION_BAR_POSITION = "navigationBarPosition";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    @Length(max = 11, message = "排序长度超出限制")
    private Integer navigationBarSort;
    public static final String NAVIGATION_BAR_SORT = "navigationBarSort";


    public String getNavigationBarId() {
        return getString(NAVIGATION_BAR_ID);
    }

    public void setNavigationBarId(String navigationBarId) {
        put(NAVIGATION_BAR_ID, navigationBarId);
    }
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    public String getNavigationBarCategoryCode() {
        return getString(NAVIGATION_BAR_CATEGORY_CODE);
    }

    public void setNavigationBarCategoryCode(String navigationBarCategoryCode) {
        put(NAVIGATION_BAR_CATEGORY_CODE, navigationBarCategoryCode);
    }
    public String getNavigationBarCode() {
        return getString(NAVIGATION_BAR_CODE);
    }

    public void setNavigationBarCode(String navigationBarCode) {
        put(NAVIGATION_BAR_CODE, navigationBarCode);
    }
    public String getNavigationBarName() {
        return getString(NAVIGATION_BAR_NAME);
    }

    public void setNavigationBarName(String navigationBarName) {
        put(NAVIGATION_BAR_NAME, navigationBarName);
    }
    public String getNavigationBarImage() {
        return getString(NAVIGATION_BAR_IMAGE);
    }

    public void setNavigationBarImage(String navigationBarImage) {
        put(NAVIGATION_BAR_IMAGE, navigationBarImage);
    }
    public String getNavigationBarUrl() {
        return getString(NAVIGATION_BAR_URL);
    }

    public void setNavigationBarUrl(String navigationBarUrl) {
        put(NAVIGATION_BAR_URL, navigationBarUrl);
    }
    public String getNavigationBarPosition() {
        return getString(NAVIGATION_BAR_POSITION);
    }

    public void setNavigationBarPosition(String navigationBarPosition) {
        put(NAVIGATION_BAR_POSITION, navigationBarPosition);
    }
    public Integer getNavigationBarSort() {
        return getInteger(NAVIGATION_BAR_SORT);
    }

    public void setNavigationBarSort(String navigationBarSort) {
        put(NAVIGATION_BAR_SORT, navigationBarSort);
    }

}