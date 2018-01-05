package com.nowui.cloud.cms.article.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章后台端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章", description = "文章接口后台端管理")
@RestController
public class ArticleAdminController extends BaseController {
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleArticleCategoryService articleArticleCategoryService;
    
    @Autowired
    private ArticleMediaService articleMediaService;
    
    @Autowired
    private FileRpc fileRpc;
    
    @ApiOperation(value = "文章分页列表")
    @RequestMapping(value = "/article/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.APP_ID, 
            Article.ARTICLE_TITLE, 
            Article.PAGE_INDEX, 
            Article.PAGE_SIZE
        );

        Integer resultTotal = articleService.adminCount(body.getAppId(), body.getArticleTitle());
        List<Article> resultList = articleService.adminList(body.getAppId(), body.getArticleTitle(), body.getPageIndex(), body.getPageSize());

        validateResponse(
            Article.ARTICLE_ID, 
            ArticleCategory.ARTICLE_CATEGORY_NAME, 
            Article.ARTICLE_TITLE,
            Article.ARTICLE_MEDIA,
            Article.ARTICLE_MEDIA_TYPE,
            Article.ARTICLE_AUTHOR,
            Article.ARTICLE_PUBLISH_TIME,
            Article.ARTICLE_IS_TOP,
            Article.ARTICLE_IS_DRAFT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询文章信息")
    @RequestMapping(value = "/article/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Article body) {
        validateRequest(body, Article.ARTICLE_ID);

        Article result = articleService.find(body.getArticleId());
        
        //查询文章分类
        List<ArticleArticleCategory> articleArticleCategoryList = articleArticleCategoryService.listByArticleId(body.getArticleId());
        for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
            articleArticleCategory.keep(ArticleArticleCategory.ARTICLE_CATEGORY_ID, ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY);
        }
        result.put(Article.ARTICLE_ARTICLE_CATEGORY_LIST, articleArticleCategoryList);
        //查询文章主媒体
        if (!Util.isNullOrEmpty(result.getArticleMedia())) {
            File file = fileRpc.find(result.getArticleMedia());
            file.keep(File.FILE_ID, File.FILE_PATH);
            result.put(Article.ARTICLE_MEDIA, file);
        }
        //查询文章副媒体
        List<ArticleMedia> articleMeidaList = articleMediaService.listByArticleId(body.getArticleId());
        for (ArticleMedia articleMedia : articleMeidaList) {
            articleMedia.put(File.FILE_PATH, fileRpc.find(articleMedia.getFileId()).getFilePath());
            articleMedia.keep(ArticleMedia.FILE_ID, File.FILE_PATH);
        }
        result.put(Article.ARTICLE_MEDIA_LIST, articleMeidaList);

        validateResponse(
            Article.ARTICLE_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_MEDIA, 
            Article.ARTICLE_MEDIA_TYPE,
            Article.ARTICLE_IS_ALLOW_COMMENT,
            Article.ARTICLE_IS_DRAFT,
            Article.ARTICLE_IS_OUTER_LINK,
            Article.ARTICLE_IS_TOP,
            Article.ARTICLE_KEYWORDS,
            Article.ARTICLE_OUTER_LINK,
            Article.ARTICLE_PUBLISH_TIME,
            Article.ARTICLE_TOP_END_TIME,
            Article.ARTICLE_SORT,
            Article.ARTICLE_IS_REQUIRE_AUDIT,
            Article.ARTICLE_TAGS,
            Article.ARTICLE_SOURCE,
            Article.ARTICLE_WEIGHT,
            Article.SYSTEM_VERSION,
            Article.ARTICLE_ARTICLE_CATEGORY_LIST,
            Article.ARTICLE_MEDIA_LIST
        );

        return renderJson(result);
    }

    @ApiOperation(value = "文章新增")
    @RequestMapping(value = "/article/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.APP_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_MEDIA, 
            Article.ARTICLE_MEDIA_TYPE,
            Article.ARTICLE_IS_ALLOW_COMMENT,
            Article.ARTICLE_IS_DRAFT,
            Article.ARTICLE_IS_OUTER_LINK,
            Article.ARTICLE_IS_TOP,
            Article.ARTICLE_KEYWORDS,
            Article.ARTICLE_OUTER_LINK,
            Article.ARTICLE_PUBLISH_TIME,
            Article.ARTICLE_TOP_END_TIME,
            Article.ARTICLE_SORT,
            Article.ARTICLE_IS_REQUIRE_AUDIT,
            Article.ARTICLE_TAGS,
            Article.ARTICLE_SOURCE,
            Article.ARTICLE_WEIGHT
        );

        String articleCategoryJsonString = body.getString(Article.ARTICLE_ARTICLE_CATEGORY_LIST);
        if (Util.isNullOrEmpty(articleCategoryJsonString)) {
            throw new RuntimeException("文章没有选择文章分类");
        }
        List<ArticleArticleCategory> articleArticleCategoryList = JSONArray.parseArray(articleCategoryJsonString, ArticleArticleCategory.class);
        
        String articleMediaJsonString = body.getString(Article.ARTICLE_MEDIA_LIST);
        List<ArticleMedia> mediaList = new ArrayList<ArticleMedia>();
        if (!Util.isNullOrEmpty(articleMediaJsonString)) {
            mediaList = JSONArray.parseArray(articleMediaJsonString, ArticleMedia.class);
        }
       
        Boolean result = articleService.save(articleArticleCategoryList, mediaList, body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "文章修改")
    @RequestMapping(value = "/article/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.ARTICLE_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_MEDIA, 
            Article.ARTICLE_MEDIA_TYPE,
            Article.ARTICLE_IS_ALLOW_COMMENT,
            Article.ARTICLE_IS_DRAFT,
            Article.ARTICLE_IS_OUTER_LINK,
            Article.ARTICLE_IS_TOP,
            Article.ARTICLE_KEYWORDS,
            Article.ARTICLE_OUTER_LINK,
            Article.ARTICLE_PUBLISH_TIME,
            Article.ARTICLE_TOP_END_TIME,
            Article.ARTICLE_SORT,
            Article.ARTICLE_IS_REQUIRE_AUDIT,
            Article.ARTICLE_TAGS,
            Article.ARTICLE_SOURCE,
            Article.ARTICLE_WEIGHT,
            Article.SYSTEM_VERSION
        );
        
        String articleCategoryJsonString = body.getString(Article.ARTICLE_ARTICLE_CATEGORY_LIST);
        if (Util.isNullOrEmpty(articleCategoryJsonString)) {
            throw new RuntimeException("文章没有选择文章分类");
        }
        JSONArray articleCategoryJsonArray = JSONArray.parseArray(articleCategoryJsonString);
        
        List<ArticleArticleCategory> articleArticleCategoryList = articleCategoryJsonArray.toJavaList(ArticleArticleCategory.class);
        
        String articleMediaJsonString = body.getString(Article.ARTICLE_MEDIA_LIST);
        List<ArticleMedia> mediaList = new ArrayList<ArticleMedia>();
        if (!Util.isNullOrEmpty(articleMediaJsonString)) {
            JSONArray articleMediaJSONArray = JSONArray.parseArray(articleMediaJsonString);
            mediaList = articleMediaJSONArray.toJavaList(ArticleMedia.class);
        }

        Boolean result = articleService.update(articleArticleCategoryList, mediaList, body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "文章删除")
    @RequestMapping(value = "/article/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.ARTICLE_ID, 
            Article.SYSTEM_VERSION
        );

        Boolean result = articleService.delete(body.getArticleId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}
