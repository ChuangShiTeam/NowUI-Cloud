package com.nowui.cloud.cms.article.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleMedia;

import java.util.List;

/**
 * 文章多媒体业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleMediaService extends BaseService<ArticleMedia> {

    /**
     * 文章多媒体统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param fileId 文件编号
     * @return Integer 文章多媒体统计
     */
    Integer adminCount(String appId, String articleId, String fileId);

    /**
     * 文章多媒体列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param fileId 文件编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleMedia> 文章多媒体列表
     */
    List<ArticleMedia> adminList(String appId, String articleId, String fileId, Integer m, Integer n);
}
