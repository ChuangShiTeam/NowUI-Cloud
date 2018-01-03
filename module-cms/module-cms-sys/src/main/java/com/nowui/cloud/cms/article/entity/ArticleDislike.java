package com.nowui.cloud.cms.article.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 文章鄙视
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component
@TableName(value = "article_dislike_info")
public class ArticleDislike extends BaseEntity {

    /**
     * 文章鄙视编号
     */
    @TableId
    @NotNull(message = "文章鄙视编号不能为空")
    @Length(max = 32, message = "文章鄙视编号长度超出限制")
    private String articleDislikeId;
    public static final String ARTICLE_DISLIKE_ID = "articleDislikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 文章编号
     */
    @TableField
    @NotNull(message = "文章编号不能为空")
    @Length(max = 32, message = "文章编号长度超出限制")
    private String articleId;
    public static final String ARTICLE_ID = "articleId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getArticleDislikeId() {
        return getString(ARTICLE_DISLIKE_ID);
    }
    public void setArticleDislikeId(String articleDislikeId) {
        put(ARTICLE_DISLIKE_ID, articleDislikeId);
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