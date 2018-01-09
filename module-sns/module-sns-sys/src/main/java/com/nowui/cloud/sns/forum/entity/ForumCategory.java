package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
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
@Document(indexName = "nowui", type = "forum_category_info")
@TableName(value = "forum_category_info")
public class ForumCategory extends BaseEntity {

    /**
     * 论坛分类id
     */
	@Id
    @TableId
    @NotNull(message = "论坛分类id不能为空")
    @Length(max = 32, message = "论坛分类id长度超出限制")
    private String forumCategoryId;
    public static final String FORUM_CATEGORY_ID = "forumCategoryId";

    /**
     * 应用Id
     */
    @Field
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛分类名称
     */
    @Field
    @TableField
    @NotNull(message = "论坛分类名称不能为空")
    @Length(max = 32, message = "论坛分类名称长度超出限制")
    private String forumCategoryName;
    public static final String FORUM_CATEGORY_NAME = "forumCategoryName";

    /**
     * 论坛分类缩略图
     */
    @Field
    @TableField
    @NotNull(message = "论坛分类缩略图不能为空")
    @Length(max = 500, message = "论坛分类缩略图长度超出限制")
    private String forumCategoryThumb;
    public static final String FORUM_CATEGORY_THUMB = "forumCategoryThumb";

    /**
     * 论坛分类排序
     */
    @Field
    @TableField
    @NotNull(message = "论坛分类排序不能为空")
    @Length(max = 11, message = "论坛分类排序长度超出限制")
    private Integer forumCategorySort;
    public static final String FORUM_CATEGORY_SORT = "forumCategorySort";

    /**
     * 论坛分类是否启用
     */
    @Field
    @TableField
    @NotNull(message = "论坛分类是否启用不能为空")
    @Length(max = 4, message = "论坛分类是否启用长度超出限制")
    private Boolean forumCategoryEnabled;
    public static final String FORUM_CATEGORY_ENABLED = "forumCategoryEnabled";

    /**
     * 是否推荐
     */
    @Field
    @TableField
    @NotNull(message = "是否推荐不能为空")
    @Length(max = 1, message = "是否推荐长度超出限制")
    private Boolean forumCategoryRecommand;
    public static final String FORUM_CATEGORY_RECOMMAND = "forumCategoryRecommand";


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

    public Integer getForumCategorySort() {
        return getInteger(FORUM_CATEGORY_SORT);
    }
    
    public void setForumCategorySort(Integer forumCategorySort) {
        put(FORUM_CATEGORY_SORT, forumCategorySort);
    }

    public Boolean getForumCategoryEnabled() {
        return getBoolean(FORUM_CATEGORY_ENABLED);
    }
    
    public void setForumCategoryEnabled(Boolean forumCategoryEnabled) {
        put(FORUM_CATEGORY_ENABLED, forumCategoryEnabled);
    }

    public Boolean getForumCategoryRecommand() {
        return getBoolean(FORUM_CATEGORY_RECOMMAND);
    }
    
    public void setForumCategoryRecommand(Boolean forumCategoryRecommand) {
        put(FORUM_CATEGORY_RECOMMAND, forumCategoryRecommand);
    }


}