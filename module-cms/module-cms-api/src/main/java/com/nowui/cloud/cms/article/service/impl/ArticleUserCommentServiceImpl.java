package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleUserComment;
import com.nowui.cloud.cms.article.mapper.ArticleUserCommentMapper;
import com.nowui.cloud.cms.article.service.ArticleUserCommentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章用户评论业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class ArticleUserCommentServiceImpl extends BaseServiceImpl<ArticleUserCommentMapper, ArticleUserComment> implements ArticleUserCommentService {

    @Override
    public Integer countForAdmin(String appId, String articleId, String userId) {
        Integer count = count(
                new BaseWrapper<ArticleUserComment>()
                        .eq(ArticleUserComment.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserComment.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserComment.USER_ID, userId)
                        .eq(ArticleUserComment.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleUserComment> listForAdmin(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize) {
        List<ArticleUserComment> articleUserCommentList = list(
                new BaseWrapper<ArticleUserComment>()
                        .eq(ArticleUserComment.APP_ID, appId)
                        .likeAllowEmpty(ArticleUserComment.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleUserComment.USER_ID, userId)
                        .eq(ArticleUserComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleUserComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleUserCommentList;
    }

}