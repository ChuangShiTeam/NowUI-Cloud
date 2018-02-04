package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleUserLike;
import com.nowui.cloud.cms.article.mapper.ArticleUserLikeMapper;
import com.nowui.cloud.cms.article.repository.ArticleUserLikeRepository;
import com.nowui.cloud.cms.article.service.ArticleUserLikeService;
import com.nowui.cloud.cms.article.view.ArticleUserLikeView;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章用户点赞业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class ArticleUserLikeServiceImpl extends SuperServiceImpl<ArticleUserLikeMapper, ArticleUserLike, ArticleUserLikeRepository, ArticleUserLikeView> implements ArticleUserLikeService {

    @Override
    public Integer countForAdmin(String appId, String articleId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleUserLike>()
                        .eq(ArticleUserLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserLike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserLike.USER_ID, userId)
                        .eq(ArticleUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleUserLike> listForAdmin(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize) {
        List<ArticleUserLike> articleUserLikeList = list(
                new BaseWrapper<ArticleUserLike>()
                        .eq(ArticleUserLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserLike.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserLike.USER_ID, userId)
                        .eq(ArticleUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleUserLikeList;
    }

}