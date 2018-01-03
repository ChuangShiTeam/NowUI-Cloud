package com.nowui.cloud.cms.article.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 文章评论点赞
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component
@TableName(value = "article_comment_like_info")
public class ArticleCommentLike extends BaseEntity {

    /**
     * 文章评论点赞编号
     */
    @TableId
    @NotNull(message = "文章评论点赞编号不能为空")
    @Length(max = 32, message = "文章评论点赞编号长度超出限制")
    private String articleCommentLikeId;
    public static final String ARTICLE_COMMENT_LIKE_ID = "articleCommentLikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章评论编号
     */
    @TableField
    @NotNull(message = "文章评论编号不能为空")
    @Length(max = 32, message = "文章评论编号长度超出限制")
    private String articleCommentId;
    public static final String ARTICLE_COMMENT_ID = "articleCommentId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getArticleCommentLikeId() {
        return getString(ARTICLE_COMMENT_LIKE_ID);
    }
    public void setArticleCommentLikeId(String articleCommentLikeId) {
        put(ARTICLE_COMMENT_LIKE_ID, articleCommentLikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getArticleCommentId() {
        return getString(ARTICLE_COMMENT_ID);
    }
    public void setArticleCommentId(String articleCommentId) {
        put(ARTICLE_COMMENT_ID, articleCommentId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}