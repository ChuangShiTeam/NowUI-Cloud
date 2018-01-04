package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleComment;
import com.nowui.cloud.cms.article.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章评论管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章评论", description = "文章评论管理端接口管理")
@RestController
public class ArticleCommentAdminController extends BaseController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @ApiOperation(value = "文章评论列表")
    @RequestMapping(value = "/article/comment/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleComment body) {
        validateRequest(
                body,
                ArticleComment.APP_ID,
                ArticleComment.ARTICLE_ID,
                ArticleComment.USER_ID,
                ArticleComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleComment.ARTICLE_REPLY_USER_ID,
                ArticleComment.ARTICLE_COMMENT_CONTENT,
                ArticleComment.PAGE_INDEX,
                ArticleComment.PAGE_SIZE
        );

        Integer resultTotal = articleCommentService.adminCount(body.getAppId() , body.getArticleId(), body.getUserId(), body.getArticleReolyCommentId(), body.getArticleReplyUserId(), body.getArticleCommentContent());
        List<ArticleComment> resultList = articleCommentService.adminList(body.getAppId(), body.getArticleId(), body.getUserId(), body.getArticleReolyCommentId(), body.getArticleReplyUserId(), body.getArticleCommentContent(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleComment.ARTICLE_COMMENT_ID,
                ArticleComment.ARTICLE_ID,
                ArticleComment.USER_ID,
                ArticleComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleComment.ARTICLE_REPLY_USER_ID,
                ArticleComment.ARTICLE_COMMENT_CONTENT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章评论信息")
    @RequestMapping(value = "/article/comment/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleComment body) {
        validateRequest(
                body,
                ArticleComment.APP_ID,
                ArticleComment.ARTICLE_COMMENT_ID
        );

        ArticleComment result = articleCommentService.find(body.getArticleCommentId());

        validateResponse(
                ArticleComment.ARTICLE_COMMENT_ID,
                ArticleComment.ARTICLE_ID,
                ArticleComment.USER_ID,
                ArticleComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleComment.ARTICLE_REPLY_USER_ID,
                ArticleComment.ARTICLE_COMMENT_CONTENT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章评论")
    @RequestMapping(value = "/article/comment/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleComment body) {
        validateRequest(
                body,
                ArticleComment.APP_ID,
                ArticleComment.ARTICLE_ID,
                ArticleComment.USER_ID,
                ArticleComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleComment.ARTICLE_REPLY_USER_ID,
                ArticleComment.ARTICLE_COMMENT_CONTENT
        );

        Boolean result = articleCommentService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章评论")
    @RequestMapping(value = "/article/comment/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleComment body) {
        validateRequest(
                body,
                ArticleComment.ARTICLE_COMMENT_ID,
                ArticleComment.APP_ID,
                ArticleComment.ARTICLE_ID,
                ArticleComment.USER_ID,
                ArticleComment.ARTICLE_REOLY_COMMENT_ID,
                ArticleComment.ARTICLE_REPLY_USER_ID,
                ArticleComment.ARTICLE_COMMENT_CONTENT,
                ArticleComment.SYSTEM_VERSION
        );

        Boolean result = articleCommentService.update(body, body.getArticleCommentId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章评论")
    @RequestMapping(value = "/article/comment/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleComment body) {
        validateRequest(
                body,
                ArticleComment.ARTICLE_COMMENT_ID,
                ArticleComment.APP_ID,
                ArticleComment.SYSTEM_VERSION
        );

        Boolean result = articleCommentService.delete(body.getArticleCommentId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}