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
import com.nowui.cloud.cms.article.mapper.ArticleMapper;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
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
    private FileRpc fileRpc;

    @Override
    public Integer adminCount(String appId, String articleTitle) {
        Integer count = count(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .likeAllowEmpty(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Article> adminList(String appId, String articleTitle, Integer m, Integer n) {
        List<Article> articleList = list(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .likeAllowEmpty(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Article.ARTICLE_IS_TOP))
                        .orderAsc(Arrays.asList(Article.ARTICLE_TOP_LEVEL, Article.ARTICLE_SORT))
                        .orderDesc(Arrays.asList(Article.SYSTEM_CREATE_TIME)),
                m, 
                n
        );
        
        for (Article article : articleList) {
            ArticleArticleCategory articleArticleCategory = articleArticleCategoryService.articleFindPrimary(article.getArticleId());
            if (articleArticleCategory != null) {
                article.put(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryService.find(articleArticleCategory.getArticleArticleCategoryId()));
            }
            
            if (!Util.isNullOrEmpty(article.getArticleCover())) {
                File file = fileRpc.find(article.getArticleCover());
                file.keep(File.FILE_ID, File.FILE_PATH);
                article.put(Article.ARTICLE_COVER, file);
            }
        }
        
        return articleList;
    }

}
