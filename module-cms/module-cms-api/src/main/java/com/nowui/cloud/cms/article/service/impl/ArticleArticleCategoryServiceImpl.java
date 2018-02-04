package com.nowui.cloud.cms.article.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleArticleCategoryMapper;
import com.nowui.cloud.cms.article.repository.ArticleArticleCategoryRepository;
import com.nowui.cloud.cms.article.router.ArticleArticleCategoryRouter;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import com.nowui.cloud.cms.toolbar.repository.ToolbarRepository;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 文章文章分类业务实现
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Service
public class ArticleArticleCategoryServiceImpl extends SuperServiceImpl<ArticleArticleCategoryMapper, ArticleArticleCategory,  ArticleArticleCategoryRepository, ArticleArticleCategoryView> implements ArticleArticleCategoryService {

    @Override
    public ArticleArticleCategory findPrimaryByArticleId(String articleId) {
        
        List<ArticleArticleCategory> articleArticleCategoryList = list(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .eq(ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY, true)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
        );
        
        if (articleArticleCategoryList == null || articleArticleCategoryList.size() == 0) {
            return null;
        }
        
        return articleArticleCategoryList.get(0);
    }

    @Override
    public List<ArticleArticleCategory> listByArticleId(String articleId) {
        
        List<ArticleArticleCategory> articleArticleCategoryList = list(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
        );
        
        return articleArticleCategoryList;
    }

    @Override
    public List<ArticleArticleCategory> listNotPrimaryByArticleId(String articleId) {
        
        List<ArticleArticleCategory> articleArticleCategoryList = list(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .eq(ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY, false)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
        );
        
        return articleArticleCategoryList;
    }

    @Override
    public void deleteByArticleId(String articleId, String systemRequestUserId) {
        
        List<ArticleArticleCategory> articleArticleCategoryList = listByArticleId(articleId);
        
        if (articleArticleCategoryList != null && articleArticleCategoryList.size() > 0) {
            for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
                delete(articleArticleCategory.getArticleArticleCategoryId(), articleArticleCategory.getAppId(), ArticleArticleCategoryRouter.ARTICLE_ARTICLE_CATEGORY_V1_DELETE, systemRequestUserId, articleArticleCategory.getSystemVersion());
            }
        }
        
    }

    @Override
    public List<ArticleArticleCategory> listPrimaryByArticleCategoryId(String articleCategoryId) {
        List<ArticleArticleCategory> articleArticleCategoryList = list(
                new BaseWrapper<ArticleArticleCategory>()
                .eq(ArticleArticleCategory.ARTICLE_CATEGORY_ID, articleCategoryId)
                .eq(ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY, true)
                .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
        );
        return articleArticleCategoryList;
    }


}