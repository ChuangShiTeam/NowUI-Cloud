package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserComment;
import com.nowui.cloud.cms.article.router.ArticleUserCommentRouter;
import com.nowui.cloud.cms.article.service.ArticleUserCommentService;
import com.nowui.cloud.cms.article.view.ArticleUserCommentView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章用户评论管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户评论", description = "文章用户评论管理端接口管理")
@RestController
public class ArticleUserCommentAdminController extends BaseController {

    @Autowired
    private ArticleUserCommentService articleUserCommentService;

    @ApiOperation(value = "文章用户评论列表")
    @RequestMapping(value = "/article/user/comment/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ArticleUserComment body) {
        validateRequest(
                body,
                ArticleUserComment.APP_ID,
                ArticleUserComment.ARTICLE_ID,
                ArticleUserComment.USER_ID,
                ArticleUserComment.PAGE_INDEX,
                ArticleUserComment.PAGE_SIZE
        );

        Integer resultTotal = articleUserCommentService.countForAdmin(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleUserComment> resultList = articleUserCommentService.listForAdmin(body.getAppId(), body.getArticleId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserComment.ARTICLE_USER_COMMENT_ID,
                ArticleUserComment.ARTICLE_ID,
                ArticleUserComment.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户评论信息")
    @RequestMapping(value = "/article/user/comment/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ArticleUserComment body) {
        validateRequest(
                body,
                ArticleUserComment.APP_ID,
                ArticleUserComment.ARTICLE_USER_COMMENT_ID
        );

        ArticleUserCommentView result = articleUserCommentService.find(body.getArticleUserCommentId());

        validateResponse(
                ArticleUserComment.ARTICLE_USER_COMMENT_ID,
                ArticleUserComment.ARTICLE_ID,
                ArticleUserComment.USER_ID,
                ArticleUserComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleUserComment.ARTICLE_REPLY_USER_ID,
                ArticleUserComment.ARTICLE_COMMENT_CONTENT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户评论")
    @RequestMapping(value = "/article/user/comment/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ArticleUserComment body) {
        validateRequest(
                body,
                ArticleUserComment.APP_ID,
                ArticleUserComment.ARTICLE_ID,
                ArticleUserComment.USER_ID,
                ArticleUserComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleUserComment.ARTICLE_REPLY_USER_ID,
                ArticleUserComment.ARTICLE_COMMENT_CONTENT
        );

        Boolean result = articleUserCommentService.save(body, Util.getRandomUUID(), body.getAppId(), ArticleUserCommentRouter.ARTICLE_USER_COMMENT_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章用户评论")
    @RequestMapping(value = "/article/user/comment/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ArticleUserComment body) {
        validateRequest(
                body,
                ArticleUserComment.ARTICLE_USER_COMMENT_ID,
                ArticleUserComment.APP_ID,
                ArticleUserComment.ARTICLE_ID,
                ArticleUserComment.USER_ID,
                ArticleUserComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleUserComment.ARTICLE_REPLY_USER_ID,
                ArticleUserComment.ARTICLE_COMMENT_CONTENT,
                ArticleUserComment.SYSTEM_VERSION
        );

        Boolean result = articleUserCommentService.update(body, body.getArticleUserCommentId(), body.getAppId(), ArticleUserCommentRouter.ARTICLE_USER_COMMENT_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章用户评论")
    @RequestMapping(value = "/article/user/comment/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ArticleUserComment body) {
        validateRequest(
                body,
                ArticleUserComment.ARTICLE_USER_COMMENT_ID,
                ArticleUserComment.APP_ID,
                ArticleUserComment.SYSTEM_VERSION
        );

        Boolean result = articleUserCommentService.delete(body.getArticleUserCommentId(), body.getAppId(), ArticleUserCommentRouter.ARTICLE_USER_COMMENT_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}