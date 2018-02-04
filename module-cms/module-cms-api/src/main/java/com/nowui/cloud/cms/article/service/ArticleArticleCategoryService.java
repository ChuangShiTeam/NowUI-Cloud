package com.nowui.cloud.cms.article.service;
import java.util.List;

import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 文章文章分类业务接口
 *
 * @author marcus
 *
 * 2018-01-03
 */
public interface ArticleArticleCategoryService extends SuperService<ArticleArticleCategory, ArticleArticleCategoryView> {
    
    /**
     * 根据文章ID查询文章的主文章分类
     * 
     * @param articleId 文章编号
     * @return ArticleArticleCategory 文章文章分类
     */
    ArticleArticleCategory findPrimaryByArticleId(String articleId);
    
    /**
     * 根据文章ID查询文章的文章分类列表
     * 
     * @param articleId 文章编号
     * @return List<ArticleArticleCategory> 文章文章分类列表
     */
    List<ArticleArticleCategory> listByArticleId(String articleId);
    
    /**
     * 根据文章ID查询文章的非主分类列表
     * 
     * @param articleId 文章编号
     * @return List<ArticleArticleCategory> 文章文章分类列表
     */
    List<ArticleArticleCategory> listNotPrimaryByArticleId(String articleId);
    
    /**
     * 根据文章ID逻辑删除文章与文章分类的关联
     * 
     * @param articleId 文章编号
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    void deleteByArticleId(String articleId, String systemRequestUserId);
    
    /**
     * 根据文章分类编号查询问文章的主分类列表
     * 
     * @param articleCategoryId 文章分类编号
     * @return List<ArticleArticleCategory> 文章文章分类列表
     */
    List<ArticleArticleCategory> listPrimaryByArticleCategoryId(String articleCategoryId);

}
