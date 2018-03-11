package com.nowui.cloud.cms.article.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public ArticleArticleCategoryView findPrimaryByArticleId(String articleId) {
        Criteria criteria = Criteria.where(ArticleArticleCategoryView.ARTICLE_ID).is(articleId)
                .and(ArticleArticleCategoryView.ARTICLE_CATEGORY_IS_PRIMARY).is(true)
                .and(ArticleArticleCategoryView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return find(query);
    }

    @Override
    public List<ArticleArticleCategoryView> listByArticleId(String articleId) {
        
        Criteria criteria = Criteria.where(ArticleArticleCategoryView.ARTICLE_ID).is(articleId)
                .and(ArticleArticleCategoryView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return list(query);
    }

    @Override
    public List<ArticleArticleCategoryView> listNotPrimaryByArticleId(String articleId) {

        Criteria criteria = Criteria.where(ArticleArticleCategoryView.ARTICLE_ID).is(articleId)
                .and(ArticleArticleCategoryView.ARTICLE_CATEGORY_IS_PRIMARY).is(false)
                .and(ArticleArticleCategoryView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return list(query);
    }

    @Override
    public void deleteByArticleId(String articleId, String systemRequestUserId) {
        ArticleArticleCategory result = delete(
            new BaseWrapper<ArticleArticleCategory>()
                    .eq(ArticleArticleCategory.ARTICLE_ID, articleId)
                    .eq(ArticleArticleCategory.SYSTEM_STATUS, true),
            systemRequestUserId
        );
        
        if (result != null) {
            // 删除MongoDB文章文章分类视图信息
            ArticleArticleCategoryView articleArticleCategoryView = new ArticleArticleCategoryView();
            articleArticleCategoryView.putAll(result);
            
            delete(articleArticleCategoryView);
        }
    }

    @Override
    public List<ArticleArticleCategoryView> listPrimaryByArticleCategoryId(String articleCategoryId) {
        Criteria criteria = Criteria.where(ArticleArticleCategoryView.ARTICLE_CATEGORY_ID).is(articleCategoryId)
                .and(ArticleArticleCategoryView.ARTICLE_CATEGORY_IS_PRIMARY).is(true)
                .and(ArticleArticleCategoryView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        return list(query);
    }


}