package com.nowui.cloud.cms.article.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.cms.article.view.ArticleView;
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

    @ApiOperation(value = "文章分页列表")
    @RequestMapping(value = "/article/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        ArticleView articleEntity = getEntry(ArticleView.class);

        validateRequest(
                articleEntity,
                ArticleView.APP_ID,
                ArticleView.ARTICLE_TITLE,
                ArticleView.PAGE_INDEX,
                ArticleView.PAGE_SIZE
        );

        Integer resultTotal = articleService.countForAdmin(articleEntity.getAppId(), articleEntity.getArticleTitle());
        List<ArticleView> resultList = articleService.listForAdmin(articleEntity.getAppId(), articleEntity.getArticleTitle(), articleEntity.getPageIndex(), articleEntity.getPageSize());

        validateResponse(
                ArticleView.ARTICLE_ID,
                ArticleView.ARTICLE_CATEGORY_NAME,
                ArticleView.ARTICLE_TITLE,
                Article.ARTICLE_MEDIA_PATH,
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
        ArticleView articleView = getEntry(ArticleView.class);

        validateRequest(articleView, Article.ARTICLE_ID);

        ArticleView result = articleService.find(articleView.getArticleId());

        validateResponse(
                ArticleView.ARTICLE_ID,
                ArticleView.ARTICLE_TITLE,
                ArticleView.ARTICLE_AUTHOR,
                ArticleView.ARTICLE_SUMMARY,
                ArticleView.ARTICLE_CONTENT,
                ArticleView.ARTICLE_MEDIA_ID,
                ArticleView.ARTICLE_MEDIA_PATH,
                ArticleView.ARTICLE_MEDIA_TYPE,
                ArticleView.ARTICLE_IS_ALLOW_COMMENT,
                ArticleView.ARTICLE_IS_DRAFT,
                ArticleView.ARTICLE_IS_OUTER_LINK,
                ArticleView.ARTICLE_IS_TOP,
                ArticleView.ARTICLE_TOP_LEVEL,
                ArticleView.ARTICLE_KEYWORDS,
                ArticleView.ARTICLE_OUTER_LINK,
                ArticleView.ARTICLE_PUBLISH_TIME,
                ArticleView.ARTICLE_TOP_END_TIME,
                ArticleView.ARTICLE_SORT,
                ArticleView.ARTICLE_IS_REQUIRE_AUDIT,
                ArticleView.ARTICLE_IS_RECOMMEND,
                ArticleView.ARTICLE_TAGS,
                ArticleView.ARTICLE_SOURCE,
                ArticleView.ARTICLE_WEIGHT,
                ArticleView.SYSTEM_VERSION,
                ArticleView.ARTICLE_MEDIA_LIST,
                ArticleView.ARTICLE_PRIMARY_ARTICLE_CATEGORY,
                ArticleView.ARTICLE_SECONDARY_ARTICLE_CATEGORY_LIST
        );

        validateSecondResponse(ArticleView.ARTICLE_PRIMARY_ARTICLE_CATEGORY, ArticleArticleCategory.ARTICLE_CATEGORY_ID);
        validateSecondResponse(ArticleView.ARTICLE_SECONDARY_ARTICLE_CATEGORY_LIST, ArticleArticleCategory.ARTICLE_CATEGORY_ID);

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
//                Article.ARTICLE_MEDIA_ID,
                Article.ARTICLE_MEDIA_TYPE,
                Article.ARTICLE_IS_ALLOW_COMMENT,
                Article.ARTICLE_IS_DRAFT,
                Article.ARTICLE_IS_OUTER_LINK,
                Article.ARTICLE_IS_TOP,
                Article.ARTICLE_TOP_LEVEL,
                Article.ARTICLE_KEYWORDS,
                Article.ARTICLE_OUTER_LINK,
                Article.ARTICLE_PUBLISH_TIME,
                Article.ARTICLE_SORT,
                Article.ARTICLE_IS_REQUIRE_AUDIT,
                Article.ARTICLE_IS_RECOMMEND,
                Article.ARTICLE_TAGS,
                Article.ARTICLE_SOURCE,
                Article.ARTICLE_WEIGHT,
                Article.ARTICLE_MEDIA_LIST,
                Article.ARTICLE_PRIMARY_ARTICLE_CATEGORY
        );

        String articleId = Util.getRandomUUID();

        //文章主分类
        ArticleArticleCategory articlePrimaryArticleCategory = JSON.parseObject(articleEntity.getArticlePrimaryArticleCategory().toJSONString(), ArticleArticleCategory.class);

        //文章二级分类
        List<ArticleArticleCategory> articleSecondaryArticleCategoryList = JSON.parseArray(articleEntity.getArticleSecondaryArticleCategoryList().toJSONString(), ArticleArticleCategory.class);

        //文章媒体列表
        List<ArticleMedia> articleMediaList = articleEntity.getArticleMediaList().toJavaList(ArticleMedia.class);

        Article result = articleService.save(articleEntity, articleId, articlePrimaryArticleCategory, articleSecondaryArticleCategoryList, articleMediaList, articleEntity.getSystemRequestUserId());

        if (result != null) {
            ArticleView articleView = new ArticleView();
            articleView.putAll(result);

            articleService.save(articleView);
        }

        return renderJson(true);
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
                Article.ARTICLE_MEDIA_PATH,
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
                Article.ARTICLE_MEDIA_LIST,
                Article.ARTICLE_PRIMARY_ARTICLE_CATEGORY
        );

        ArticleArticleCategory articlePrimaryArticleCategory = JSON.parseObject(articleEntity.getArticlePrimaryArticleCategory().toJSONString(), ArticleArticleCategory.class);

        List<ArticleArticleCategory> articleSecondaryArticleCategoryList = JSON.parseArray(articleEntity.getArticleSecondaryArticleCategoryList().toJSONString(), ArticleArticleCategory.class);

        List<ArticleMedia> articleMediaList = articleEntity.getArticleMediaList().toJavaList(ArticleMedia.class);

        Article result = articleService.update(articleEntity, articlePrimaryArticleCategory, articleSecondaryArticleCategoryList, articleMediaList, articleEntity.getSystemRequestUserId());

        if (result != null) {
            ArticleView articleView = new ArticleView();
            articleView.putAll(result);
            
            articleService.update(articleView);
        }

        return renderJson(true);
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

        Article result = articleService.delete(articleEntity.getArticleId(), articleEntity.getSystemRequestUserId(), articleEntity.getSystemVersion());

        if (result != null) {
            ArticleView articleView = new ArticleView();
            articleView.putAll(result);
            
            articleService.delete(articleView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "文章数据同步")
    @RequestMapping(value = "/article/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Article> articleList = articleService.listByMysql();

        for(Article article : articleList) {
            ArticleView articleView = new ArticleView();
            articleView.putAll(article);

            articleService.saveOrUpdate(articleView);
        }

        return renderJson(true);
    }

}