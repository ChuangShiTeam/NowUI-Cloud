package com.nowui.cloud.cms.article.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章审核
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component
@Document(indexName = "nowui", type = "article_audit_info")
@TableName(value = "article_audit_info")
public class ArticleAudit extends BaseEntity {

    /**
     * 文章审核编号
     */
    @Id
    @TableId
    @NotNull(message = "文章审核编号不能为空")
    @Length(max = 32, message = "文章审核编号长度超出限制")
    private String articleAuditId;
    public static final String ARTICLE_AUDIT_ID = "articleAuditId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章编号
     */
    @Field
    @TableField
    @NotNull(message = "文章编号不能为空")
    @Length(max = 32, message = "文章编号长度超出限制")
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 审核人编号
     */
    @Field
    @TableField
    @NotNull(message = "审核人编号不能为空")
    @Length(max = 32, message = "审核人编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 审核状态
     */
    @Field
    @TableField
    @NotNull(message = "审核状态不能为空")
    @Length(max = 25, message = "审核状态长度超出限制")
    private String articleAuditStatus;
    public static final String ARTICLE_AUDIT_STATUS = "articleAuditStatus";

    /**
     * 审核时间
     */
    @Field
    @TableField
    @NotNull(message = "审核时间不能为空")
    @Length(max = 0, message = "审核时间长度超出限制")
    private Date articleAuditTime;
    public static final String ARTICLE_AUDIT_TIME = "articleAuditTime";


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


}