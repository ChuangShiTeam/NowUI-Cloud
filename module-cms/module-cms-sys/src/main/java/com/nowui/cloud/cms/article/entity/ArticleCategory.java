package com.nowui.cloud.cms.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章分类
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Component(value = "articleCategory")
@TableName(value = "article_category_info")
public class ArticleCategory extends BaseEntity {
    
    /**
     * 文章分类编号
     */
    @TableId
    @NotNull(message = "文章分类编号不能为空")
    @Length(max = 32, message = "文章分类编号长度超出限制")
    private String articleCategoryId;
    public static final String ARTICLE_CATEGORY_ID = "articleCategoryId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 上级文章分类编号
     */
    @TableField
    @NotNull(message = "上级文章分类编号不能为空")
    @Length(max = 32, message = "上级文章分类编号长度超出限制")
    private String articleCategoryParentId;
    public static final String ARTICLE_CATEGORY_PARENT_ID = "articleCategoryParentId";
    
    /**
     * 上级文章分类路径
     */
    @TableField
    @NotNull(message = "上级文章分类路径不能为空")
    @Length(max = 2000, message = "上级文章分类路径长度超出限制")
    private String articleCategoryParentPath;
    public static final String ARTICLE_CATEGORY_PARENT_PATH = "articleCategoryParentPath";
    
    /**
     * 分类名称
     */
    @TableField
    @NotNull(message = "分类名称不能为空")
    @Length(max = 100, message = "分类名称长度超出限制")
    private String articleCategoryName;
    public static final String ARTICLE_CATEGORY_NAME = "articleCategoryName";
    
    /**
     * 关键字
     */
    @TableField
    @NotNull(message = "关键字不能为空")
    @Length(max = 200, message = "关键字长度超出限制")
    private String articleCategoryKeywords;
    public static final String ARTICLE_CATEGORY_KEYWORDS = "articleCategoryKeywords";
    
    /**
     * 描述
     */
    @TableField
    @NotNull(message = "描述不能为空")
    @Length(max = 500, message = "描述长度超出限制")
    private String articleCategoryDescription;
    public static final String ARTICLE_CATEGORY_DESCRIPTION = "articleCategoryDescription";
    
    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    @Length(max = 11, message = "排序长度超出限制")
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
