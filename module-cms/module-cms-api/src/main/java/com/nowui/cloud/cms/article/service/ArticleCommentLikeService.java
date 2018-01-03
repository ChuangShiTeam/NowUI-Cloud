package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleCommentLike;

import java.util.List;

/**
 * 文章评论点赞业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleCommentLikeService extends BaseService<ArticleCommentLike> {

    /**
     * 文章评论点赞统计
     *
     * @param appId 应用编号
     * @param articleCommentId 文章评论编号
     * @param userId 用户编号
     * @return Integer 文章评论点赞统计
     */
    Integer adminCount(String appId, String articleCommentId, String userId);

    /**
     * 文章评论点赞列表
     *
     * @param appId 应用编号
     * @param articleCommentId 文章评论编号
     * @param userId 用户编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleCommentLike> 文章评论点赞列表
     */
    List<ArticleCommentLike> adminList(String appId, String articleCommentId, String userId, Integer m, Integer n);
}
