package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleUserBookmark;

import java.util.List;

/**
 * 文章用户收藏业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface ArticleUserBookmarkService extends BaseService<ArticleUserBookmark> {

    /**
     * 文章用户收藏统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param useId 用户编号
     * @return Integer 文章用户收藏统计
     */
    Integer adminCount(String appId, String articleId, String useId);

    /**
     * 文章用户收藏列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param useId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleUserBookmark> 文章用户收藏列表
     */
    List<ArticleUserBookmark> adminList(String appId, String articleId, String useId, Integer pageIndex, Integer pageSize);
}
