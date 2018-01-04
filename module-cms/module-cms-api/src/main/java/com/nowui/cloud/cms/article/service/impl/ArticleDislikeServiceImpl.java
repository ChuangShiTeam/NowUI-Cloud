package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleDislike;
import com.nowui.cloud.cms.article.mapper.ArticleDislikeMapper;
import com.nowui.cloud.cms.article.service.ArticleDislikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章鄙视业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleDislikeServiceImpl extends BaseServiceImpl<ArticleDislikeMapper, ArticleDislike> implements ArticleDislikeService {

    @Override
    public Integer adminCount(String appId, String articleId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleDislike>()
                        .eq(ArticleDislike.APP_ID, appId)
                        .likeAllowEmpty(ArticleDislike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleDislike.USER_ID, userId)
                        .eq(ArticleDislike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleDislike> adminList(String appId, String articleId, String userId, Integer m, Integer n) {
        List<ArticleDislike> articleDislikeList = list(
                new BaseWrapper<ArticleDislike>()
                        .eq(ArticleDislike.APP_ID, appId)
                        .likeAllowEmpty(ArticleDislike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleDislike.USER_ID, userId)
                        .eq(ArticleDislike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleDislike.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return articleDislikeList;
    }

}