package com.nowui.cloud.cms.article.entity;

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
 * 文章用户评论
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "article_user_comment_info")
@TableName(value = "article_user_comment_info")
public class ArticleUserComment extends BaseEntity {

    /**
     * 文章用户评论编号
     */
    @Id
    @TableId
    @NotNull(message = "文章用户评论编号不能为空")
    @Length(max = 32, message = "文章用户评论编号长度超出限制")
    private String articleUserCommentId;
    public static final String ARTICLE_USER_COMMENT_ID = "articleUserCommentId";

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
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 回复的评论编号
     */
    @Field
    @TableField
    @NotNull(message = "回复的评论编号不能为空")
    @Length(max = 32, message = "回复的评论编号长度超出限制")
    private String articleReolyCommentId;
    public static final String ARTICLE_REOLY_COMMENT_ID = "articleReolyCommentId";

    /**
     * 回复的用户编号
     */
    @Field
    @TableField
    @NotNull(message = "回复的用户编号不能为空")
    @Length(max = 32, message = "回复的用户编号长度超出限制")
    private String articleReplyUserId;
    public static final String ARTICLE_REPLY_USER_ID = "articleReplyUserId";

    /**
     * 评论内容
     */
    @Field
    @TableField
    @NotNull(message = "评论内容不能为空")
    @Length(max = 2000, message = "评论内容长度超出限制")
    private String articleCommentContent;
    public static final String ARTICLE_COMMENT_CONTENT = "articleCommentContent";


    public String getArticleUserCommentId() {
        return getString(ARTICLE_USER_COMMENT_ID);
    }
    
    public void setArticleUserCommentId(String articleUserCommentId) {
        put(ARTICLE_USER_COMMENT_ID, articleUserCommentId);
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

    public String getArticleReolyCommentId() {
        return getString(ARTICLE_REOLY_COMMENT_ID);
    }
    
    public void setArticleReolyCommentId(String articleReolyCommentId) {
        put(ARTICLE_REOLY_COMMENT_ID, articleReolyCommentId);
    }

    public String getArticleReplyUserId() {
        return getString(ARTICLE_REPLY_USER_ID);
    }
    
    public void setArticleReplyUserId(String articleReplyUserId) {
        put(ARTICLE_REPLY_USER_ID, articleReplyUserId);
    }

    public String getArticleCommentContent() {
        return getString(ARTICLE_COMMENT_CONTENT);
    }
    
    public void setArticleCommentContent(String articleCommentContent) {
        put(ARTICLE_COMMENT_CONTENT, articleCommentContent);
    }


}