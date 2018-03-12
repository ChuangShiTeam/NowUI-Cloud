package com.nowui.cloud.cms.article.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.mapper.ArticleMapper;
import com.nowui.cloud.cms.article.repository.ArticleRepository;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.cms.article.view.ArticleView;
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
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article, ArticleRepository, ArticleView> implements ArticleService {
    
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
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleView.ARTICLE_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ArticleView> articleViewList = list(query, sort, pageIndex, pageSize);
        
        return articleViewList;
    }
    
    @Override
	public List<ArticleView> hotList(String appId, Integer pageIndex, Integer pageSize) {
    	Criteria criteria = Criteria.where(ArticleView.APP_ID).is(appId)
                .and(ArticleView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleView.ARTICLE_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ArticleView> articleViewList = list(query, sort, pageIndex, pageSize);
        
        return articleViewList;
	}

    @Override
    public Article save(Article article, String articleId, ArticleArticleCategory articlePrimaryArticleCategory, List<ArticleArticleCategory> articleSecondaryArticleCategoryList, List<ArticleMedia> articleMediaList, String systemRequestUserId) {
        
        //保存文章
        String appId = article.getAppId();
        Article result = save(article, articleId, systemRequestUserId);
        
        if (result != null) {
            List<ArticleArticleCategory> articleArticleCategoryList = new ArrayList<ArticleArticleCategory>();
            articleArticleCategoryList.add(articlePrimaryArticleCategory);
            articleArticleCategoryList.addAll(articleSecondaryArticleCategoryList);

            //保存文章文章分类关联
            for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
                articleArticleCategory.setArticleArticleCategoryId(Util.getRandomUUID());
                articleArticleCategory.setAppId(appId);
                articleArticleCategory.setArticleId(articleId);
            }
            articleArticleCategoryService.save(articleArticleCategoryList, systemRequestUserId);

            //保存文章多媒体
            for (ArticleMedia articleMedia : articleMediaList) {
                articleMedia.setArticleMediaId(Util.getRandomUUID());
                articleMedia.setAppId(appId);
                articleMedia.setArticleId(articleId);
            }
            articleMediaService.save(articleMediaList, systemRequestUserId);

        }

        return result;
    }

    @Override
    public Article update(Article article, ArticleArticleCategory articlePrimaryArticleCategory, List<ArticleArticleCategory> articleSecondaryArticleCategoryList, List<ArticleMedia> articleMediaList, String systemRequestUserId) {
        String appId = article.getAppId();
        String articleId = article.getArticleId();
        Article result = update(article, articleId, systemRequestUserId, article.getSystemVersion());

        if (result != null) {
            List<ArticleArticleCategory> articleArticleCategoryList = new ArrayList<ArticleArticleCategory>();
            articleArticleCategoryList.add(articlePrimaryArticleCategory);
            articleArticleCategoryList.addAll(articleSecondaryArticleCategoryList);

            //删除旧的文章文章分类
            articleArticleCategoryService.deleteByArticleId(articleId, systemRequestUserId);

            //保存文章文章分类关联
            for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
                articleArticleCategory.setArticleArticleCategoryId(Util.getRandomUUID());
                articleArticleCategory.setAppId(appId);
                articleArticleCategory.setArticleId(articleId);
            }
            articleArticleCategoryService.save(articleArticleCategoryList, systemRequestUserId);

            //删除旧的文章多媒体
            articleMediaService.deleteByArticleId(articleId, systemRequestUserId);

            //保存文章多媒体
            for (ArticleMedia articleMedia : articleMediaList) {
                articleMedia.setArticleMediaId(Util.getRandomUUID());
                articleMedia.setAppId(appId);
                articleMedia.setArticleId(articleId);
            }
            articleMediaService.save(articleMediaList, systemRequestUserId);
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
    public List<ArticleView> listByPrimaryCategoryCode(String appId, String articleCategoryCode) {
        
        ArticleCategoryView articleCategoryView = articleCategoryService.findByCategoryCode(appId, articleCategoryCode);
        
        if (articleCategoryView == null) {
            return null;
        }
        
        List<ArticleArticleCategoryView> articleArticleCategoryViewList = articleArticleCategoryService.listPrimaryByArticleCategoryId(articleCategoryView.getArticleCategoryId());
        
        if (Util.isNullOrEmpty(articleArticleCategoryViewList)) {
            return null;
        }
        
        List<String> articleIdList = articleArticleCategoryViewList
                                    .stream()
                                    .map(articleArticleCategoryView -> articleArticleCategoryView.getArticleId())
                                    .collect(Collectors.toList());
        
        Criteria criteria = Criteria.where(ArticleView.APP_ID).is(appId)
                .and(ArticleView.ARTICLE_ID).in(articleIdList)
                .and(ArticleView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleView.ARTICLE_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        return list(query, sort);
    }

}
