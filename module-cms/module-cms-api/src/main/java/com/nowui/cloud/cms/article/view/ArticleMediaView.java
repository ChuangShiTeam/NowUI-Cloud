package com.nowui.cloud.cms.article.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 文章多媒体视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "article_media_info")
public class ArticleMediaView extends BaseView {

    /**
     * 文章多媒体编号
     */
    @Field
    private String articleMediaId;
    public static final String ARTICLE_MEDIA_ID = "articleMediaId";

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
     * 文件编号
     */
    @Field
    private String fileId;
    public static final String FILE_ID = "fileId";

    /**
     * 排序
     */
    @Field
    private Integer articleMediaSort;
    public static final String ARTICLE_MEDIA_SORT = "articleMediaSort";


    public String getArticleMediaId() {
        return getString(ARTICLE_MEDIA_ID);
    }

    public void setArticleMediaId(String articleMediaId) {
        put(ARTICLE_MEDIA_ID, articleMediaId);
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

    public String getFileId() {
        return getString(FILE_ID);
    }

    public void setFileId(String fileId) {
        put(FILE_ID, fileId);
    }

    public Integer getArticleMediaSort() {
        return getInteger(ARTICLE_MEDIA_SORT);
    }

    public void setArticleMediaSort(Integer articleMediaSort) {
        put(ARTICLE_MEDIA_SORT, articleMediaSort);
    }


}