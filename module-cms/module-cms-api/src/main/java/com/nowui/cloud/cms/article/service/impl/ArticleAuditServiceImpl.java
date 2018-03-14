package com.nowui.cloud.cms.article.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.mapper.ArticleAuditMapper;
import com.nowui.cloud.cms.article.repository.ArticleAuditRepository;
import com.nowui.cloud.cms.article.service.ArticleAuditService;
import com.nowui.cloud.cms.article.view.ArticleAuditView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 文章审核业务实现
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Service
public class ArticleAuditServiceImpl extends BaseServiceImpl<ArticleAuditMapper, ArticleAudit,  ArticleAuditRepository, ArticleAuditView> implements ArticleAuditService {

    @Override
    public Integer countForAdmin(String appId, String articleId, String userId, String articleAuditStatus) {
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
    public List<ArticleAudit> listForAdmin(String appId, String articleId, String userId, String articleAuditStatus, Integer pageIndex, Integer pageSize) {
        List<ArticleAudit> articleAuditList = list(
                new BaseWrapper<ArticleAudit>()
                        .eq(ArticleAudit.APP_ID, appId)
                        .eq(ArticleAudit.ARTICLE_ID, articleId)
                        .eq(ArticleAudit.USER_ID, userId)
                        .eq(ArticleAudit.ARTICLE_AUDIT_STATUS, articleAuditStatus)
                        .eq(ArticleAudit.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleAudit.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return articleAuditList;
    }

}