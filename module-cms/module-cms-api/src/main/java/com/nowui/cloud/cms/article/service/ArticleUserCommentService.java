package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleUserComment;

import java.util.List;

/**
 * 文章用户评论业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface ArticleUserCommentService extends BaseService<ArticleUserComment> {

    /**
     * 文章用户评论统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @return Integer 文章用户评论统计
     */
    Integer adminCount(String appId, String articleId, String userId);

    /**
     * 文章用户评论列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleUserComment> 文章用户评论列表
     */
    List<ArticleUserComment> adminList(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize);
}
