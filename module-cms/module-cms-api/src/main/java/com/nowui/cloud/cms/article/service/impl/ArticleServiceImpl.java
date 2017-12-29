package com.nowui.cloud.cms.article.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.mapper.ArticleMapper;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 文章业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Integer adminCount(String appId, String articleTitle) {
        Integer count = count(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .like(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Article> adminList(String appId, String articleTitle, Integer m, Integer n) {
        List<Article> articleList = list(
                new BaseWrapper<Article>()
                        .eq(Article.APP_ID, appId)
                        .like(Article.ARTICLE_TITLE, articleTitle)
                        .eq(Article.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Article.SYSTEM_CREATE_TIME)),
                m, 
                n
        );
        
        return articleList;
    }

}
