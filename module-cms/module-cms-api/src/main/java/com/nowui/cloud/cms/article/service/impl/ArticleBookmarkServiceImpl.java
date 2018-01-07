package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleBookmark;
import com.nowui.cloud.cms.article.mapper.ArticleBookmarkMapper;
import com.nowui.cloud.cms.article.service.ArticleBookmarkService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章收藏业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleBookmarkServiceImpl extends BaseServiceImpl<ArticleBookmarkMapper, ArticleBookmark> implements ArticleBookmarkService {

    @Override
    public Integer adminCount(String appId, String articleId, String useId) {
        Integer count = count(
                new BaseWrapper<ArticleBookmark>()
                        .eq(ArticleBookmark.APP_ID, appId)
                        .likeAllowEmpty(ArticleBookmark.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleBookmark.USE_ID, useId)
                        .eq(ArticleBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleBookmark> adminList(String appId, String articleId, String useId, Integer pageIndex, Integer pageSize) {
        List<ArticleBookmark> articleBookmarkList = list(
                new BaseWrapper<ArticleBookmark>()
                        .eq(ArticleBookmark.APP_ID, appId)
                        .likeAllowEmpty(ArticleBookmark.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleBookmark.USE_ID, useId)
                        .eq(ArticleBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleBookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleBookmarkList;
    }

}