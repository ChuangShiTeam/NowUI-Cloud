package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserLike;
import com.nowui.cloud.cms.article.service.ArticleUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章用户点赞管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户点赞", description = "文章用户点赞管理端接口管理")
@RestController
public class ArticleUserLikeAdminController extends BaseController {

    @Autowired
    private ArticleUserLikeService articleUserLikeService;

    @ApiOperation(value = "文章用户点赞列表")
    @RequestMapping(value = "/article/user/like/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID,
                ArticleUserLike.PAGE_INDEX,
                ArticleUserLike.PAGE_SIZE
        );

        Integer resultTotal = articleUserLikeService.countForAdmin(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleUserLike> resultList = articleUserLikeService.listForAdmin(body.getAppId(), body.getArticleId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户点赞信息")
    @RequestMapping(value = "/article/user/like/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_USER_LIKE_ID
        );

        ArticleUserLike result = articleUserLikeService.find(body.getArticleUserLikeId());

        validateResponse(
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        Boolean result = articleUserLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID,
                ArticleUserLike.SYSTEM_VERSION
        );

        Boolean result = articleUserLikeService.update(body, body.getArticleUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.APP_ID,
                ArticleUserLike.SYSTEM_VERSION
        );

        Boolean result = articleUserLikeService.delete(body.getArticleUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}