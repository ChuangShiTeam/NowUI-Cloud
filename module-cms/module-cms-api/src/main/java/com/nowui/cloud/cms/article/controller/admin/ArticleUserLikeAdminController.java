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
    @RequestMapping(value = "/article/user/like/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleUserLike body) {
        validateRequest(
                body,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID,
                ArticleUserLike.PAGE_INDEX,
                ArticleUserLike.PAGE_SIZE
        );

        Integer resultTotal = articleUserLikeService.adminCount(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleUserLike> resultList = articleUserLikeService.adminList(body.getAppId(), body.getArticleId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户点赞信息")
    @RequestMapping(value = "/article/user/like/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleUserLike body) {
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
    @RequestMapping(value = "/article/user/like/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleUserLike body) {
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
    @RequestMapping(value = "/article/user/like/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleUserLike body) {
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
    @RequestMapping(value = "/article/user/like/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleUserLike body) {
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