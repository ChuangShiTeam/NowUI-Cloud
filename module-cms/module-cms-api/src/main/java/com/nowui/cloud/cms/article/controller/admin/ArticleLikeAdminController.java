package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleLike;
import com.nowui.cloud.cms.article.service.ArticleLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章点赞管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章点赞", description = "文章点赞管理端接口管理")
@RestController
public class ArticleLikeAdminController extends BaseController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @ApiOperation(value = "文章点赞列表")
    @RequestMapping(value = "/article/like/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleLike body) {
        validateRequest(
                body,
                ArticleLike.APP_ID,
                ArticleLike.ARTICLE_ID,
                ArticleLike.USER_ID,
                ArticleLike.PAGE_INDEX,
                ArticleLike.PAGE_SIZE
        );

        Integer resultTotal = articleLikeService.adminCount(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleLike> resultList = articleLikeService.adminList(body.getAppId(), body.getArticleId(), body.getUserId(), body.getM(), body.getN());

        validateResponse(
                ArticleLike.ARTICLE_LIKE_ID,
                ArticleLike.ARTICLE_ID,
                ArticleLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章点赞信息")
    @RequestMapping(value = "/article/like/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleLike body) {
        validateRequest(
                body,
                ArticleLike.APP_ID,
                ArticleLike.ARTICLE_LIKE_ID
        );

        ArticleLike result = articleLikeService.find(body.getArticleLikeId());

        validateResponse(
                ArticleLike.ARTICLE_LIKE_ID,
                ArticleLike.ARTICLE_ID,
                ArticleLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章点赞")
    @RequestMapping(value = "/article/like/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleLike body) {
        validateRequest(
                body,
                ArticleLike.APP_ID,
                ArticleLike.ARTICLE_ID,
                ArticleLike.USER_ID
        );

        Boolean result = articleLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章点赞")
    @RequestMapping(value = "/article/like/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleLike body) {
        validateRequest(
                body,
                ArticleLike.ARTICLE_LIKE_ID,
                ArticleLike.APP_ID,
                ArticleLike.ARTICLE_ID,
                ArticleLike.USER_ID,
                ArticleLike.SYSTEM_VERSION
        );

        Boolean result = articleLikeService.update(body, body.getArticleLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章点赞")
    @RequestMapping(value = "/article/like/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleLike body) {
        validateRequest(
                body,
                ArticleLike.ARTICLE_LIKE_ID,
                ArticleLike.APP_ID,
                ArticleLike.SYSTEM_VERSION
        );

        Boolean result = articleLikeService.delete(body.getArticleLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}