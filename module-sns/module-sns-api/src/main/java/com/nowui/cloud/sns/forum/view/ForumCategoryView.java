package com.nowui.cloud.sns.forum.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 论坛分类视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "forum_category_info")
public class ForumCategoryView extends BaseView {

    /**
     * 论坛分类编号
     */
    @Field
    @NotNull(message = "论坛分类编号不能为空")
    private String forumCategoryId;
    public static final String FORUM_CATEGORY_ID = "forumCategoryId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛分类名称
     */
    @Field
    @NotNull(message = "论坛分类名称不能为空")
    private String forumCategoryName;
    public static final String FORUM_CATEGORY_NAME = "forumCategoryName";

    /**
     * 论坛分类缩略图
     */
    @Field
    @NotNull(message = "论坛分类缩略图不能为空")
    private String forumCategoryThumb;
    public static final String FORUM_CATEGORY_THUMB = "forumCategoryThumb";

    /**
     * 是否生效
     */
    @Field
    @NotNull(message = "是否生效不能为空")
    private Boolean forumCategoryIsActive;
    public static final String FORUM_CATEGORY_IS_ACTIVE = "forumCategoryIsActive";

    /**
     * 是否推荐
     */
    @Field
    @NotNull(message = "是否推荐不能为空")
    private Boolean forumCategoryIsRecommend;
    public static final String FORUM_CATEGORY_IS_RECOMMEND = "forumCategoryIsRecommend";

    /**
     * 论坛分类排序
     */
    @Field
    @NotNull(message = "论坛分类排序不能为空")
    private Integer forumCategorySort;
    public static final String FORUM_CATEGORY_SORT = "forumCategorySort";


    public String getForumCategoryId() {
        return getString(FORUM_CATEGORY_ID);
    }

    public void setForumCategoryId(String forumCategoryId) {
        put(FORUM_CATEGORY_ID, forumCategoryId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getForumCategoryName() {
        return getString(FORUM_CATEGORY_NAME);
    }

    public void setForumCategoryName(String forumCategoryName) {
        put(FORUM_CATEGORY_NAME, forumCategoryName);
    }

    public String getForumCategoryThumb() {
        return getString(FORUM_CATEGORY_THUMB);
    }

    public void setForumCategoryThumb(String forumCategoryThumb) {
        put(FORUM_CATEGORY_THUMB, forumCategoryThumb);
    }

    public Boolean getForumCategoryIsActive() {
        return getBoolean(FORUM_CATEGORY_IS_ACTIVE);
    }

    public void setForumCategoryIsActive(Boolean forumCategoryIsActive) {
        put(FORUM_CATEGORY_IS_ACTIVE, forumCategoryIsActive);
    }

    public Boolean getForumCategoryIsRecommend() {
        return getBoolean(FORUM_CATEGORY_IS_RECOMMEND);
    }

    public void setForumCategoryIsRecommend(Boolean forumCategoryIsRecommend) {
        put(FORUM_CATEGORY_IS_RECOMMEND, forumCategoryIsRecommend);
    }

    public Integer getForumCategorySort() {
        return getInteger(FORUM_CATEGORY_SORT);
    }

    public void setForumCategorySort(Integer forumCategorySort) {
        put(FORUM_CATEGORY_SORT, forumCategorySort);
    }


}