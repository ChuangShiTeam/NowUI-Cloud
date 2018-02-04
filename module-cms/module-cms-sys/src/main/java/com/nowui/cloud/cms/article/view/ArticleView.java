package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_info")
public class ArticleView extends BaseView {

    /**
     * 文章编号
     */
    @KeyId
    @Field
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 标题
     */
    @Field
    private String articleTitle;
    public static final String ARTICLE_TITLE = "articleTitle";

    /**
     * 作者
     */
    @Field
    private String articleAuthor;
    public static final String ARTICLE_AUTHOR = "articleAuthor";

    /**
     * 来源
     */
    @Field
    private String articleSource;
    public static final String ARTICLE_SOURCE = "articleSource";

    /**
     * 文章多媒体
     */
    @Field
    private String articleMediaId;
    public static final String ARTICLE_MEDIA_ID = "articleMediaId";

    /**
     * 文章多媒体类型
     */
    @Field
    private String articleMediaType;
    public static final String ARTICLE_MEDIA_TYPE = "articleMediaType";

    /**
     * 摘要
     */
    @Field
    private String articleSummary;
    public static final String ARTICLE_SUMMARY = "articleSummary";

    /**
     * 内容
     */
    @Field
    private String articleContent;
    public static final String ARTICLE_CONTENT = "articleContent";

    /**
     * 标签
     */
    @Field
    private String articleTags;
    public static final String ARTICLE_TAGS = "articleTags";

    /**
     * 关键字
     */
    @Field
    private String articleKeywords;
    public static final String ARTICLE_KEYWORDS = "articleKeywords";

    /**
     * 外部链接
     */
    @Field
    private String articleOuterLink;
    public static final String ARTICLE_OUTER_LINK = "articleOuterLink";

    /**
     * 是否外部链接
     */
    @Field
    private Boolean articleIsOuterLink;
    public static final String ARTICLE_IS_OUTER_LINK = "articleIsOuterLink";

    /**
     * 是否允许评论
     */
    @Field
    private Boolean articleIsAllowComment;
    public static final String ARTICLE_IS_ALLOW_COMMENT = "articleIsAllowComment";

    /**
     * 发布时间
     */
    @Field
    private Date articlePublishTime;
    public static final String ARTICLE_PUBLISH_TIME = "articlePublishTime";

    /**
     * 是否置顶
     */
    @Field
    private Boolean articleIsTop;
    public static final String ARTICLE_IS_TOP = "articleIsTop";

    /**
     * 置顶级别
     */
    @Field
    private Integer articleTopLevel;
    public static final String ARTICLE_TOP_LEVEL = "articleTopLevel";

    /**
     * 置顶结束时间
     */
    @Field
    private Date articleTopEndTime;
    public static final String ARTICLE_TOP_END_TIME = "articleTopEndTime";

    /**
     * 是否草稿
     */
    @Field
    private Boolean articleIsDraft;
    public static final String ARTICLE_IS_DRAFT = "articleIsDraft";

    /**
     * 权重
     */
    @Field
    private Integer articleWeight;
    public static final String ARTICLE_WEIGHT = "articleWeight";

    /**
     * 是否推荐
     */
    @Field
    private Boolean articleIsRecommend;
    public static final String ARTICLE_IS_RECOMMEND = "articleIsRecommend";

    /**
     * 是否需要审核
     */
    @Field
    private Boolean articleIsRequireAudit;
    public static final String ARTICLE_IS_REQUIRE_AUDIT = "articleIsRequireAudit";

    /**
     * 排序
     */
    @Field
    private Integer articleSort;
    public static final String ARTICLE_SORT = "articleSort";


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

    public String getArticleSource() {
        return getString(ARTICLE_SOURCE);
    }

    public void setArticleSource(String articleSource) {
        put(ARTICLE_SOURCE, articleSource);
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

    public String getArticleTags() {
        return getString(ARTICLE_TAGS);
    }

    public void setArticleTags(String articleTags) {
        put(ARTICLE_TAGS, articleTags);
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

    public Integer getArticleWeight() {
        return getInteger(ARTICLE_WEIGHT);
    }

    public void setArticleWeight(Integer articleWeight) {
        put(ARTICLE_WEIGHT, articleWeight);
    }

    public Boolean getArticleIsRecommend() {
        return getBoolean(ARTICLE_IS_RECOMMEND);
    }

    public void setArticleIsRecommend(Boolean articleIsRecommend) {
        put(ARTICLE_IS_RECOMMEND, articleIsRecommend);
    }

    public Boolean getArticleIsRequireAudit() {
        return getBoolean(ARTICLE_IS_REQUIRE_AUDIT);
    }

    public void setArticleIsRequireAudit(Boolean articleIsRequireAudit) {
        put(ARTICLE_IS_REQUIRE_AUDIT, articleIsRequireAudit);
    }

    public Integer getArticleSort() {
        return getInteger(ARTICLE_SORT);
    }

    public void setArticleSort(Integer articleSort) {
        put(ARTICLE_SORT, articleSort);
    }


}