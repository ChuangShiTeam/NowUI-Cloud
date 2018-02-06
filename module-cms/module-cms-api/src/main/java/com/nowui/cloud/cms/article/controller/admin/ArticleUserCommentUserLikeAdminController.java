package com.nowui.cloud.cms.article.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserCommentUserLike;
import com.nowui.cloud.cms.article.router.ArticleUserCommentUserLikeRouter;
import com.nowui.cloud.cms.article.service.ArticleUserCommentUserLikeService;
import com.nowui.cloud.cms.article.view.ArticleUserCommentUserLikeView;

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
 * <p>
 * 2018-01-08
 */
@Api(value = "文章用户评论用户点赞", description = "文章用户评论用户点赞管理端接口管理")
@RestController
public class ArticleUserCommentUserLikeAdminController extends BaseController {

    @Autowired
    private ArticleUserCommentUserLikeService articleUserCommentUserLikeService;

    @ApiOperation(value = "文章用户评论用户点赞列表")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        ArticleUserCommentUserLike articleUserCommentUserLikeEntity = getEntry(ArticleUserCommentUserLike.class);

        validateRequest(
                articleUserCommentUserLikeEntity,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID,
                ArticleUserCommentUserLike.PAGE_INDEX,
                ArticleUserCommentUserLike.PAGE_SIZE
        );

        Integer resultTotal = articleUserCommentUserLikeService.countForAdmin(articleUserCommentUserLikeEntity.getAppId(), articleUserCommentUserLikeEntity.getArticleUserCommentId(), articleUserCommentUserLikeEntity.getUserId());
        List<ArticleUserCommentUserLike> resultList = articleUserCommentUserLikeService.listForAdmin(articleUserCommentUserLikeEntity.getAppId(), articleUserCommentUserLikeEntity.getArticleUserCommentId(), articleUserCommentUserLikeEntity.getUserId(), articleUserCommentUserLikeEntity.getPageIndex(), articleUserCommentUserLikeEntity.getPageSize());

        validateResponse(
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户评论用户点赞信息")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ArticleUserCommentUserLike articleUserCommentUserLikeEntity = getEntry(ArticleUserCommentUserLike.class);

        validateRequest(
                articleUserCommentUserLikeEntity,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID
        );

        ArticleUserCommentUserLikeView result = articleUserCommentUserLikeService.find(articleUserCommentUserLikeEntity.getArticleUserCommentUserLikeId());

        validateResponse(
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ArticleUserCommentUserLike articleUserCommentUserLikeEntity = getEntry(ArticleUserCommentUserLike.class);

        validateRequest(
                articleUserCommentUserLikeEntity,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID
        );

        String articleUserCommentUserLikeId = Util.getRandomUUID();

        ArticleUserCommentUserLike result = articleUserCommentUserLikeService.save(articleUserCommentUserLikeEntity, articleUserCommentUserLikeId, articleUserCommentUserLikeEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserCommentUserLikeRouter.ARTICLE_USER_COMMENT_USER_LIKE_V1_SAVE, articleUserCommentUserLikeEntity.getAppId(), articleUserCommentUserLikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ArticleUserCommentUserLike articleUserCommentUserLikeEntity = getEntry(ArticleUserCommentUserLike.class);

        validateRequest(
                articleUserCommentUserLikeEntity,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_ID,
                ArticleUserCommentUserLike.USER_ID,
                ArticleUserCommentUserLike.SYSTEM_VERSION
        );

        ArticleUserCommentUserLike result = articleUserCommentUserLikeService.update(articleUserCommentUserLikeEntity, articleUserCommentUserLikeEntity.getArticleUserCommentUserLikeId(), articleUserCommentUserLikeEntity.getSystemRequestUserId(), articleUserCommentUserLikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserCommentUserLikeRouter.ARTICLE_USER_COMMENT_USER_LIKE_V1_UPDATE, articleUserCommentUserLikeEntity.getAppId(), articleUserCommentUserLikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除文章用户评论用户点赞")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ArticleUserCommentUserLike articleUserCommentUserLikeEntity = getEntry(ArticleUserCommentUserLike.class);

        validateRequest(
                articleUserCommentUserLikeEntity,
                ArticleUserCommentUserLike.ARTICLE_USER_COMMENT_USER_LIKE_ID,
                ArticleUserCommentUserLike.APP_ID,
                ArticleUserCommentUserLike.SYSTEM_VERSION
        );

        ArticleUserCommentUserLike result = articleUserCommentUserLikeService.delete(articleUserCommentUserLikeEntity.getArticleUserCommentUserLikeId(), articleUserCommentUserLikeEntity.getSystemRequestUserId(), articleUserCommentUserLikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ArticleUserCommentUserLikeRouter.ARTICLE_USER_COMMENT_USER_LIKE_V1_DELETE, articleUserCommentUserLikeEntity.getAppId(), articleUserCommentUserLikeEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章用户评论用户点赞数据同步")
    @RequestMapping(value = "/article/user/comment/user/like/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleUserCommentUserLike> articleUserCommentUserLikeList = articleUserCommentUserLikeService.listByMysql();

        for (ArticleUserCommentUserLike articleUserCommentUserLike : articleUserCommentUserLikeList) {
            ArticleUserCommentUserLikeView articleUserCommentUserLikeView = new ArticleUserCommentUserLikeView();
            articleUserCommentUserLikeView.putAll(articleUserCommentUserLike);

            articleUserCommentUserLikeService.update(articleUserCommentUserLikeView);
        }

        return renderJson(true);
    }

}