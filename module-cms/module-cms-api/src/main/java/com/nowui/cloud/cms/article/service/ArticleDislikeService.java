package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleDislike;

import java.util.List;

/**
 * 文章鄙视业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleDislikeService extends BaseService<ArticleDislike> {

    /**
     * 文章鄙视统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @return Integer 文章鄙视统计
     */
    Integer adminCount(String appId, String articleId, String userId);

    /**
     * 文章鄙视列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleDislike> 文章鄙视列表
     */
    List<ArticleDislike> adminList(String appId, String articleId, String userId, Integer pageIndex, Integer pageSize);
}
