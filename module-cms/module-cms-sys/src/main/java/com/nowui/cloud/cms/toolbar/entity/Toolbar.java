package com.nowui.cloud.cms.toolbar.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
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
@Document(indexName = "nowui", type = "toolbar_info")
@TableName(value = "toolbar_info")
public class Toolbar extends BaseEntity {

    /**
     * 工具栏编号
     */
    @Id
    @TableId
    @NotNull(message = "工具栏编号不能为空")
    @Length(max = 32, message = "工具栏编号长度超出限制")
    private String toolbarId;
    public static final String TOOLBAR_ID = "toolbarId";
    
    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 工具栏名称
     */
    @Field
    @TableField
    @NotNull(message = "工具栏名称不能为空")
    @Length(max = 50, message = "工具栏名称长度超出限制")
    private String toolbarName;
    public static final String TOOLBAR_NAME = "toolbarName";
    
    /**
     * 工具栏激活图片(选择)
     */
    @Field
    @TableField
    @NotNull(message = "工具栏激活图片不能为空")
    @Length(max = 32, message = "工具栏激活图片长度超出限制")
    private String toolbarActiveImage;
    public static final String TOOLBAR_ACTIVE_IMAGE = "toolbarActiveImage";
    
    /**
     * 工具栏图片
     */
    @Field
    @TableField
    @NotNull(message = "工具栏图片不能为空")
    @Length(max = 32, message = "工具栏图片长度超出限制")
    private String toolbarImage;
    public static final String TOOLBAR_IMAGE = "toolbarImage";
    
    
    /**
     * 工具栏地址
     */
    @Field
    @TableField
    @NotNull(message = "工具栏地址不能为空")
    @Length(max = 200, message = "工具栏地址长度超出限制")
    private String toolbarUrl;
    public static final String TOOLBAR_URL = "toolbarUrl";
    
    
    /**
     * 工具栏排序
     */
    @Field
    @TableField
    @NotNull(message = "工具栏排序不能为空")
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
    
    public String getToolbarActiveImage() {
        return getString(TOOLBAR_ACTIVE_IMAGE);
    }
    
    public void setToolbarActiveImage(String toolbarActiveImage) {
        put(TOOLBAR_ACTIVE_IMAGE, toolbarActiveImage);
    }
    
    public String getToolbarImage() {
        return getString(TOOLBAR_IMAGE);
    }
    
    public void setToolbarImage(String toolbarImage) {
        put(TOOLBAR_IMAGE, toolbarImage);
    }
    
    public String getToolbarUrl() {
        return getString(TOOLBAR_URL);
    }
    
    public void setToolbarUrl(String toolbarUrl) {
        put(TOOLBAR_URL, toolbarUrl);
    }
    
    public Integer getToolbarSort() {
        return getInteger(TOOLBAR_SORT);
    }
    
    public void setToolbarSort(Integer toolbarSort) {
        put(TOOLBAR_SORT, toolbarSort);
    }
}
