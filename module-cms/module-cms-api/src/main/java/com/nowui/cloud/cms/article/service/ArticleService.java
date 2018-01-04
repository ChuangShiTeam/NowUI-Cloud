package com.nowui.cloud.cms.article.service;

import java.util.List;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.rpc.ArticleRpc;
import com.nowui.cloud.service.BaseService;

/**
 * 文章业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ArticleService extends BaseService<Article>, ArticleRpc {
    
    /**
     * 文章统计
     *
     * @param appId 应用编号
     * @param articleName 文章名称
     * @return Integer 文章数量
     */
    Integer adminCount(String appId, String articleName);

    /**
     * 文章列表
     *
     * @param appId 应用编号
     * @param articleName 文章名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Article> 文章列表
     */
    List<Article> adminList(String appId, String articleName, Integer m, Integer n);
    
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
    
}
