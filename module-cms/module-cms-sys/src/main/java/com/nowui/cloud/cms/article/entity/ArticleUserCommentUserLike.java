package com.nowui.cloud.cms.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章用户评论用户点赞
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "article_user_comment_user_like_info")
public class ArticleUserCommentUserLike extends BaseEntity {

    /**
     * 文章用户评论用户点赞编号
     */
    @Id
    @TableId
    @NotNull(message = "文章用户评论用户点赞编号不能为空")
    @Length(max = 32, message = "文章用户评论用户点赞编号长度超出限制")
    private String articleUserCommentUserLikeId;
    public static final String ARTICLE_USER_COMMENT_USER_LIKE_ID = "articleUserCommentUserLikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章用户评论编号
     */
    @TableField
    @NotNull(message = "文章用户评论编号不能为空")
    @Length(max = 32, message = "文章用户评论编号长度超出限制")
    private String articleUserCommentId;
    public static final String ARTICLE_USER_COMMENT_ID = "articleUserCommentId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getArticleUserCommentUserLikeId() {
        return getString(ARTICLE_USER_COMMENT_USER_LIKE_ID);
    }
    
    public void setArticleUserCommentUserLikeId(String articleUserCommentUserLikeId) {
        put(ARTICLE_USER_COMMENT_USER_LIKE_ID, articleUserCommentUserLikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getArticleUserCommentId() {
        return getString(ARTICLE_USER_COMMENT_ID);
    }
    
    public void setArticleUserCommentId(String articleUserCommentId) {
        put(ARTICLE_USER_COMMENT_ID, articleUserCommentId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}