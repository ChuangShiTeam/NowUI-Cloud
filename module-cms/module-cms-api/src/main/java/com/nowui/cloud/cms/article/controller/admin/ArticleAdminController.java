package com.nowui.cloud.cms.article.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.controller.BaseController;

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
    
    @ApiOperation(value = "文章分页列表")
    @RequestMapping(value = "/article/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.APP_ID, 
            Article.ARTICLE_CATEGORY_ID, 
            Article.ARTICLE_TITLE, 
            Article.PAGE_INDEX, 
            Article.PAGE_SIZE
        );

        Integer resultTotal = articleService.adminCount(body.getAppId(), body.getArticleCategoryId(), body.getArticleTitle());
        List<Article> resultList = articleService.adminList(body.getAppId(), body.getArticleCategoryId(), body.getArticleTitle(), body.getM(), body.getN());

        validateResponse(
            Article.ARTICLE_ID, 
            ArticleCategory.ARTICLE_CATEGORY_NAME, 
            Article.ARTICLE_TITLE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询文章信息")
    @RequestMapping(value = "/article/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Article body) {
        validateRequest(body, Article.ARTICLE_ID);

        Article result = articleService.find(body.getArticleId());

        validateResponse(
            Article.ARTICLE_ID, 
            Article.ARTICLE_CATEGORY_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_COVER, 
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

        return renderJson(result);
    }

    @ApiOperation(value = "文章新增")
    @RequestMapping(value = "/article/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.APP_ID, 
            Article.ARTICLE_CATEGORY_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_COVER, 
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

        Boolean result = articleService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "文章修改")
    @RequestMapping(value = "/article/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Article body) {
        validateRequest(
            body, 
            Article.ARTICLE_ID, 
            Article.ARTICLE_CATEGORY_ID, 
            Article.ARTICLE_TITLE, 
            Article.ARTICLE_AUTHOR, 
            Article.ARTICLE_SUMMARY,
            Article.ARTICLE_CONTENT, 
            Article.ARTICLE_COVER, 
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

        Boolean result = articleService.update(body, body.getArticleId(), body.getSystemRequestUserId(), body.getSystemVersion());

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
