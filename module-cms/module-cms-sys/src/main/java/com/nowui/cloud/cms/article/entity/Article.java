package com.nowui.cloud.cms.article.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Component(value = "article")
@TableName(value = "article_info")
public class Article extends BaseEntity {
    
    /**
     * 文章编号
     */
    @Id
    @TableId
    @NotNull(message = "文章编号不能为空")
    @Length(max = 32, message = "文章编号长度超出限制")
    private String articleId;
    public static final String ARTICLE_ID = "articleId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 文章标题
     */
    @TableField
    @NotNull(message = "文章标题不能为空")
    @Length(max = 200, message = "文章标题长度超出限制")
    private String articleTitle;
    public static final String ARTICLE_TITLE = "articleTitle";
    
    /**
     * 文章作者
     */
    @TableField
    @NotNull(message = "文章作者不能为空")
    @Length(max = 50, message = "文章作者长度超出限制")
    private String articleAuthor;
    public static final String ARTICLE_AUTHOR = "articleAuthor";
    
    /**
     * 文章来源
     */
    @TableField
    @NotNull(message = "文章来源不能为空")
    @Length(max = 200, message = "文章来源长度超出限制")
    private String articleSource;
    public static final String ARTICLE_SOURCE = "articleSource";

    /**
     * 文章媒体
     */
    @TableField(exist = false)
    @NotNull(message = "文章媒体不能为空")
    private JSONObject articleMedia;
    public static final String ARTICLE_MEDIA = "articleMedia";
    
    /**
     * 文章媒体
     */
    @TableField
    @NotNull(message = "文章媒体不能为空")
    @Length(max = 32, message = "文章媒体长度超出限制")
    private String articleMediaId;
    public static final String ARTICLE_MEDIA_ID = "articleMediaId";
    
    /**
     * 文章媒体类型
     */
    @TableField
    @NotNull(message = "文章媒体类型不能为空")
    @Length(max = 32, message = "文章媒体类型长度超出限制")
    private String articleMediaType;
    public static final String ARTICLE_MEDIA_TYPE = "articleMediaType";
    
    /**
     * 文章摘要
     */
    @TableField
    @NotNull(message = "文章摘要不能为空")
    @Length(max = 250, message = "文章摘要长度超出限制")
    private String articleSummary;
    public static final String ARTICLE_SUMMARY = "articleSummary";
    
    /**
     * 文章内容
     */
    @TableField
    @NotNull(message = "文章内容不能为空")
    private String articleContent;
    public static final String ARTICLE_CONTENT = "articleContent";
    
    /**
     * 文章标签
     */
    @TableField
    @NotNull(message = "文章标签不能为空")
    @Length(max = 250, message = "文章标签长度超出限制")
    private String articleTags;
    public static final String ARTICLE_TAGS = "articleTags";
    
    /**
     * 文章关键字
     */
    @TableField
    @NotNull(message = "文章关键字不能为空")
    @Length(max = 250, message = "文章关键字长度超出限制")
    private String articleKeywords;
    public static final String ARTICLE_KEYWORDS = "articleKeywords";
    
    /**
     * 文章外部链接
     */
    @TableField
    @NotNull(message = "文章外部链接不能为空")
    @Length(max = 200, message = "文章外部链接长度超出限制")
    private String articleOuterLink;
    public static final String ARTICLE_OUTER_LINK = "articleOuterLink";
    
    /**
     * 文章是否外部链接
     */
    @TableField
    @NotNull(message = "文章是否外部链接不能为空")
    private Boolean articleIsOuterLink;
    public static final String ARTICLE_IS_OUTER_LINK = "articleIsOuterLink";
    
    /**
     * 文章是否允许评论
     */
    @TableField
    @NotNull(message = "文章是否允许评论不能为空")
    private Boolean articleIsAllowComment;
    public static final String ARTICLE_IS_ALLOW_COMMENT = "articleIsAllowComment";
    
    /**
     * 文章发布时间
     */
    @TableField
    @NotNull(message = "文章发布时间不能为空")
    private Date articlePublishTime;
    public static final String ARTICLE_PUBLISH_TIME = "articlePublishTime";
    
    /**
     * 文章是否置顶
     */
    @TableField
    @NotNull(message = "文章是否置顶不能为空")
    private Boolean articleIsTop;
    public static final String ARTICLE_IS_TOP = "articleIsTop";
    
    /**
     * 文章置顶级别
     */
    @TableField
    private Integer articleTopLevel;
    public static final String ARTICLE_TOP_LEVEL = "articleTopLevel";
    
    /**
     * 文章置顶截止时间
     */
    @TableField
    private Date articleTopEndTime;
    public static final String ARTICLE_TOP_END_TIME = "articleTopEndTime";
    
    /**
     * 文章是否草稿
     */
    @TableField
    @NotNull(message = "文章是否草稿不能为空")
    private Boolean articleIsDraft;
    public static final String ARTICLE_IS_DRAFT = "articleIsDraft";
    
    /**
     * 文章权重
     */
    @TableField
    @NotNull(message = "文章权重不能为空")
    private Integer articleWeight;
    public static final String ARTICLE_WEIGHT = "articleWeight";
    
    /**
     * 文章是否需要审核
     */
    @TableField
    @NotNull(message = "文章是否需要审核不能为空")
    private Boolean articleIsRequireAudit;
    public static final String ARTICLE_IS_REQUIRE_AUDIT = "articleIsRequireAudit";
    
    /**
     * 文章是否推荐
     */
    @TableField
    @NotNull(message = "文章是否推荐不能为空")
    private Boolean articleIsRecommend;
    public static final String ARTICLE_IS_RECOMMEND = "articleIsRecommend";
    
    /**
     * 文章排序
     */
    @TableField
    @NotNull(message = "文章排序不能为空")
    private Integer articleSort;
    public static final String ARTICLE_SORT = "articleSort";
    
    public static final String ARTICLE_MEDIA_LIST = "articleMediaList";
    
    public static final String ARTICLE_ARTICLE_CATEGORY_LIST = "articleArticleCategoryList";
    
    public String getArticleId() {
        return getString(ARTICLE_ID);
    }
    
    public void setArticleId(String articleId) {
        put(ARTICLE_ID, articleId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getArticleTitle() {
        return getString(ARTICLE_TITLE);
    }
    
    public void setArticleTitle(String articleTitle) {
        put(ARTICLE_TITLE, articleTitle);
    }
    
    public String getArticleAuthor() {
        return getString(ARTICLE_AUTHOR);
    }
    
    public void setArticleAuthor(String articleAuthor) {
        put(ARTICLE_AUTHOR, articleAuthor);
    }

    public JSONObject getArticleMedia() {
        return getJSONObject(ARTICLE_MEDIA);
    }

    public void setArticleMedia(JSONObject articleMedia) {
        put(ARTICLE_MEDIA, articleMedia);
    }

    public String getArticleMediaId() {
        return getString(ARTICLE_MEDIA_ID);
    }
    
    public void setArticleMediaId(String articleMediaId) {
        put(ARTICLE_MEDIA_ID, articleMediaId);
    }
    
    public String getArticleMediaType() {
        return getString(ARTICLE_MEDIA_TYPE);
    }
    
    public void setArticleMediaType(String articleMediaType) {
        put(ARTICLE_MEDIA_TYPE, articleMediaType);
    }
    
    public String getArticleSummary() {
        return getString(ARTICLE_SUMMARY);
    }
    
    public void setArticleSummary(String articleSummary) {
        put(ARTICLE_SUMMARY, articleSummary);
    }
    
    public String getArticleContent() {
        return getString(ARTICLE_CONTENT);
    }
    
    public void setArticleContent(String articleContent) {
        put(ARTICLE_CONTENT, articleContent);
    }
    
    public String getArticleKeywords() {
        return getString(ARTICLE_KEYWORDS);
    }
    
    public void setArticleKeywords(String articleKeywords) {
        put(ARTICLE_KEYWORDS, articleKeywords);
    }
    
    public String getArticleOuterLink() {
        return getString(ARTICLE_OUTER_LINK);
    }
    
    public void setArticleOuterLink(String articleOuterLink) {
        put(ARTICLE_OUTER_LINK, articleOuterLink);
    }
    
    public Boolean getArticleIsOuterLink() {
        return getBoolean(ARTICLE_IS_OUTER_LINK);
    }
    
    public void setArticleIsOuterLink(Boolean articleIsOuterLink) {
        put(ARTICLE_IS_OUTER_LINK, articleIsOuterLink);
    }
    
    public Boolean getArticleIsAllowComment() {
        return getBoolean(ARTICLE_IS_ALLOW_COMMENT);
    }
    
    public void setArticleIsAllowComment(Boolean articleIsAllowComment) {
        put(ARTICLE_IS_ALLOW_COMMENT, articleIsAllowComment);
    }
    
    public Date getArticlePublishTime() {
        return getDate(ARTICLE_PUBLISH_TIME);
    }
    
    public void setArticlePublishTime(Date articlePublishTime) {
        put(ARTICLE_PUBLISH_TIME, articlePublishTime);
    }
    
    public Boolean getArticleIsTop() {
        return getBoolean(ARTICLE_IS_TOP);
    }
    
    public void setArticleIsTop(Boolean articleIsTop) {
        put(ARTICLE_IS_TOP, articleIsTop);
    }
    
    public Integer getArticleTopLevel() {
        return getInteger(ARTICLE_TOP_LEVEL);
    }
    
    public void setArticleTopLevel(Integer articleTopLevel) {
        put(ARTICLE_TOP_LEVEL, articleTopLevel);
    }
    
    public Date getArticleTopEndTime() {
        return getDate(ARTICLE_TOP_END_TIME);
    }
    
    public void setArticleTopEndTime(Date articleTopEndTime) {
        put(ARTICLE_TOP_END_TIME, articleTopEndTime);
    }
    
    public Boolean getArticleIsDraft() {
        return getBoolean(ARTICLE_IS_DRAFT);
    }
    
    public void setArticleIsDraft(Boolean articleIsDraft) {
        put(ARTICLE_IS_DRAFT, articleIsDraft);
    }
    
    public Integer getArticleSort() {
        return getInteger(ARTICLE_SORT);
    }
    
    public void setArticleSort(Integer articleSort) {
        put(ARTICLE_SORT, articleSort);
    }
    
    public String getArticleSource() {
        return getString(ARTICLE_SOURCE);
    }

    public void setArticleSource(String articleSource) {
        put(ARTICLE_SOURCE, articleSource);
    }

    public String getArticleTags() {
        return getString(ARTICLE_TAGS);
    }

    public void setArticleTags(String articleTags) {
        put(ARTICLE_TAGS, articleTags);
    }

    public Integer getArticleWeight() {
        return getInteger(ARTICLE_WEIGHT);
    }

    public void setArticleWeight(Integer articleWeight) {
        put(ARTICLE_WEIGHT, articleWeight);
    }

    public Boolean getArticleIsRequireAudit() {
        return getBoolean(ARTICLE_IS_REQUIRE_AUDIT);
    }

    public void setArticleIsRequireAudit(Boolean articleIsRequireAudit) {
        put(ARTICLE_IS_REQUIRE_AUDIT, articleIsRequireAudit);
    }
    
    public Boolean getArticleIsarticleIsRecommend() {
        return getBoolean(ARTICLE_IS_RECOMMEND);
    }
    
    public void articleIsRecommend(Boolean articleIsRecommend) {
        put(ARTICLE_IS_RECOMMEND, articleIsRecommend);
    }

}
