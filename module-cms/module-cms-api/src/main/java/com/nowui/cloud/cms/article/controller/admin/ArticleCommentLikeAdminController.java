package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleCommentLike;
import com.nowui.cloud.cms.article.service.ArticleCommentLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章评论点赞管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章评论点赞", description = "文章评论点赞管理端接口管理")
@RestController
public class ArticleCommentLikeAdminController extends BaseController {

    @Autowired
    private ArticleCommentLikeService articleCommentLikeService;

    @ApiOperation(value = "文章评论点赞列表")
    @RequestMapping(value = "/article/comment/like/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleCommentLike body) {
        validateRequest(
                body,
                ArticleCommentLike.APP_ID,
                ArticleCommentLike.ARTICLE_COMMENT_ID,
                ArticleCommentLike.USER_ID,
                ArticleCommentLike.PAGE_INDEX,
                ArticleCommentLike.PAGE_SIZE
        );

        Integer resultTotal = articleCommentLikeService.adminCount(body.getAppId() , body.getArticleCommentId(), body.getUserId());
        List<ArticleCommentLike> resultList = articleCommentLikeService.adminList(body.getAppId(), body.getArticleCommentId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleCommentLike.ARTICLE_COMMENT_LIKE_ID,
                ArticleCommentLike.ARTICLE_COMMENT_ID,
                ArticleCommentLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章评论点赞信息")
    @RequestMapping(value = "/article/comment/like/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleCommentLike body) {
        validateRequest(
                body,
                ArticleCommentLike.APP_ID,
                ArticleCommentLike.ARTICLE_COMMENT_LIKE_ID
        );

        ArticleCommentLike result = articleCommentLikeService.find(body.getArticleCommentLikeId());

        validateResponse(
                ArticleCommentLike.ARTICLE_COMMENT_LIKE_ID,
                ArticleCommentLike.ARTICLE_COMMENT_ID,
                ArticleCommentLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章评论点赞")
    @RequestMapping(value = "/article/comment/like/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleCommentLike body) {
        validateRequest(
                body,
                ArticleCommentLike.APP_ID,
                ArticleCommentLike.ARTICLE_COMMENT_ID,
                ArticleCommentLike.USER_ID
        );

        Boolean result = articleCommentLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章评论点赞")
    @RequestMapping(value = "/article/comment/like/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleCommentLike body) {
        validateRequest(
                body,
                ArticleCommentLike.ARTICLE_COMMENT_LIKE_ID,
                ArticleCommentLike.APP_ID,
                ArticleCommentLike.ARTICLE_COMMENT_ID,
                ArticleCommentLike.USER_ID,
                ArticleCommentLike.SYSTEM_VERSION
        );

        Boolean result = articleCommentLikeService.update(body, body.getArticleCommentLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章评论点赞")
    @RequestMapping(value = "/article/comment/like/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleCommentLike body) {
        validateRequest(
                body,
                ArticleCommentLike.ARTICLE_COMMENT_LIKE_ID,
                ArticleCommentLike.APP_ID,
                ArticleCommentLike.SYSTEM_VERSION
        );

        Boolean result = articleCommentLikeService.delete(body.getArticleCommentLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}