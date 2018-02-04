package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserDislike;
import com.nowui.cloud.cms.article.router.ArticleUserDislikeRouter;
import com.nowui.cloud.cms.article.service.ArticleUserDislikeService;
import com.nowui.cloud.cms.article.view.ArticleUserDislikeView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章用户鄙视管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户鄙视", description = "文章用户鄙视管理端接口管理")
@RestController
public class ArticleUserDislikeAdminController extends BaseController {

    @Autowired
    private ArticleUserDislikeService articleUserDislikeService;

    @ApiOperation(value = "文章用户鄙视列表")
    @RequestMapping(value = "/article/user/dislike/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ArticleUserDislike body) {
        validateRequest(
                body,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID,
                ArticleUserDislike.PAGE_INDEX,
                ArticleUserDislike.PAGE_SIZE
        );

        Integer resultTotal = articleUserDislikeService.countForAdmin(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleUserDislike> resultList = articleUserDislikeService.listForAdmin(body.getAppId(), body.getArticleId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户鄙视信息")
    @RequestMapping(value = "/article/user/dislike/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ArticleUserDislike body) {
        validateRequest(
                body,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID
        );

        ArticleUserDislikeView result = articleUserDislikeService.find(body.getArticleUserDislikeId());

        validateResponse(
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ArticleUserDislike body) {
        validateRequest(
                body,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        Boolean result = articleUserDislikeService.save(body, Util.getRandomUUID(), body.getAppId(), ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ArticleUserDislike body) {
        validateRequest(
                body,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID,
                ArticleUserDislike.SYSTEM_VERSION
        );

        Boolean result = articleUserDislikeService.update(body, body.getArticleUserDislikeId(), body.getAppId(), ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ArticleUserDislike body) {
        validateRequest(
                body,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.SYSTEM_VERSION
        );

        Boolean result = articleUserDislikeService.delete(body.getArticleUserDislikeId(), body.getAppId(), ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}