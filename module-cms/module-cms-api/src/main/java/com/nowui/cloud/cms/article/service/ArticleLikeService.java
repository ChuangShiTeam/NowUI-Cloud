package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleLike;

import java.util.List;

/**
 * 文章点赞业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleLikeService extends BaseService<ArticleLike> {

    /**
     * 文章点赞统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 点赞数
     * @return Integer 文章点赞统计
     */
    Integer adminCount(String appId, String articleId, String userId);

    /**
     * 文章点赞列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 点赞数
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleLike> 文章点赞列表
     */
    List<ArticleLike> adminList(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize);
}
