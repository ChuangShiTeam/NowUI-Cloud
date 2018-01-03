package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;

import java.util.List;

/**
 * 文章文章分类业务接口
 *
 * @author marcus
 *
 * 2018-01-03
 */
public interface ArticleArticleCategoryService extends BaseService<ArticleArticleCategory> {

    /**
     * 文章文章分类统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param articleCategoryId 文章分类编号
     * @return Integer 文章文章分类统计
     */
    Integer adminCount(String appId, String articleId, String articleCategoryId);

    /**
     * 文章文章分类列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param articleCategoryId 文章分类编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleArticleCategory> 文章文章分类列表
     */
    List<ArticleArticleCategory> adminList(String appId, String articleId, String articleCategoryId, Integer m, Integer n);
}
