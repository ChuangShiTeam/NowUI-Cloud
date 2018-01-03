package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleComment;

import java.util.List;

/**
 * 文章评论业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleCommentService extends BaseService<ArticleComment> {

    /**
     * 文章评论统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @param articleReolyCommentId 回复的评论编号
     * @param articleReplyUserId 回复的用户编号
     * @param articleCommentContent 评论内容
     * @return Integer 文章评论统计
     */
    Integer adminCount(String appId, String articleId, String userId, String articleReolyCommentId, String articleReplyUserId, String articleCommentContent);

    /**
     * 文章评论列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @param articleReolyCommentId 回复的评论编号
     * @param articleReplyUserId 回复的用户编号
     * @param articleCommentContent 评论内容
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleComment> 文章评论列表
     */
    List<ArticleComment> adminList(String appId, String articleId, String userId, String articleReolyCommentId, String articleReplyUserId, String articleCommentContent, Integer m, Integer n);
}
