package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章分类视图
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Component
@Document(collection = "article_category_info")
public class ArticleCategoryView extends BaseView {

    /**
     * 文章分类编号
     */
    @KeyId
    @Field
    @NotNull(message = "文章分类编号不能为空")
    @Length(max = 32, message = "文章分类编号长度超出限制")
    private String articleCategoryId;
    public static final String ARTICLE_CATEGORY_ID = "articleCategoryId";

    /**
     * 父级编号
     */
    @Field
    @NotNull(message = "父级编号不能为空")
    @Length(max = 32, message = "父级编号长度超出限制")
    private String articleCategoryParentId;
    public static final String ARTICLE_CATEGORY_PARENT_ID = "articleCategoryParentId";

    /**
     * 父级路径
     */
    @Field
    @NotNull(message = "父级路径不能为空")
    @Length(max = 2000, message = "父级路径长度超出限制")
    private String articleCategoryParentPath;
    public static final String ARTICLE_CATEGORY_PARENT_PATH = "articleCategoryParentPath";

    /**
     * 分类名称
     */
    @Field
    @NotNull(message = "分类名称不能为空")
    @Length(max = 100, message = "分类名称长度超出限制")
    private String articleCategoryName;
    public static final String ARTICLE_CATEGORY_NAME = "articleCategoryName";

    /**
     * 分类编码
     */
    @Field
    @NotNull(message = "分类编码不能为空")
    @Length(max = 100, message = "分类编码长度超出限制")
    private String articleCategoryKey;
    public static final String ARTICLE_CATEGORY_KEY = "articleCategoryKey";

    /**
     * 图片编号
     */
    @Field
    @NotNull(message = "图片编号不能为空")
    @Length(max = 32, message = "图片编号长度超出限制")
    private String articleCategoryImageId;
    public static final String ARTICLE_CATEGORY_IMAGE_ID = "articleCategoryImageId";

    /**
     * 描述
     */
    @Field
    @NotNull(message = "描述不能为空")
    @Length(max = 500, message = "描述长度超出限制")
    private String articleCategoryDescription;
    public static final String ARTICLE_CATEGORY_DESCRIPTION = "articleCategoryDescription";

    /**
     * 排序
     */
    @Field
    @NotNull(message = "排序不能为空")
    @Digits(integer = 5, fraction = 0, message = "排序长度超出限制")
    private Integer articleCategorySort;
    public static final String ARTICLE_CATEGORY_SORT = "articleCategorySort";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";


    public String getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(String articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
    
    public String getArticleCategoryParentId() {
        return articleCategoryParentId;
    }

    public void setArticleCategoryParentId(String articleCategoryParentId) {
        this.articleCategoryParentId = articleCategoryParentId;
    }
    
    public String getArticleCategoryParentPath() {
        return articleCategoryParentPath;
    }

    public void setArticleCategoryParentPath(String articleCategoryParentPath) {
        this.articleCategoryParentPath = articleCategoryParentPath;
    }
    
    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }
    
    public String getArticleCategoryKey() {
        return articleCategoryKey;
    }

    public void setArticleCategoryKey(String articleCategoryKey) {
        this.articleCategoryKey = articleCategoryKey;
    }
    
    public String getArticleCategoryImageId() {
        return articleCategoryImageId;
    }

    public void setArticleCategoryImageId(String articleCategoryImageId) {
        this.articleCategoryImageId = articleCategoryImageId;
    }
    
    public String getArticleCategoryDescription() {
        return articleCategoryDescription;
    }

    public void setArticleCategoryDescription(String articleCategoryDescription) {
        this.articleCategoryDescription = articleCategoryDescription;
    }
    
    public Integer getArticleCategorySort() {
        return articleCategorySort;
    }

    public void setArticleCategorySort(Integer articleCategorySort) {
        this.articleCategorySort = articleCategorySort;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    

}