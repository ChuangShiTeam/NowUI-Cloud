package com.nowui.cloud.cms.navigation.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 导航栏视图
 *
 * @author marcus
 *
 * 2018-03-04
 */
@Component
@Document(collection = "navigation_info")
public class NavigationView extends BaseView {

    /**
     * 导航栏编号
     */
    @KeyId
    @Field
    @NotNull(message = "导航栏编号不能为空")
    private String navigationId;
    public static final String NAVIGATION_ID = "navigationId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 导航栏分类编码
     */
    @Field
    @NotNull(message = "导航栏分类编码不能为空")
    private String navigationCategoryCode;
    public static final String NAVIGATION_CATEGORY_CODE = "navigationCategoryCode";

    /**
     * 导航栏编码
     */
    @Field
    @NotNull(message = "导航栏编码不能为空")
    private String navigationCode;
    public static final String NAVIGATION_CODE = "navigationCode";

    /**
     * 导航栏名称
     */
    @Field
    @NotNull(message = "导航栏名称不能为空")
    private String navigationName;
    public static final String NAVIGATION_NAME = "navigationName";

    /**
     * 导航栏图片文件编号
     */
    @Field
    @NotNull(message = "导航栏图片文件编号不能为空")
    private String navigationImageFileId;
    public static final String NAVIGATION_IMAGE_FILE_ID = "navigationImageFileId";

    /**
     * 导航栏链接
     */
    @Field
    @NotNull(message = "导航栏链接不能为空")
    private String navigationUrl;
    public static final String NAVIGATION_URL = "navigationUrl";

    /**
     * 导航栏位置
     */
    @Field
    @NotNull(message = "导航栏位置不能为空")
    private String navigationPosition;
    public static final String NAVIGATION_POSITION = "navigationPosition";

    /**
     * 排序
     */
    @Field
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

    public void setNavigationImageFileId(String navigationImageFileId) {
        put(NAVIGATION_IMAGE_FILE_ID, navigationImageFileId);
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