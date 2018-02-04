package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章审核视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_audit_info")
public class ArticleAuditView extends BaseView {

    /**
     * 文章审核编号
     */
    @KeyId
    @Field
    private String articleAuditId;
    public static final String ARTICLE_AUDIT_ID = "articleAuditId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章编号
     */
    @Field
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 审核人编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 审核状态
     */
    @Field
    private String articleAuditStatus;
    public static final String ARTICLE_AUDIT_STATUS = "articleAuditStatus";

    /**
     * 审核时间
     */
    @Field
    private Date articleAuditTime;
    public static final String ARTICLE_AUDIT_TIME = "articleAuditTime";

    /**
     * 审核内容
     */
    @Field
    private String articleAuditContent;
    public static final String ARTICLE_AUDIT_CONTENT = "articleAuditContent";


    public String getArticleAuditId() {
        return getString(ARTICLE_AUDIT_ID);
    }

    public void setArticleAuditId(String articleAuditId) {
        put(ARTICLE_AUDIT_ID, articleAuditId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getArticleId() {
        return getString(ARTICLE_ID);
    }

    public void setArticleId(String articleId) {
        put(ARTICLE_ID, articleId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getArticleAuditStatus() {
        return getString(ARTICLE_AUDIT_STATUS);
    }

    public void setArticleAuditStatus(String articleAuditStatus) {
        put(ARTICLE_AUDIT_STATUS, articleAuditStatus);
    }

    public Date getArticleAuditTime() {
        return getDate(ARTICLE_AUDIT_TIME);
    }

    public void setArticleAuditTime(Date articleAuditTime) {
        put(ARTICLE_AUDIT_TIME, articleAuditTime);
    }

    public String getArticleAuditContent() {
        return getString(ARTICLE_AUDIT_CONTENT);
    }

    public void setArticleAuditContent(String articleAuditContent) {
        put(ARTICLE_AUDIT_CONTENT, articleAuditContent);
    }


}