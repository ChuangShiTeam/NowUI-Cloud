package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserCommentUserLike;
import com.nowui.cloud.cms.article.service.ArticleUserCommentUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章用户评论用户点赞管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户评论用户点赞", description = "文章用户评论用户点赞管理端接口管理")
@RestController
public class ArticleUserCommentUserLikeAdminController extends BaseController {

    @Autowired
    private ArticleUserCommentUserLikeService articleUserCommentUserLikeService;

    @ApiOperation(value = "文章用户评论用户点赞列表")
    @RequestMapping(value = "/article/user/comment/user/like/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleUserCommentUserLike body) {
        validateRequest(
                body,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID,
                ArticleUserCommentUserLike.PAGE_INDEX,
                ArticleUserCommentUserLike.PAGE_SIZE
        );

        Integer resultTotal = articleUserCommentUserLikeService.adminCount(body.getAppId() , body.getArticleUserCommentId(), body.getUserId());
        List<ArticleUserCommentUserLike> resultList = articleUserCommentUserLikeService.adminList(body.getAppId(), body.getArticleUserCommentId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户评论用户点赞信息")
    @RequestMapping(value = "/article/user/comment/user/like/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleUserCommentUserLike body) {
        validateRequest(
                body,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID
        );

        ArticleUserCommentUserLike result = articleUserCommentUserLikeService.find(body.getArticleUserCommentUserLikeId());

        validateResponse(
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleUserCommentUserLike body) {
        validateRequest(
                body,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        Boolean result = articleUserCommentUserLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleUserCommentUserLike body) {
        validateRequest(
                body,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID,
                ArticleUserCommentUserLike.SYSTEM_VERSION
        );

        Boolean result = articleUserCommentUserLikeService.update(body, body.getArticleUserCommentUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleUserCommentUserLike body) {
        validateRequest(
                body,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.SYSTEM_VERSION
        );

        Boolean result = articleUserCommentUserLikeService.delete(body.getArticleUserCommentUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}