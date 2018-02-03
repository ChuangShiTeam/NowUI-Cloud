package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章用户收藏视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_user_bookmark_info")
public class ArticleUserBookmarkView extends BaseView {

    /**
     * 文章用户收藏编号
     */
    @Field
    private String articleUserBookMarkId;
    public static final String ARTICLE_USER_BOOK_MARK_ID = "articleUserBookMarkId";

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
    private String useId;
    public static final String USE_ID = "useId";


    public String getArticleUserBookMarkId() {
        return getString(ARTICLE_USER_BOOK_MARK_ID);
    }

    public void setArticleUserBookMarkId(String articleUserBookMarkId) {
        put(ARTICLE_USER_BOOK_MARK_ID, articleUserBookMarkId);
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

    public String getUseId() {
        return getString(USE_ID);
    }

    public void setUseId(String useId) {
        put(USE_ID, useId);
    }


}