package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章用户评论视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_user_comment_info")
public class ArticleUserCommentView extends BaseView {

    /**
     * 文章用户评论编号
     */
    @Field
    private String articleUserCommentId;
    public static final String ARTICLE_USER_COMMENT_ID = "articleUserCommentId";

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
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 回复的评论编号
     */
    @Field
    private String articleReolyCommentId;
    public static final String ARTICLE_REOLY_COMMENT_ID = "articleReolyCommentId";

    /**
     * 回复的用户编号
     */
    @Field
    private String articleReplyUserId;
    public static final String ARTICLE_REPLY_USER_ID = "articleReplyUserId";

    /**
     * 评论内容
     */
    @Field
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