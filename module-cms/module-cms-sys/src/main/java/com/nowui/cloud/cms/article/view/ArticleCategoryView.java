package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章分类视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_category_info")
public class ArticleCategoryView extends BaseView {

    /**
     * 文章分类编号
     */
    @KeyId
    @Field
    private String articleCategoryId;
    public static final String ARTICLE_CATEGORY_ID = "articleCategoryId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 父级编号
     */
    @Field
    private String articleCategoryParentId;
    public static final String ARTICLE_CATEGORY_PARENT_ID = "articleCategoryParentId";

    /**
     * 
     */
    @Field
    private String articleCategoryParentPath;
    public static final String ARTICLE_CATEGORY_PARENT_PATH = "articleCategoryParentPath";

    /**
     * 分类名称
     */
    @Field
    private String articleCategoryName;
    public static final String ARTICLE_CATEGORY_NAME = "articleCategoryName";

    /**
     * 文章分类编码
     */
    @Field
    private String articleCategoryCode;
    public static final String ARTICLE_CATEGORY_CODE = "articleCategoryCode";

    /**
     * 关键字
     */
    @Field
    private String articleCategoryKeywords;
    public static final String ARTICLE_CATEGORY_KEYWORDS = "articleCategoryKeywords";

    /**
     * 描述
     */
    @Field
    private String articleCategoryDescription;
    public static final String ARTICLE_CATEGORY_DESCRIPTION = "articleCategoryDescription";

    /**
     * 排序
     */
    @Field
    private Integer articleCategorySort;
    public static final String ARTICLE_CATEGORY_SORT = "articleCategorySort";


    public String getArticleCategoryId() {
        return getString(ARTICLE_CATEGORY_ID);
    }

    public void setArticleCategoryId(String articleCategoryId) {
        put(ARTICLE_CATEGORY_ID, articleCategoryId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getArticleCategoryParentId() {
        return getString(ARTICLE_CATEGORY_PARENT_ID);
    }

    public void setArticleCategoryParentId(String articleCategoryParentId) {
        put(ARTICLE_CATEGORY_PARENT_ID, articleCategoryParentId);
    }

    public String getArticleCategoryParentPath() {
        return getString(ARTICLE_CATEGORY_PARENT_PATH);
    }

    public void setArticleCategoryParentPath(String articleCategoryParentPath) {
        put(ARTICLE_CATEGORY_PARENT_PATH, articleCategoryParentPath);
    }

    public String getArticleCategoryName() {
        return getString(ARTICLE_CATEGORY_NAME);
    }

    public void setArticleCategoryName(String articleCategoryName) {
        put(ARTICLE_CATEGORY_NAME, articleCategoryName);
    }

    public String getArticleCategoryCode() {
        return getString(ARTICLE_CATEGORY_CODE);
    }

    public void setArticleCategoryCode(String articleCategoryCode) {
        put(ARTICLE_CATEGORY_CODE, articleCategoryCode);
    }

    public String getArticleCategoryKeywords() {
        return getString(ARTICLE_CATEGORY_KEYWORDS);
    }

    public void setArticleCategoryKeywords(String articleCategoryKeywords) {
        put(ARTICLE_CATEGORY_KEYWORDS, articleCategoryKeywords);
    }

    public String getArticleCategoryDescription() {
        return getString(ARTICLE_CATEGORY_DESCRIPTION);
    }

    public void setArticleCategoryDescription(String articleCategoryDescription) {
        put(ARTICLE_CATEGORY_DESCRIPTION, articleCategoryDescription);
    }

    public Integer getArticleCategorySort() {
        return getInteger(ARTICLE_CATEGORY_SORT);
    }

    public void setArticleCategorySort(Integer articleCategorySort) {
        put(ARTICLE_CATEGORY_SORT, articleCategorySort);
    }


}