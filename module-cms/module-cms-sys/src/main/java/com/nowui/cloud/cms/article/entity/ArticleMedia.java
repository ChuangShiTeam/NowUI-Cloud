package com.nowui.cloud.cms.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 文章多媒体
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Component
@TableName(value = "article_media_info")
public class ArticleMedia extends BaseEntity {

    /**
     * 文章多媒体编号
     */
    @Id
    @TableId
    @NotNull(message = "文章多媒体编号不能为空")
    @Length(max = 32, message = "文章多媒体编号长度超出限制")
    private String articleMediaId;
    public static final String ARTICLE_MEDIA_ID = "articleMediaId";

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
     * 文件编号
     */
    @TableField
    @NotNull(message = "文件编号不能为空")
    @Length(max = 32, message = "文件编号长度超出限制")
    private String fileId;
    public static final String FILE_ID = "fileId";
    
    /**
     * 文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "文件路径不能为空")
    @Length(max = 200, message = "文件路径长度超出限制")
    private String filePath;
    public static final String FILE_PATH = "filePath";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    @Length(max = 11, message = "排序长度超出限制")
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
    
    public String getFilePath() {
        return getString(FILE_PATH);
    }
    
    public void setFilePath(String filePath) {
        put(FILE_PATH, filePath);
    }

    public Integer getArticleMediaSort() {
        return getInteger(ARTICLE_MEDIA_SORT);
    }
    
    public void setArticleMediaSort(Integer articleMediaSort) {
        put(ARTICLE_MEDIA_SORT, articleMediaSort);
    }

}