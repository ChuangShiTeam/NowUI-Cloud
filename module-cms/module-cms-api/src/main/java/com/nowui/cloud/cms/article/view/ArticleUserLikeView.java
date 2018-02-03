package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章用户点赞视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_user_like_info")
public class ArticleUserLikeView extends BaseView {

    /**
     * 文章点赞编号
     */
    @Field
    private String articleUserLikeId;
    public static final String ARTICLE_USER_LIKE_ID = "articleUserLikeId";

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


    public String getArticleUserLikeId() {
        return getString(ARTICLE_USER_LIKE_ID);
    }

    public void setArticleUserLikeId(String articleUserLikeId) {
        put(ARTICLE_USER_LIKE_ID, articleUserLikeId);
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


}