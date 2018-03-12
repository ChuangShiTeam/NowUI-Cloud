package com.nowui.cloud.cms.article.service;
import java.util.List;

import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.view.ArticleMediaView;
import com.nowui.cloud.service.SuperService;

/**
 * 文章多媒体业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleMediaService extends SuperService<ArticleMedia, ArticleMediaView> {
    
    /**
     * 根据文章ID查询文章的文章多媒体列表
     * 
     * @param articleId 文章编号
     * @return List<ArticleMedia> 文章多媒体列表
     */
    List<ArticleMedia> listByArticleId(String articleId);

    /**
     * 根据文章ID逻辑删除文章与多媒体的关联
     * 
     * @param articleId 文章编号
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    void deleteByArticleId(String articleId, String systemRequestUserId);
}
