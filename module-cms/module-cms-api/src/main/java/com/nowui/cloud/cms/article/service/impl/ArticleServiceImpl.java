package com.nowui.cloud.cms.article.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.mapper.ArticleMapper;
import com.nowui.cloud.cms.article.repository.ArticleRepository;
import com.nowui.cloud.cms.article.router.ArticleRouter;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.cms.article.view.ArticleView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 文章业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleServiceImpl extends SuperServiceImpl<ArticleMapper, Article, ArticleRepository, ArticleView> implements ArticleService {
    
    @Autowired
    private ArticleArticleCategoryService articleArticleCategoryService;
    
    @Autowired
    private ArticleCategoryService articleCategoryService;
    
    @Autowired
    private ArticleMediaService articleMediaService;
    
    @Override
    public Integer countForAdmin(String appId, String articleTitle) {
        Criteria criteria = Criteria.where(ArticleView.APP_ID).is(appId)
                .and(ArticleView.ARTICLE_TITLE).regex(".*?" + articleTitle + ".*")
                .and(ArticleView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<ArticleView> listForAdmin(String appId, String articleTitle, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(ArticleView.APP_ID).is(appId)
                .and(ArticleView.ARTICLE_TITLE).regex(".*?" + articleTitle + ".*")
                .and(ArticleView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, ArticleView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ArticleView> articleViewList = list(query, sort, pageIndex, pageSize);
        
        return articleViewList;
    }

    @Override
    public Article save(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList, Article article, String systemRequestUserId) {
        
        //保存文章
        String articleId = Util.getRandomUUID();
        String appId = article.getAppId();
        Article result = save(article, articleId, systemRequestUserId);
        
        if (result != null) {
            //保存文章多媒体
            for (ArticleMedia articleMedia : articleMediaList) {
                String articleMediaId = Util.getRandomUUID();

                articleMedia.setAppId(appId);
                articleMedia.setArticleId(articleId);
                articleMediaService.save(articleMedia, articleMediaId, systemRequestUserId);
            }

            //保存文章文章分类关联
            for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
                String articleArticleCategoryId = Util.getRandomUUID();

                articleArticleCategory.setAppId(appId);
                articleArticleCategory.setArticleId(articleId);
                articleArticleCategoryService.save(articleArticleCategory, articleArticleCategoryId, systemRequestUserId);
            }
        }

        return result;
    }

    @Override
    public Article update(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList, Article article, String systemRequestUserId) {
        String appId = article.getAppId();
        String articleId = article.getArticleId();
        Article result = update(article, articleId, systemRequestUserId, article.getSystemVersion());

        if (result != null) {
            //删除旧的文章文章分类
            articleArticleCategoryService.deleteByArticleId(articleId, systemRequestUserId);
            //删除旧的文章多媒体
            articleMediaService.deleteByArticleId(articleId, systemRequestUserId);
            //保存文章多媒体
            for (ArticleMedia articleMedia : articleMediaList) {
                articleMedia.setAppId(appId);
                articleMedia.setArticleId(articleId);
                articleMediaService.save(articleMedia, Util.getRandomUUID(), systemRequestUserId);
            }
            
            //保存文章文章分类关联
            for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
                articleArticleCategory.setAppId(appId);
                articleArticleCategory.setArticleId(articleId);
                articleArticleCategoryService.save(articleArticleCategory,  Util.getRandomUUID(), systemRequestUserId);
            }

            rabbitSender.send(appId, ArticleRouter.ARTICLE_V1_UPDATE, article, systemRequestUserId);
        }

        return result;
    }

    @Override
    public Article delete(String articleId, String systemRequestUserId, Integer systemVersion) {

        Article result = super.delete(articleId, systemRequestUserId, systemVersion);

        if (result != null) {
            //删除旧的文章文章分类
            articleArticleCategoryService.deleteByArticleId(articleId, systemRequestUserId);
            //删除旧的文章多媒体
            articleMediaService.deleteByArticleId(articleId, systemRequestUserId);
        }
        
        return result;
    }

    @Override
    public List<Article> listByPrimaryCategoryCode(String appId, String articleCategoryCode) {
        
        ArticleCategory articleCategory = articleCategoryService.findByCategoryCode(appId, articleCategoryCode);
        
        if (articleCategory == null) {
            return null;
        }
        
        List<ArticleArticleCategory> articleArticleCategoryList = articleArticleCategoryService.listPrimaryByArticleCategoryId(articleCategory.getArticleCategoryId());
        
        if (Util.isNullOrEmpty(articleArticleCategoryList)) {
            return null;
        }
        
//        // 查询文章列表并按文章排序字段排序
//        List<Article> articleList = articleArticleCategoryList
//                                    .stream()
//                                    .map(articleArticleCategory -> find(articleArticleCategory.getArticleId()))
//                                    .sorted(Comparator.comparing(Article::getArticleSort))
//                                    .collect(Collectors.toList());
//
//        return articleList;

        return null;
    }

}
