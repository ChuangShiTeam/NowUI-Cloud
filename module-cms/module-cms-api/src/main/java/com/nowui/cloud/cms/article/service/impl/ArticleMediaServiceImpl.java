package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.mapper.ArticleMediaMapper;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章多媒体业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleMediaServiceImpl extends BaseServiceImpl<ArticleMediaMapper, ArticleMedia> implements ArticleMediaService {

    @Override
    public Integer adminCount(String appId, String articleId, String fileId) {
        Integer count = count(
                new BaseWrapper<ArticleMedia>()
                        .eq(ArticleMedia.APP_ID, appId)
                        .likeAllowEmpty(ArticleMedia.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleMedia.FILE_ID, fileId)
                        .eq(ArticleMedia.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleMedia> adminList(String appId, String articleId, String fileId, Integer m, Integer n) {
        List<ArticleMedia> articleMediaList = list(
                new BaseWrapper<ArticleMedia>()
                        .eq(ArticleMedia.APP_ID, appId)
                        .likeAllowEmpty(ArticleMedia.ARTICLE_ID, articleId)
                        .likeAllowEmpty(ArticleMedia.FILE_ID, fileId)
                        .eq(ArticleMedia.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleMedia.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return articleMediaList;
    }

}