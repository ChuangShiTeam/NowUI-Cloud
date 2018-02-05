package com.nowui.cloud.cms.article.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleArticleCategoryMapper;
import com.nowui.cloud.cms.article.repository.ArticleArticleCategoryRepository;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 文章文章分类业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-03
 */
@Service
public class ArticleArticleCategoryServiceImpl extends SuperServiceImpl<ArticleArticleCategoryMapper, ArticleArticleCategory, ArticleArticleCategoryRepository, ArticleArticleCategoryView> implements ArticleArticleCategoryService {

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
        delete(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true),
                systemRequestUserId
        );
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