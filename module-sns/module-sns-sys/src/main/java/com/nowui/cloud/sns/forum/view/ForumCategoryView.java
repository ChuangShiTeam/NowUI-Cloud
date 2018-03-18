package com.nowui.cloud.sns.forum.view;

import com.nowui.cloud.annotation.KeyId;
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
    @KeyId
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
    
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getForumCategoryId() {
		return forumCategoryId;
	}
	public void setForumCategoryId(String forumCategoryId) {
		this.forumCategoryId = forumCategoryId;
	}
	public String getForumCategoryName() {
		return forumCategoryName;
	}
	public void setForumCategoryName(String forumCategoryName) {
		this.forumCategoryName = forumCategoryName;
	}
	public String getForumCategoryThumb() {
		return forumCategoryThumb;
	}
	public void setForumCategoryThumb(String forumCategoryThumb) {
		this.forumCategoryThumb = forumCategoryThumb;
	}
	public Boolean getForumCategoryIsActive() {
		return forumCategoryIsActive;
	}
	public void setForumCategoryIsActive(Boolean forumCategoryIsActive) {
		this.forumCategoryIsActive = forumCategoryIsActive;
	}
	public Boolean getForumCategoryIsRecommend() {
		return forumCategoryIsRecommend;
	}
	public void setForumCategoryIsRecommend(Boolean forumCategoryIsRecommend) {
		this.forumCategoryIsRecommend = forumCategoryIsRecommend;
	}
	public Integer getForumCategorySort() {
		return forumCategorySort;
	}
	public void setForumCategorySort(Integer forumCategorySort) {
		this.forumCategorySort = forumCategorySort;
	}
	
	

    
    


}