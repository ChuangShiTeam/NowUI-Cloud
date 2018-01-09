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
 * 文章用户鄙视
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "article_user_dislike_info")
@TableName(value = "article_user_dislike_info")
public class ArticleUserDislike extends BaseEntity {

    /**
     * 文章用户鄙视编号
     */
    @Id
    @TableId
    @NotNull(message = "文章用户鄙视编号不能为空")
    @Length(max = 32, message = "文章用户鄙视编号长度超出限制")
    private String articleUserDislikeId;
    public static final String ARTICLE_USER_DISLIKE_ID = "articleUserDislikeId";

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


    public String getArticleUserDislikeId() {
        return getString(ARTICLE_USER_DISLIKE_ID);
    }
    
    public void setArticleUserDislikeId(String articleUserDislikeId) {
        put(ARTICLE_USER_DISLIKE_ID, articleUserDislikeId);
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