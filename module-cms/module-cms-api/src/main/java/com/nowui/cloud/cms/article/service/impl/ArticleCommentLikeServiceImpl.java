package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleCommentLike;
import com.nowui.cloud.cms.article.mapper.ArticleCommentLikeMapper;
import com.nowui.cloud.cms.article.service.ArticleCommentLikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章评论点赞业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleCommentLikeServiceImpl extends BaseServiceImpl<ArticleCommentLikeMapper, ArticleCommentLike> implements ArticleCommentLikeService {

    @Override
    public Integer adminCount(String appId, String articleCommentId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleCommentLike>()
                        .eq(ArticleCommentLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleCommentLike.ARTICLE_COMMENT_ID, articleCommentId)
                        .likeAllowEmpty(ArticleCommentLike.USER_ID, userId)
                        .eq(ArticleCommentLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleCommentLike> adminList(String appId, String articleCommentId, String userId, Integer pageIndex, Integer pageSize) {
        List<ArticleCommentLike> articleCommentLikeList = list(
                new BaseWrapper<ArticleCommentLike>()
                        .eq(ArticleCommentLike.APP_ID, appId)
                        .likeAllowEmpty(ArticleCommentLike.ARTICLE_COMMENT_ID, articleCommentId)
                        .likeAllowEmpty(ArticleCommentLike.USER_ID, userId)
                        .eq(ArticleCommentLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleCommentLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleCommentLikeList;
    }

}