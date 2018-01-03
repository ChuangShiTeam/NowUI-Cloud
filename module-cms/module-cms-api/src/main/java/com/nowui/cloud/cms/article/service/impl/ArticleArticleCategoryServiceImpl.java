package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.Article;
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
    public ArticleArticleCategory articleFindPrimary(String articleId) {
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


}