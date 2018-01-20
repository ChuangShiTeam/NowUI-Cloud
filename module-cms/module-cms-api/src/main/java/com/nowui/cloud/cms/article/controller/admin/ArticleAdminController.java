package com.nowui.cloud.cms.article.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
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
 * <p>
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
    @RequestMapping(value = "/article/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(
                articleEntity,
                Article.APP_ID,
                Article.ARTICLE_TITLE,
                Article.PAGE_INDEX,
                Article.PAGE_SIZE
        );

        Integer resultTotal = articleService.countForAdmin(articleEntity.getAppId(), articleEntity.getArticleTitle());
        List<Article> resultList = articleService.listForAdmin(articleEntity.getAppId(), articleEntity.getArticleTitle(), articleEntity.getPageIndex(), articleEntity.getPageSize());

        String fileIds = Util.beanToFieldString(resultList, Article.ARTICLE_MEDIA_ID);
        List<File> fileList = fileRpc.findsV1(fileIds);

        resultList = Util.beanAddField(resultList, Article.ARTICLE_MEDIA_ID, fileList, File.FILE_PATH);

        validateResponse(
                Article.ARTICLE_ID,
                ArticleCategory.ARTICLE_CATEGORY_NAME,
                Article.ARTICLE_TITLE,
                File.FILE_PATH,
                Article.ARTICLE_MEDIA_TYPE,
                Article.ARTICLE_AUTHOR,
                Article.ARTICLE_PUBLISH_TIME,
                Article.ARTICLE_IS_TOP,
                Article.ARTICLE_IS_DRAFT,
                Article.ARTICLE_IS_RECOMMEND
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询文章信息")
    @RequestMapping(value = "/article/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(articleEntity, Article.ARTICLE_ID);

        Article result = articleService.find(articleEntity.getArticleId());
        //查询文章分类
        List<ArticleArticleCategory> articleArticleCategoryList = articleArticleCategoryService.listByArticleId(articleEntity.getArticleId());
        for (ArticleArticleCategory articleArticleCategory : articleArticleCategoryList) {
            articleArticleCategory.keep(ArticleArticleCategory.ARTICLE_CATEGORY_ID, ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY);
        }
        result.put(Article.ARTICLE_ARTICLE_CATEGORY_LIST, articleArticleCategoryList);
        //查询文章主媒体
        if (!Util.isNullOrEmpty(result.getArticleMediaId())) {
            System.out.println(result.toJSONString());
            System.out.println(result.getArticleMediaId());
            File file = fileRpc.findV1(result.getArticleMediaId());
            file.keep(File.FILE_ID, File.FILE_PATH);
            result.put(Article.ARTICLE_MEDIA, file);
        }
        //查询文章副媒体
        List<ArticleMedia> articleMeidaList = articleMediaService.listByArticleId(articleEntity.getArticleId());

        if (Util.isNullOrEmpty(articleMeidaList)) {
            result.put(Article.ARTICLE_MEDIA_LIST, new ArrayList<>());
        } else {
            String fileIds = Util.beanToFieldString(articleMeidaList, ArticleMedia.FILE_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);

            articleMeidaList = Util.beanAddField(articleMeidaList, ArticleMedia.FILE_ID, fileList, File.FILE_ID, File.FILE_PATH);
            result.put(Article.ARTICLE_MEDIA_LIST, articleMeidaList);
        }


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
                Article.ARTICLE_IS_RECOMMEND,
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
    @RequestMapping(value = "/article/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(
                articleEntity,
                Article.APP_ID,
                Article.ARTICLE_TITLE,
                Article.ARTICLE_AUTHOR,
                Article.ARTICLE_SUMMARY,
                Article.ARTICLE_CONTENT,
                Article.ARTICLE_MEDIA_ID,
                Article.ARTICLE_MEDIA_TYPE,
                Article.ARTICLE_IS_ALLOW_COMMENT,
                Article.ARTICLE_IS_DRAFT,
                Article.ARTICLE_IS_OUTER_LINK,
                Article.ARTICLE_IS_TOP,
                Article.ARTICLE_KEYWORDS,
                Article.ARTICLE_OUTER_LINK,
                Article.ARTICLE_PUBLISH_TIME,
                Article.ARTICLE_SORT,
                Article.ARTICLE_IS_REQUIRE_AUDIT,
                Article.ARTICLE_IS_RECOMMEND,
                Article.ARTICLE_TAGS,
                Article.ARTICLE_SOURCE,
                Article.ARTICLE_WEIGHT
        );

        JSONArray articleCategoryJsonArray = articleEntity.getJSONArray(Article.ARTICLE_ARTICLE_CATEGORY_LIST);
        if (Util.isNullOrEmpty(articleCategoryJsonArray)) {
            throw new RuntimeException("文章没有选择文章主分类");
        }
        List<ArticleArticleCategory> articleArticleCategoryList = JSONArray.parseArray(articleCategoryJsonArray.toJSONString(), ArticleArticleCategory.class);

        JSONArray articleMediaJsonArray = articleEntity.getJSONArray(Article.ARTICLE_MEDIA_LIST);
        List<ArticleMedia> mediaList = new ArrayList<ArticleMedia>();
        if (!Util.isNullOrEmpty(articleMediaJsonArray)) {
            mediaList = JSONArray.parseArray(articleMediaJsonArray.toJSONString(), ArticleMedia.class);
        }

        // 文章置顶级别
        if (Util.isNullOrEmpty(articleEntity.getArticleTopLevel())) {
            articleEntity.setArticleTopLevel(null);
        }

        Boolean result = articleService.save(articleArticleCategoryList, mediaList, articleEntity, articleEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "文章修改")
    @RequestMapping(value = "/article/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(
                articleEntity,
                Article.ARTICLE_ID,
                Article.ARTICLE_TITLE,
                Article.ARTICLE_AUTHOR,
                Article.ARTICLE_SUMMARY,
                Article.ARTICLE_CONTENT,
                Article.ARTICLE_MEDIA_ID,
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
                Article.ARTICLE_IS_RECOMMEND,
                Article.ARTICLE_TAGS,
                Article.ARTICLE_SOURCE,
                Article.ARTICLE_WEIGHT,
                Article.SYSTEM_VERSION
        );

        JSONArray articleCategoryJsonArray = articleEntity.getJSONArray(Article.ARTICLE_ARTICLE_CATEGORY_LIST);
        if (Util.isNullOrEmpty(articleCategoryJsonArray)) {
            throw new RuntimeException("文章没有选择文章主分类");
        }
        List<ArticleArticleCategory> articleArticleCategoryList = JSONArray.parseArray(articleCategoryJsonArray.toJSONString(), ArticleArticleCategory.class);

        JSONArray articleMediaJsonArray = articleEntity.getJSONArray(Article.ARTICLE_MEDIA_LIST);
        List<ArticleMedia> mediaList = new ArrayList<ArticleMedia>();
        if (!Util.isNullOrEmpty(articleMediaJsonArray)) {
            mediaList = JSONArray.parseArray(articleMediaJsonArray.toJSONString(), ArticleMedia.class);
        }

        // 文章置顶级别
        if (Util.isNullOrEmpty(articleEntity.getArticleTopLevel())) {
            articleEntity.setArticleTopLevel(null);
        }

        Boolean result = articleService.update(articleArticleCategoryList, mediaList, articleEntity, articleEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "文章删除")
    @RequestMapping(value = "/article/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(
                articleEntity,
                Article.ARTICLE_ID,
                Article.SYSTEM_VERSION
        );

        Boolean result = articleService.delete(articleEntity.getArticleId(), articleEntity.getSystemRequestUserId(), articleEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "文章重建缓存")
    @RequestMapping(value = "/article/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        Article articleEntity = getEntry(Article.class);

        validateRequest(articleEntity, Article.ARTICLE_ID);

        articleService.replace(articleEntity.getArticleId());

        return renderJson(true);
    }

}
