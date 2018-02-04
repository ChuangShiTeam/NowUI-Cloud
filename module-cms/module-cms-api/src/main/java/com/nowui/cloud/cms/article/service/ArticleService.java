package com.nowui.cloud.cms.article.service;

import java.util.List;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.view.ArticleView;
import com.nowui.cloud.service.BaseService;
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
    List<Article> listForAdmin(String appId, String articleName, Integer pageIndex, Integer pageSize);
    
    /**
     * 文章保存
     * 
     * @param articleArticleCategoryList 文章文章分类列表
     * @param articleMediaList 文章多媒体列表
     * @param article 文章
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    Boolean save(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList, Article article, String systemRequestUserId);
    
    /**
     * 文章更新
     * 
     * @param articleArticleCategoryList 文章文章分类列表
     * @param articleMediaList 文章多媒体列表
     * @param article 文章
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    Boolean update(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList, Article article, String systemRequestUserId);
    
    /**
     * 根据文章主分类编码查询文章列表
     * 
     * @param appId 应用编号
     * @param articleCategoryCode 文章主分类编码
     * @return List<Article> 文章列表信息
     */
    List<Article> listByPrimaryCategoryCode(String appId, String articleCategoryCode);
}
