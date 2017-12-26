package com.nowui.cloud.cms.toolbar.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 工具栏
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Component(value = "toolbar")
@TableName(value = "toolbar_info")
public class Toolbar extends BaseEntity {

    /**
     * 工具栏编号
     */
    @TableId
    @NotNull(message = "工具栏编号不能为空")
    @Length(max = 32, message = "工具栏编号长度超出限制")
    private String toolbarId;
    public static final String TOOLBAR_ID = "toolbarId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 工具栏名称
     */
    @TableField
    @NotNull(message = "工具栏名称不能为空")
    @Length(max = 50, message = "工具栏名称长度超出限制")
    private String toolbarName;
    public static final String TOOLBAR_NAME = "toolbarName";
    
    /**
     * 工具栏图片
     */
    @TableField
    @NotNull(message = "工具栏图片不能为空")
    @Length(max = 32, message = "工具栏图片长度超出限制")
    private String toolbarImage;
    public static final String TOOLBAR_IMAGE = "toolbarImage";
    
    /**
     * 工具栏排序
     */
    @TableField
    @NotNull(message = "工具栏排序不能为空")
    @Length(max = 11, message = "工具栏排序长度超出限制")
    private Integer toolbarSort;
    public static final String TOOLBAR_SORT = "toolbarSort";
    
    public String getToolbarId() {
        return getString(TOOLBAR_ID);
    }
    
    public void setToolbarId(String toolbarId) {
        put(TOOLBAR_ID, toolbarId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getToolbarName() {
        return getString(TOOLBAR_NAME);
    }
    
    public void setToolbarName(String toolbarName) {
        put(TOOLBAR_NAME, toolbarName);
    }
    
    public String getToolbarImage() {
        return getString(TOOLBAR_IMAGE);
    }
    
    public void setToolbarImage(String toolbarImage) {
        put(TOOLBAR_IMAGE, toolbarImage);
    }
    
    public Integer getToolbarSort() {
        return getInteger(TOOLBAR_SORT);
    }
    
    public void setToolbarSort(Integer toolbarSort) {
        put(TOOLBAR_SORT, toolbarSort);
    }
}
