package com.nowui.cloud.cms.navigation.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 导航栏
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@TableName(value = "navigation_info")
public class Navigation extends BaseEntity {

    /**
     * 导航栏编号
     */
    @Id
    @TableId
    @NotNull(message = "导航栏编号不能为空")
    @Length(max = 32, message = "导航栏编号长度超出限制")
    private String navigationId;
    public static final String NAVIGATION_ID = "navigationId";

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
    private String navigationCategoryCode;
    public static final String NAVIGATION_CATEGORY_CODE = "navigationCategoryCode";

    /**
     * 导航栏编码
     */
    @TableField
    @NotNull(message = "导航栏编码不能为空")
    @Length(max = 50, message = "导航栏编码长度超出限制")
    private String navigationCode;
    public static final String NAVIGATION_CODE = "navigationCode";

    /**
     * 导航栏名称
     */
    @TableField
    @NotNull(message = "导航栏名称不能为空")
    @Length(max = 50, message = "导航栏名称长度超出限制")
    private String navigationName;
    public static final String NAVIGATION_NAME = "navigationName";

    /**
     * 导航栏图片文件编号
     */
    @TableField
    @NotNull(message = "导航栏图片文件编号不能为空")
    @Length(max = 32, message = "导航栏图片文件编号长度超出限制")
    private String navigationImageFileId;
    public static final String NAVIGATION_IMAGE_FILE_ID = "navigationImageFileId";
    
    /**
     * 导航栏图片文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "导航栏图片文件路径不能为空")
    private String navigationImageFilePath;
    public static final String NAVIGATION_IMAGE_FILE_PATH = "navigationImageFilePath";

    /**
     * 导航栏链接
     */
    @TableField
    @NotNull(message = "导航栏链接不能为空")
    @Length(max = 200, message = "导航栏链接长度超出限制")
    private String navigationUrl;
    public static final String NAVIGATION_URL = "navigationUrl";

    /**
     * 导航栏位置
     */
    @TableField
    @NotNull(message = "导航栏位置不能为空")
    @Length(max = 200, message = "导航栏位置长度超出限制")
    private String navigationPosition;
    public static final String NAVIGATION_POSITION = "navigationPosition";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    private Integer navigationSort;
    public static final String NAVIGATION_SORT = "navigationSort";


    public String getNavigationId() {
        return getString(NAVIGATION_ID);
    }

    public void setNavigationId(String navigationId) {
        put(NAVIGATION_ID, navigationId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getNavigationCategoryCode() {
        return getString(NAVIGATION_CATEGORY_CODE);
    }

    public void setNavigationCategoryCode(String navigationCategoryCode) {
        put(NAVIGATION_CATEGORY_CODE, navigationCategoryCode);
    }
    
    public String getNavigationCode() {
        return getString(NAVIGATION_CODE);
    }

    public void setNavigationCode(String navigationCode) {
        put(NAVIGATION_CODE, navigationCode);
    }
    
    public String getNavigationName() {
        return getString(NAVIGATION_NAME);
    }

    public void setNavigationName(String navigationName) {
        put(NAVIGATION_NAME, navigationName);
    }
    
    public String getNavigationImageFileId() {
        return getString(NAVIGATION_IMAGE_FILE_ID);
    }

    public void setNavigationImage(String navigationImageFileId) {
        put(NAVIGATION_IMAGE_FILE_ID, navigationImageFileId);
    }
    
    public String getNavigationImageFilePath() {
        return getString(NAVIGATION_IMAGE_FILE_PATH);
    }

    public void setNavigationImageFilePath(String navigationImageFilePath) {
        put(NAVIGATION_IMAGE_FILE_PATH, navigationImageFilePath);
    }
    
    public String getNavigationUrl() {
        return getString(NAVIGATION_URL);
    }

    public void setNavigationUrl(String navigationUrl) {
        put(NAVIGATION_URL, navigationUrl);
    }
    
    public String getNavigationPosition() {
        return getString(NAVIGATION_POSITION);
    }

    public void setNavigationPosition(String navigationPosition) {
        put(NAVIGATION_POSITION, navigationPosition);
    }
    
    public Integer getNavigationSort() {
        return getInteger(NAVIGATION_SORT);
    }

    public void setNavigationSort(Integer navigationSort) {
        put(NAVIGATION_SORT, navigationSort);
    }

}