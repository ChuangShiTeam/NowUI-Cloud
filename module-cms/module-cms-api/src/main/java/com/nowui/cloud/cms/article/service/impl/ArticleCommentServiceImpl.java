package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleComment;
import com.nowui.cloud.cms.article.mapper.ArticleCommentMapper;
import com.nowui.cloud.cms.article.service.ArticleCommentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章评论业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleCommentServiceImpl extends BaseServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Override
    public Integer adminCount(String appId, String articleId, String userId, String articleReolyCommentId, String articleReplyUserId, String articleCommentContent) {
        Integer count = count(
                new BaseWrapper<ArticleComment>()
                        .eq(ArticleComment.APP_ID, appId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleComment.USER_ID, userId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_REOLY_COMMENT_ID, articleReolyCommentId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_REPLY_USER_ID, articleReplyUserId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_COMMENT_CONTENT, articleCommentContent)
                        .eq(ArticleComment.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleComment> adminList(String appId, String articleId, String userId, String articleReolyCommentId, String articleReplyUserId, String articleCommentContent, Integer pageIndex, Integer pageSize) {
        List<ArticleComment> articleCommentList = list(
                new BaseWrapper<ArticleComment>()
                        .eq(ArticleComment.APP_ID, appId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleComment.USER_ID, userId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_REOLY_COMMENT_ID, articleReolyCommentId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_REPLY_USER_ID, articleReplyUserId)
                        .likeAllowEmpty(ArticleComment.ARTICLE_COMMENT_CONTENT, articleCommentContent)
                        .eq(ArticleComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleCommentList;
    }

}