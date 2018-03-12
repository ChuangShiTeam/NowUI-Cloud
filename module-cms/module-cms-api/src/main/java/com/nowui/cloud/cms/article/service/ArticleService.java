package com.nowui.cloud.cms.article.service;

import java.util.List;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.view.ArticleView;
import com.nowui.cloud.service.SuperService;

/**
 * 文章业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ArticleService extends SuperService<Article, ArticleView> {
    
    /**
     * 文章统计
     *
     * @param appId 应用编号
     * @param articleName 文章名称
     * @return Integer 文章数量
     */
    Integer countForAdmin(String appId, String articleName);

    /**
     * 文章列表
     *
     * @param appId 应用编号
     * @param articleName 文章名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Article> 文章列表
     */
    List<ArticleView> listForAdmin(String appId, String articleName, Integer pageIndex, Integer pageSize);
    
    /**
     * 热门文章列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Article> 文章列表
     */
    List<ArticleView> hotList(String appId, Integer pageIndex, Integer pageSize);

    
    /**
     * 文章保存
     *
     * @param article 文章
     * @param articlePrimaryArticleCategory 文章主分类
     * @param articleSecondaryArticleCategoryList 文章副分类列表
     * @param articleMediaList 文章多媒体列表
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    Article save(Article article, String articleId, ArticleArticleCategory articlePrimaryArticleCategory, List<ArticleArticleCategory> articleSecondaryArticleCategoryList, List<ArticleMedia> articleMediaList, String systemRequestUserId);
    
    /**
     * 文章更新
     *
     * @param article 文章
     * @param articlePrimaryArticleCategory 文章主分类
     * @param articleSecondaryArticleCategoryList 文章副分类列表
     * @param articleMediaList 文章多媒体列表
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    Article update(Article article, ArticleArticleCategory articlePrimaryArticleCategory, List<ArticleArticleCategory> articleSecondaryArticleCategoryList, List<ArticleMedia> articleMediaList, String systemRequestUserId);
    
    /**
     * 根据文章主分类编码查询文章列表
     * 
     * @param appId 应用编号
     * @param articleCategoryCode 文章主分类编码
     * @return List<ArticleView> 文章列表信息
     */
    List<ArticleView> listByPrimaryCategoryCode(String appId, String articleCategoryCode);
}
