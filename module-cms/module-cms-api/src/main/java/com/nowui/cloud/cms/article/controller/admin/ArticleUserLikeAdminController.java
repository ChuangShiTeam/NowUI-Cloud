package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserLike;
import com.nowui.cloud.cms.article.service.ArticleUserLikeService;
import com.nowui.cloud.cms.article.view.ArticleUserLikeView;

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
    @RequestMapping(value = "/article/user/like/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        ArticleUserLike articleUserLikeEntity = getEntry(ArticleUserLike.class);

        validateRequest(
                articleUserLikeEntity,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID,
                ArticleUserLike.PAGE_INDEX,
                ArticleUserLike.PAGE_SIZE
        );

        Integer resultTotal = articleUserLikeService.countForAdmin(articleUserLikeEntity.getAppId() , articleUserLikeEntity.getArticleId(), articleUserLikeEntity.getUserId());
        List<ArticleUserLike> resultList = articleUserLikeService.listForAdmin(articleUserLikeEntity.getAppId(), articleUserLikeEntity.getArticleId(), articleUserLikeEntity.getUserId(), articleUserLikeEntity.getPageIndex(), articleUserLikeEntity.getPageSize());

        validateResponse(
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户点赞信息")
    @RequestMapping(value = "/article/user/like/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ArticleUserLike articleUserLikeEntity = getEntry(ArticleUserLike.class);

        validateRequest(
                articleUserLikeEntity,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_USER_LIKE_ID
        );

        ArticleUserLikeView result = articleUserLikeService.find(articleUserLikeEntity.getArticleUserLikeId());

        validateResponse(
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ArticleUserLike articleUserLikeEntity = getEntry(ArticleUserLike.class);

        validateRequest(
                articleUserLikeEntity,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID
        );

        String articleUserLikeId = Util.getRandomUUID();

        ArticleUserLike result = articleUserLikeService.save(articleUserLikeEntity, articleUserLikeId, articleUserLikeEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ArticleUserLike articleUserLikeEntity = getEntry(ArticleUserLike.class);

        validateRequest(
                articleUserLikeEntity,
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.APP_ID,
                ArticleUserLike.ARTICLE_ID,
                ArticleUserLike.USER_ID,
                ArticleUserLike.SYSTEM_VERSION
        );

        ArticleUserLike result = articleUserLikeService.update(articleUserLikeEntity, articleUserLikeEntity.getArticleUserLikeId(), articleUserLikeEntity.getSystemRequestUserId(), articleUserLikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除文章用户点赞")
    @RequestMapping(value = "/article/user/like/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ArticleUserLike articleUserLikeEntity = getEntry(ArticleUserLike.class);

        validateRequest(
                articleUserLikeEntity,
                ArticleUserLike.ARTICLE_USER_LIKE_ID,
                ArticleUserLike.APP_ID,
                ArticleUserLike.SYSTEM_VERSION
        );

        ArticleUserLike result = articleUserLikeService.delete(articleUserLikeEntity.getArticleUserLikeId(), articleUserLikeEntity.getSystemRequestUserId(), articleUserLikeEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除文章用户点赞数据同步")
    @RequestMapping(value = "/article/user/like/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleUserLike> articleUserLikeList = articleUserLikeService.listByMysql();

        for (ArticleUserLike articleUserLike : articleUserLikeList) {
            ArticleUserLikeView articleUserLikeView = new ArticleUserLikeView();
            articleUserLikeView.putAll(articleUserLike);

            articleUserLikeService.update(articleUserLikeView);
        }

        return renderJson(true);
    }

}