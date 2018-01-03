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
     * 查询文章的主分类
     * @param articleId
     * @return
     */
    ArticleArticleCategory articleFindPrimary(String articleId);

}
