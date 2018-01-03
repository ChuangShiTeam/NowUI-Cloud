package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleArticleCategoryMapper;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章文章分类业务实现
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Service
public class ArticleArticleCategoryServiceImpl extends BaseServiceImpl<ArticleArticleCategoryMapper, ArticleArticleCategory> implements ArticleArticleCategoryService {

    @Override
    public Integer adminCount(String appId, String articleId, String articleCategoryId) {
        Integer count = count(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.APP_ID, appId)
                        .likeAllowEmpty(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleArticleCategory.ARTICLE_CATEGORY_ID, articleCategoryId)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleArticleCategory> adminList(String appId, String articleId, String articleCategoryId, Integer m, Integer n) {
        List<ArticleArticleCategory> articleArticleCategoryList = list(
                new BaseWrapper<ArticleArticleCategory>()
                        .eq(ArticleArticleCategory.APP_ID, appId)
                        .likeAllowEmpty(ArticleArticleCategory.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleArticleCategory.ARTICLE_CATEGORY_ID, articleCategoryId)
                        .eq(ArticleArticleCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleArticleCategory.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return articleArticleCategoryList;
    }

}