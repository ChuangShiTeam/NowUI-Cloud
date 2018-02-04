package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleUserBookmark;
import com.nowui.cloud.cms.article.mapper.ArticleUserBookmarkMapper;
import com.nowui.cloud.cms.article.repository.ArticleUserBookmarkRepository;
import com.nowui.cloud.cms.article.service.ArticleUserBookmarkService;
import com.nowui.cloud.cms.article.view.ArticleUserBookmarkView;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章用户收藏业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class ArticleUserBookmarkServiceImpl extends SuperServiceImpl<ArticleUserBookmarkMapper, ArticleUserBookmark, ArticleUserBookmarkRepository, ArticleUserBookmarkView> implements ArticleUserBookmarkService {

    @Override
    public Integer countForAdmin(String appId, String articleId, String useId) {
        Integer count = count(
                new BaseWrapper<ArticleUserBookmark>()
                        .eq(ArticleUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserBookmark.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserBookmark.USE_ID, useId)
                        .eq(ArticleUserBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleUserBookmark> listForAdmin(String appId, String articleId, String useId, Integer pageIndex, Integer pageSize) {
        List<ArticleUserBookmark> articleUserBookmarkList = list(
                new BaseWrapper<ArticleUserBookmark>()
                        .eq(ArticleUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserBookmark.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserBookmark.USE_ID, useId)
                        .eq(ArticleUserBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleUserBookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleUserBookmarkList;
    }

}