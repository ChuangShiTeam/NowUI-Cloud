package com.nowui.cloud.cms.article.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.mapper.ArticleMediaMapper;
import com.nowui.cloud.cms.article.repository.ArticleMediaRepository;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import com.nowui.cloud.cms.article.view.ArticleMediaView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 文章多媒体业务实现
 *
 * @author xupengfei
 * <p>
 * 2018-01-03
 */
@Service
public class ArticleMediaServiceImpl extends BaseServiceImpl<ArticleMediaMapper, ArticleMedia, ArticleMediaRepository, ArticleMediaView> implements ArticleMediaService {

    @Override
    public List<ArticleMedia> listByArticleId(String articleId) {

        List<ArticleMedia> articleMediaList = list(
                new BaseWrapper<ArticleMedia>()
                        .eq(ArticleMedia.ARTICLE_ID, articleId)
                        .eq(ArticleMedia.SYSTEM_STATUS, true)
        );

        return articleMediaList;
    }

    @Override
    public void deleteByArticleId(String articleId, String systemRequestUserId) {
        delete(
                new BaseWrapper<ArticleMedia>()
                        .eq(ArticleMedia.ARTICLE_ID, articleId)
                        .eq(ArticleMedia.SYSTEM_STATUS, true),
                systemRequestUserId
        );

    }


}