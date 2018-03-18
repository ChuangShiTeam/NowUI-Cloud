package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛分类
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "forum_category_info")
public class ForumCategory extends BaseEntity {

    /**
     * 论坛分类编号
     */
	@Id
    @TableId
    @NotNull(message = "论坛分类编号不能为空")
    @Length(max = 32, message = "论坛分类编号长度超出限制")
    private String forumCategoryId;
    public static final String FORUM_CATEGORY_ID = "forumCategoryId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛分类名称
     */
    @TableField
    @NotNull(message = "论坛分类名称不能为空")
    @Length(max = 32, message = "论坛分类名称长度超出限制")
    private String forumCategoryName;
    public static final String FORUM_CATEGORY_NAME = "forumCategoryName";

    /**
     * 论坛分类缩略图
     */
    @TableField
    @NotNull(message = "论坛分类缩略图不能为空")
    @Length(max = 500, message = "论坛分类缩略图长度超出限制")
    private String forumCategoryThumb;
    public static final String FORUM_CATEGORY_THUMB = "forumCategoryThumb";

    /**
     * 是否生效
     */
    @TableField
    @NotNull(message = "是否生效不能为空")
    private Boolean forumCategoryIsActive;
    public static final String FORUM_CATEGORY_IS_ACTIVE = "forumCategoryIsActive";

    /**
     * 是否推荐
     */
    @TableField
    @NotNull(message = "是否推荐不能为空")
    private Boolean forumCategoryIsRecommend;
    public static final String FORUM_CATEGORY_IS_RECOMMEND = "forumCategoryIsRecommend";
    
    /**
     * 论坛分类排序
     */
    @TableField
    @NotNull(message = "论坛分类排序不能为空")
    @Length(max = 11, message = "论坛分类排序长度超出限制")
    private Integer forumCategorySort;
    public static final String FORUM_CATEGORY_SORT = "forumCategorySort";
	public String getForumCategoryId() {
		return forumCategoryId;
	}
	public void setForumCategoryId(String forumCategoryId) {
		this.forumCategoryId = forumCategoryId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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