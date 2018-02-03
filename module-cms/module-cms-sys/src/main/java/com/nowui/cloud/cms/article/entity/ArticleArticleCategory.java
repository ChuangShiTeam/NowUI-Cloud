package com.nowui.cloud.cms.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章文章分类
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Component
@TableName(value = "article_article_category_map")
public class ArticleArticleCategory extends BaseEntity {

    /**
     * 文章文章分类关联编号
     */
    @Id
    @TableId
    @NotNull(message = "文章文章分类关联编号不能为空")
    @Length(max = 32, message = "文章文章分类关联编号长度超出限制")
    private String articleArticleCategoryId;
    public static final String ARTICLE_ARTICLE_CATEGORY_ID = "articleArticleCategoryId";

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
     * 文章编号
     */
    @Field
    @TableField
    @NotNull(message = "文章编号不能为空")
    @Length(max = 32, message = "文章编号长度超出限制")
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 文章分类编号
     */
    @Field
    @TableField
    @NotNull(message = "文章分类编号不能为空")
    @Length(max = 32, message = "文章分类编号长度超出限制")
    private String articleCategoryId;
    public static final String ARTICLE_CATEGORY_ID = "articleCategoryId";

    /**
     * 是否主分类
     */
    @Field
    @TableField
    @NotNull(message = "是否主分类不能为空")
    @Length(max = 1, message = "是否主分类长度超出限制")
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