package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章分类关联视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_article_category_map")
public class ArticleArticleCategoryView extends BaseView {

    /**
     * 文章文章分类关联编号
     */
    @Field
    private String articleArticleCategoryId;
    public static final String ARTICLE_ARTICLE_CATEGORY_ID = "articleArticleCategoryId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章编号
     */
    @Field
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 文章分类编号
     */
    @Field
    private String articleCategoryId;
    public static final String ARTICLE_CATEGORY_ID = "articleCategoryId";

    /**
     * 是否主分类
     */
    @Field
    private Boolean articleCategoryIsPrimary;
    public static final String ARTICLE_CATEGORY_IS_PRIMARY = "articleCategoryIsPrimary";


    public String getArticleArticleCategoryId() {
        return getString(ARTICLE_ARTICLE_CATEGORY_ID);
    }

    public void setArticleArticleCategoryId(String articleArticleCategoryId) {
        put(ARTICLE_ARTICLE_CATEGORY_ID, articleArticleCategoryId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getArticleId() {
        return getString(ARTICLE_ID);
    }

    public void setArticleId(String articleId) {
        put(ARTICLE_ID, articleId);
    }

    public String getArticleCategoryId() {
        return getString(ARTICLE_CATEGORY_ID);
    }

    public void setArticleCategoryId(String articleCategoryId) {
        put(ARTICLE_CATEGORY_ID, articleCategoryId);
    }

    public Boolean getArticleCategoryIsPrimary() {
        return getBoolean(ARTICLE_CATEGORY_IS_PRIMARY);
    }

    public void setArticleCategoryIsPrimary(Boolean articleCategoryIsPrimary) {
        put(ARTICLE_CATEGORY_IS_PRIMARY, articleCategoryIsPrimary);
    }


}