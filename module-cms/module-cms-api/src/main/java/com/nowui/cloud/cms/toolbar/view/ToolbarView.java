package com.nowui.cloud.cms.toolbar.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 工具栏视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "toolbar_info")
public class ToolbarView extends BaseView {

    /**
     * 工具栏编号
     */
    @Field
    private String toolbarId;
    public static final String TOOLBAR_ID = "toolbarId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 工具栏名称
     */
    @Field
    private String toolbarName;
    public static final String TOOLBAR_NAME = "toolbarName";

    /**
     * 
     */
    @Field
    private String toolbarActiveImage;
    public static final String TOOLBAR_ACTIVE_IMAGE = "toolbarActiveImage";

    /**
     * 工具栏图片
     */
    @Field
    private String toolbarImage;
    public static final String TOOLBAR_IMAGE = "toolbarImage";

    /**
     * 
     */
    @Field
    private String toolbarUrl;
    public static final String TOOLBAR_URL = "toolbarUrl";

    /**
     * 排序
     */
    @Field
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