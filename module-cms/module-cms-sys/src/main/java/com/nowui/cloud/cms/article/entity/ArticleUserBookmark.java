package com.nowui.cloud.cms.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章用户收藏
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "article_user_bookmark_info")
public class ArticleUserBookmark extends BaseEntity {

    /**
     * 文章用户收藏编号
     */
    @Id
    @TableId
    @NotNull(message = "文章用户收藏编号不能为空")
    @Length(max = 32, message = "文章用户收藏编号长度超出限制")
    private String articleUserBookMarkId;
    public static final String ARTICLE_USER_BOOK_MARK_ID = "articleUserBookMarkId";

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