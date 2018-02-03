package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章用户评论点赞视图
 *
 * @author x
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_user_comment_user_like_info")
public class ArticleUserCommentUserLikeView extends BaseView {

    /**
     * 文章用户评论用户点赞编号
     */
    @Field
    private String articleUserCommentUserLikeId;
    public static final String ARTICLE_USER_COMMENT_USER_LIKE_ID = "articleUserCommentUserLikeId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章用户评论编号
     */
    @Field
    private String articleUserCommentId;
    public static final String ARTICLE_USER_COMMENT_ID = "articleUserCommentId";

    /**
     * 用户编号
     */
    @Field
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