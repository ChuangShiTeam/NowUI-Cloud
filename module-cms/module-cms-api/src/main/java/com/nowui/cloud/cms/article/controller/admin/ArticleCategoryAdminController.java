package com.nowui.cloud.cms.article.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.view.CommonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;
import java.util.Map;

/**
 * 文章分类管理端控制器
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Api(value = "文章分类", description = "文章分类管理端接口管理")
@RestController
public class ArticleCategoryAdminController extends BaseController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @ApiOperation(value = "文章分类列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ArticleCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_NAME, value = "分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_CODE, value = "分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/article/category/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore ArticleCategoryView articleCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.APP_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_NAME,
                ArticleCategoryView.ARTICLE_CATEGORY_CODE,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = articleCategoryService.countForAdmin(articleCategoryView.getAppId(), articleCategoryView.getArticleCategoryName(), articleCategoryView.getArticleCategoryCode());
        List<ArticleCategoryView> resultList = articleCategoryService.listForAdmin(articleCategoryView.getAppId(), articleCategoryView.getArticleCategoryName(), articleCategoryView.getArticleCategoryCode(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                ArticleCategoryView.ARTICLE_CATEGORY_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_NAME,
                ArticleCategoryView.ARTICLE_CATEGORY_CODE,
                ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_ID, value = "文章分类编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/article/category/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore ArticleCategoryView articleCategoryView) {

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.ARTICLE_CATEGORY_ID,
                ArticleCategoryView.APP_ID
        );

        ArticleCategoryView result = articleCategoryService.find(articleCategoryView.getArticleCategoryId(), articleCategoryView.getAppId());

        validateResponse(
                ArticleCategoryView.ARTICLE_CATEGORY_ID,
            	ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID,
            	ArticleCategoryView.ARTICLE_CATEGORY_PARENT_PATH,
            	ArticleCategoryView.ARTICLE_CATEGORY_NAME,
            	ArticleCategoryView.ARTICLE_CATEGORY_CODE,
            	ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID,
            	ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION,
            	ArticleCategoryView.ARTICLE_CATEGORY_SORT,
                ArticleCategoryView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "文章分类新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ArticleCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID, value = "父级编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_PARENT_PATH, value = "父级路径", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_NAME, value = "分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_CODE, value = "分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID, value = "多媒体文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_SORT, value = "排序", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/article/category/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore ArticleCategory articleCategory, @ApiIgnore ArticleCategoryView articleCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.APP_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_PARENT_PATH,
                ArticleCategoryView.ARTICLE_CATEGORY_NAME,
                ArticleCategoryView.ARTICLE_CATEGORY_CODE,
                ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION,
                ArticleCategoryView.ARTICLE_CATEGORY_SORT
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String articleCategoryId = Util.getRandomUUID();

        ArticleCategory result = articleCategoryService.save(articleCategory, articleCategoryId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            articleCategoryView.copy(result);

            articleCategoryService.save(articleCategoryView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章分类修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ArticleCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID, value = "父级编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_PARENT_PATH, value = "父级路径", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_NAME, value = "分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_CODE, value = "分类编码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID, value = "多媒体文件编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ArticleCategoryView.ARTICLE_CATEGORY_SORT, value = "排序", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/article/category/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore ArticleCategory articleCategory, @ApiIgnore ArticleCategoryView articleCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.ARTICLE_CATEGORY_ID,
                ArticleCategoryView.APP_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_PARENT_PATH,
                ArticleCategoryView.ARTICLE_CATEGORY_NAME,
                ArticleCategoryView.ARTICLE_CATEGORY_CODE,
                ArticleCategoryView.ARTICLE_CATEGORY_IMAGE_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION,
                ArticleCategoryView.ARTICLE_CATEGORY_SORT,
                ArticleCategoryView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        ArticleCategory result = articleCategoryService.update(articleCategory, articleCategory.getArticleCategoryId(), articleCategory.getAppId(), commonView.getSystemRequestUserId(), articleCategory.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            articleCategoryView.copy(result);

            articleCategoryService.update(articleCategoryView, articleCategoryView.getArticleCategoryId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章分类删除")
    @RequestMapping(value = "/article/category/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore ArticleCategoryView articleCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.ARTICLE_CATEGORY_ID,
                ArticleCategoryView.APP_ID,
                ArticleCategoryView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        ArticleCategory result = articleCategoryService.delete(articleCategoryView.getArticleCategoryId(), articleCategoryView.getAppId(), commonView.getSystemRequestUserId(), articleCategoryView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            articleCategoryView.copy(result);

            articleCategoryService.update(articleCategoryView, articleCategoryView.getArticleCategoryId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章分类数据同步")
    @RequestMapping(value = "/article/category/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleCategory> articleCategoryList = articleCategoryService.listByMysql();

        for (ArticleCategory articleCategory : articleCategoryList) {
            ArticleCategoryView articleCategoryView = new ArticleCategoryView();
            articleCategoryView.copy(articleCategory);

            articleCategoryService.saveOrUpdate(articleCategoryView, articleCategoryView.getArticleCategoryId());
        }

        return renderJson(true);
    }

}