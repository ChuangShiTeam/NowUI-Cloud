package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleUserCommentUserLike;
import com.nowui.cloud.cms.article.view.ArticleUserCommentUserLikeView;

import java.util.List;

/**
 * 文章用户评论用户点赞业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface ArticleUserCommentUserLikeService extends BaseService<ArticleUserCommentUserLike, ArticleUserCommentUserLikeView> {

    /**
     * 文章用户评论用户点赞统计
     *
     * @param appId 应用编号
     * @param articleUserCommentId 文章用户评论编号
     * @param userId 用户编号
     * @return Integer 文章用户评论用户点赞统计
     */
    Integer countForAdmin(String appId, String articleUserCommentId, String userId);

    /**
     * 文章用户评论用户点赞列表
     *
     * @param appId 应用编号
     * @param articleUserCommentId 文章用户评论编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleUserCommentUserLike> 文章用户评论用户点赞列表
     */
    List<ArticleUserCommentUserLike> listForAdmin(String appId, String articleUserCommentId, String userId, Integer pageIndex, Integer pageSize);
}
