package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleUserDislike;
import com.nowui.cloud.cms.article.mapper.ArticleUserDislikeMapper;
import com.nowui.cloud.cms.article.service.ArticleUserDislikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章用户鄙视业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class ArticleUserDislikeServiceImpl extends BaseServiceImpl<ArticleUserDislikeMapper, ArticleUserDislike> implements ArticleUserDislikeService {

    @Override
    public Integer countForAdmin(String appId, String articleId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleUserDislike>()
                        .eq(ArticleUserDislike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserDislike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserDislike.USER_ID, userId)
                        .eq(ArticleUserDislike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleUserDislike> listForAdmin(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize) {
        List<ArticleUserDislike> articleUserDislikeList = list(
                new BaseWrapper<ArticleUserDislike>()
                        .eq(ArticleUserDislike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserDislike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserDislike.USER_ID, userId)
                        .eq(ArticleUserDislike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleUserDislike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleUserDislikeList;
    }

}