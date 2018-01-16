package com.nowui.cloud.cms.article.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.rpc.ArticleRpc;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 文章系统端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章", description = "文章接系统端管理")
@RestController
public class ArticleSystemController implements ArticleRpc {
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private FileRpc fileRpc;

    @Override
    public List<Article> listByCategoryCodeV1(String appId, String articleCategoryCode) {
        
        List<Article> articleList = articleService.listByPrimaryCategoryCode(appId, articleCategoryCode);
        
        if (Util.isNullOrEmpty(articleList)) {
            return new ArrayList<>();
        }
        String fileIds = Util.beanToFieldString(articleList, Article.ARTICLE_MEDIA_ID);
        
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        articleList = Util.beanAddField(articleList, Article.ARTICLE_MEDIA_ID, fileList, File.FILE_PATH);
        
        return articleList;
    }

}
