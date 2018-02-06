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
    public Map<String, Object> listV1() {
        ArticleUserDislike articleUserDislikeEntity = getEntry(ArticleUserDislike.class);

        validateRequest(
                articleUserDislikeEntity,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID,
                ArticleUserDislike.PAGE_INDEX,
                ArticleUserDislike.PAGE_SIZE
        );

        Integer resultTotal = articleUserDislikeService.countForAdmin(articleUserDislikeEntity.getAppId() , articleUserDislikeEntity.getArticleId(), articleUserDislikeEntity.getUserId());
        List<ArticleUserDislike> resultList = articleUserDislikeService.listForAdmin(articleUserDislikeEntity.getAppId(), articleUserDislikeEntity.getArticleId(), articleUserDislikeEntity.getUserId(), articleUserDislikeEntity.getPageIndex(), articleUserDislikeEntity.getPageSize());

        validateResponse(
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户鄙视信息")
    @RequestMapping(value = "/article/user/dislike/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ArticleUserDislike articleUserDislikeEntity = getEntry(ArticleUserDislike.class);

        validateRequest(
                articleUserDislikeEntity,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID
        );

        ArticleUserDislikeView result = articleUserDislikeService.find(articleUserDislikeEntity.getArticleUserDislikeId());

        validateResponse(
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ArticleUserDislike articleUserDislikeEntity = getEntry(ArticleUserDislike.class);

        validateRequest(
                articleUserDislikeEntity,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID
        );

        String articleUserDislikeId = Util.getRandomUUID();

        ArticleUserDislike result = articleUserDislikeService.save(articleUserDislikeEntity, articleUserDislikeId, articleUserDislikeEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_SAVE, articleUserDislikeEntity.getAppId(), articleUserDislikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ArticleUserDislike articleUserDislikeEntity = getEntry(ArticleUserDislike.class);

        validateRequest(
                articleUserDislikeEntity,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.ARTICLE_ID,
                ArticleUserDislike.USER_ID,
                ArticleUserDislike.SYSTEM_VERSION
        );

        ArticleUserDislike result = articleUserDislikeService.update(articleUserDislikeEntity, articleUserDislikeEntity.getArticleUserDislikeId(), articleUserDislikeEntity.getSystemRequestUserId(), articleUserDislikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_UPDATE, articleUserDislikeEntity.getAppId(), articleUserDislikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除文章用户鄙视")
    @RequestMapping(value = "/article/user/dislike/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ArticleUserDislike articleUserDislikeEntity = getEntry(ArticleUserDislike.class);

        validateRequest(
                articleUserDislikeEntity,
                ArticleUserDislike.ARTICLE_USER_DISLIKE_ID,
                ArticleUserDislike.APP_ID,
                ArticleUserDislike.SYSTEM_VERSION
        );

        ArticleUserDislike result = articleUserDislikeService.delete(articleUserDislikeEntity.getArticleUserDislikeId(), articleUserDislikeEntity.getSystemRequestUserId(), articleUserDislikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserDislikeRouter.ARTICLE_USER_DISLIKE_V1_DELETE, articleUserDislikeEntity.getAppId(), articleUserDislikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章用户鄙视数据同步")
    @RequestMapping(value = "/article/user/dislike/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleUserDislike> articleUserDislikeList = articleUserDislikeService.listByMysql();

        for (ArticleUserDislike articleUserDislike : articleUserDislikeList) {
            ArticleUserDislikeView articleUserDislikeView = new ArticleUserDislikeView();
            articleUserDislikeView.putAll(articleUserDislike);

            articleUserDislikeService.update(articleUserDislikeView);
        }

        return renderJson(true);
    }

}