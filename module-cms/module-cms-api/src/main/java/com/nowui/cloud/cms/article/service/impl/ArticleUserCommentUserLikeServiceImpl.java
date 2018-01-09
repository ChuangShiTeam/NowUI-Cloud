package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleUserCommentUserLike;
import com.nowui.cloud.cms.article.mapper.ArticleUserCommentUserLikeMapper;
import com.nowui.cloud.cms.article.service.ArticleUserCommentUserLikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章用户评论用户点赞业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class ArticleUserCommentUserLikeServiceImpl extends BaseServiceImpl<ArticleUserCommentUserLikeMapper, ArticleUserCommentUserLike> implements ArticleUserCommentUserLikeService {

    @Override
    public Integer countForAdmin(String appId, String articleUserCommentId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleUserCommentUserLike>()
                        .eq(ArticleUserCommentUserLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID, articleUserCommentId)
                        .likeAllowEmpty(ArticleUserCommentUserLike.USER_ID, userId)
                        .eq(ArticleUserCommentUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleUserCommentUserLike> listForAdmin(String appId, String articleUserCommentId, String userId, Integer pageIndex, Integer pageSize) {
        List<ArticleUserCommentUserLike> articleUserCommentUserLikeList = list(
                new BaseWrapper<ArticleUserCommentUserLike>()
                        .eq(ArticleUserCommentUserLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID, articleUserCommentId)
                        .likeAllowEmpty(ArticleUserCommentUserLike.USER_ID, userId)
                        .eq(ArticleUserCommentUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleUserCommentUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleUserCommentUserLikeList;
    }

}