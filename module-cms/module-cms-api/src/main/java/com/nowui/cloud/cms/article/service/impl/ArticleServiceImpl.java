package com.nowui.cloud.cms.article.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.mapper.ArticleMapper;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 文章业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {
    
    @Autowired
    private ArticleArticleCategoryService articleArticleCategoryService;
    
    @Autowired
    private ArticleCategoryService articleCategoryService;
    
    @Autowired
    private ArticleMediaService articleMediaService;
    
    @Override
    public Integer countForAdmin(String appId, String articleTitle) {
        
        Integer count = count(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .likeAllowEmpty(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
        );
        
        return count;
    }

    @Override
    public List<Article> listForAdmin(String appId, String articleTitle, Integer pageIndex, Integer pageSize) {
        
        List<Article> articleList = list(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .likeAllowEmpty(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Article.ARTICLE_IS_TOP))
                        .orderAsc(Arrays.asList(Article.ARTICLE_TOP_LEVEL, Article.ARTICLE_SORT))
                        .orderDesc(Arrays.asList(Article.SYSTEM_CREATE_TIME)),
                pageIndex, 
                pageSize
        );
        
        for (Article article : articleList) {
            ArticleArticleCategory articleArticleCategory = articleArticleCategoryService.findPrimaryByArticleId(article.getArticleId());
            if (articleArticleCategory != null) {
                article.put(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryService.find(articleArticleCategory.getArticleCategoryId()).getArticleCategoryName());
            }
        }
        
        return articleList;
    }

    @Override
    public Boolean save(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList,
            Article article, String systemRequestUserId) {
        
        //保存文章
        String articleId = Util.getRandomUUID();
        String appId = article.getAppId();
        Boolean result = save(article, articleId, systemRequestUserId);
        
        if (result) {
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
        }
        return result;
    }

    @Override
    public Boolean update(List<ArticleArticleCategory> articleArticleCategoryList, List<ArticleMedia> articleMediaList,
            Article article, String systemRequestUserId) {
        
        String appId = article.getAppId();
        String articleId = article.getArticleId();
        Boolean result = update(article, articleId, systemRequestUserId, article.getSystemVersion());
        
        if (result) {
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
        }
        return null;
    }

    /**
     * 重写delete方法
     */
    @Override
    public Boolean delete(String articleId, String systemRequestUserId, Integer systemVersion) {
        
        Boolean result = delete(articleId, systemRequestUserId, systemVersion);
        
        if (result) {
            //删除旧的文章文章分类
            articleArticleCategoryService.deleteByArticleId(articleId, systemRequestUserId);
            //删除旧的文章多媒体
            articleMediaService.deleteByArticleId(articleId, systemRequestUserId);
        }
        
        return result;
    }

}
