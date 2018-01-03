package com.nowui.cloud.cms.article.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 文章收藏
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component
@TableName(value = "article_bookmark_info")
public class ArticleBookmark extends BaseEntity {

    /**
     * 文章收藏编号
     */
    @TableId
    @NotNull(message = "文章收藏编号不能为空")
    @Length(max = 32, message = "文章收藏编号长度超出限制")
    private String articleBookMarkId;
    public static final String ARTICLE_BOOK_MARK_ID = "articleBookMarkId";

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
    private String useId;
    public static final String USE_ID = "useId";


    public String getArticleBookMarkId() {
        return getString(ARTICLE_BOOK_MARK_ID);
    }
    public void setArticleBookMarkId(String articleBookMarkId) {
        put(ARTICLE_BOOK_MARK_ID, articleBookMarkId);
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