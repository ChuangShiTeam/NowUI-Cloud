package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章文章分类管理端控制器
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Api(value = "文章文章分类", description = "文章文章分类管理端接口管理")
@RestController
public class ArticleArticleCategoryAdminController extends BaseController {

    @Autowired
    private ArticleArticleCategoryService articleArticleCategoryService;

    @ApiOperation(value = "文章文章分类列表")
    @RequestMapping(value = "/article/article/category/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleArticleCategory body) {
        validateRequest(
                body,
                ArticleArticleCategory.APP_ID,
                ArticleArticleCategory.ARTICLE_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.PAGE_INDEX,
                ArticleArticleCategory.PAGE_SIZE
        );

        Integer resultTotal = articleArticleCategoryService.adminCount(body.getAppId() , body.getArticleId(), body.getArticleCategoryId());
        List<ArticleArticleCategory> resultList = articleArticleCategoryService.adminList(body.getAppId(), body.getArticleId(), body.getArticleCategoryId(), body.getM(), body.getN());

        validateResponse(
                ArticleArticleCategory.ARTICLE_ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章文章分类信息")
    @RequestMapping(value = "/article/article/category/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleArticleCategory body) {
        validateRequest(
                body,
                ArticleArticleCategory.APP_ID,
                ArticleArticleCategory.ARTICLE_ARTICLE_CATEGORY_ID
        );

        ArticleArticleCategory result = articleArticleCategoryService.find(body.getArticleArticleCategoryId());

        validateResponse(
                ArticleArticleCategory.ARTICLE_ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章文章分类")
    @RequestMapping(value = "/article/article/category/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleArticleCategory body) {
        validateRequest(
                body,
                ArticleArticleCategory.APP_ID,
                ArticleArticleCategory.ARTICLE_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY
        );

        Boolean result = articleArticleCategoryService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章文章分类")
    @RequestMapping(value = "/article/article/category/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleArticleCategory body) {
        validateRequest(
                body,
                ArticleArticleCategory.ARTICLE_ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.APP_ID,
                ArticleArticleCategory.ARTICLE_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.ARTICLE_CATEGORY_IS_PRIMARY,
                ArticleArticleCategory.SYSTEM_VERSION
        );

        Boolean result = articleArticleCategoryService.update(body, body.getArticleArticleCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章文章分类")
    @RequestMapping(value = "/article/article/category/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleArticleCategory body) {
        validateRequest(
                body,
                ArticleArticleCategory.ARTICLE_ARTICLE_CATEGORY_ID,
                ArticleArticleCategory.APP_ID,
                ArticleArticleCategory.SYSTEM_VERSION
        );

        Boolean result = articleArticleCategoryService.delete(body.getArticleArticleCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}