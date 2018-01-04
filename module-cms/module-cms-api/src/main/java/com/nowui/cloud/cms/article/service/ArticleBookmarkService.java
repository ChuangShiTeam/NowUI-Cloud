package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleBookmark;

import java.util.List;

/**
 * 文章收藏业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleBookmarkService extends BaseService<ArticleBookmark> {

    /**
     * 文章收藏统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param useId 用户编号
     * @return Integer 文章收藏统计
     */
    Integer adminCount(String appId, String articleId, String useId);

    /**
     * 文章收藏列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param useId 用户编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleBookmark> 文章收藏列表
     */
    List<ArticleBookmark> adminList(String appId, String articleId, String useId, Integer m, Integer n);
}
