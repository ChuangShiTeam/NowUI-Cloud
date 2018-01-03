package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.mapper.ArticleAuditMapper;
import com.nowui.cloud.cms.article.service.ArticleAuditService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文章审核业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleAuditServiceImpl extends BaseServiceImpl<ArticleAuditMapper, ArticleAudit> implements ArticleAuditService {

    @Override
    public Integer adminCount(String appId, String articleId, String userId, String articleAuditStatus) {
        Integer count = count(
                new BaseWrapper<ArticleAudit>()
                        .eq(ArticleAudit.APP_ID, appId)
                        .eq(ArticleAudit.ARTICLE_ID, articleId)
                        .eq(ArticleAudit.USER_ID, userId)
                        .eq(ArticleAudit.ARTICLE_AUDIT_STATUS, articleAuditStatus)
                        .eq(ArticleAudit.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleAudit> adminList(String appId, String articleId, String userId, String articleAuditStatus, Integer m, Integer n) {
        List<ArticleAudit> articleAuditList = list(
                new BaseWrapper<ArticleAudit>()
                        .eq(ArticleAudit.APP_ID, appId)
                        .eq(ArticleAudit.ARTICLE_ID, articleId)
                        .eq(ArticleAudit.USER_ID, userId)
                        .eq(ArticleAudit.ARTICLE_AUDIT_STATUS, articleAuditStatus)
                        .eq(ArticleAudit.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleAudit.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return articleAuditList;
    }

}