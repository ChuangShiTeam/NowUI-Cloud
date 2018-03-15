package com.nowui.cloud.cms.article.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 文章分类
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Component

@TableName(value = "article_category_info")
public class ArticleCategory extends BaseEntity {

    /**
     * 文章分类编号
     */
    @TableId
    @TableField
    private String articleCategoryId;

    /**
     * 父级编号
     */
    @TableField
    private String articleCategoryParentId;

    /**
     * 父级路径
     */
    @TableField
    private String articleCategoryParentPath;

    /**
     * 分类名称
     */
    @TableField
    private String articleCategoryName;

    /**
     * 分类编码
     */
    @TableField
    private String articleCategoryCode;

    /**
     * 多媒体文件编号
     */
    @TableField
    private String articleCategoryImageId;

    /**
     * 描述
     */
    @TableField
    private String articleCategoryDescription;

    /**
     * 排序
     */
    @TableField
    private Integer articleCategorySort;

    /**
     * 应用编号
     */
    @TableField
    private String appId;


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

    public String getArticleCategoryCode() {
        return articleCategoryCode;
    }
    
    public void setArticleCategoryCode(String articleCategoryCode) {
        this.articleCategoryCode = articleCategoryCode;
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