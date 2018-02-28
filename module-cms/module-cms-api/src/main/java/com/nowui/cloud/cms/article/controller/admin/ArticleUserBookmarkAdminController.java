package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserBookmark;
import com.nowui.cloud.cms.article.service.ArticleUserBookmarkService;
import com.nowui.cloud.cms.article.view.ArticleUserBookmarkView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章用户收藏管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户收藏", description = "文章用户收藏管理端接口管理")
@RestController
public class ArticleUserBookmarkAdminController extends BaseController {

    @Autowired
    private ArticleUserBookmarkService articleUserBookmarkService;

    @ApiOperation(value = "文章用户收藏列表")
    @RequestMapping(value = "/article/user/bookmark/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        ArticleUserBookmark articleUserBookmarkEntity = getEntry(ArticleUserBookmark.class);
        
        validateRequest(
                articleUserBookmarkEntity,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID,
                ArticleUserBookmark.PAGE_INDEX,
                ArticleUserBookmark.PAGE_SIZE
        );

        Integer resultTotal = articleUserBookmarkService.countForAdmin(articleUserBookmarkEntity.getAppId() , articleUserBookmarkEntity.getArticleId(), articleUserBookmarkEntity.getUseId());
        List<ArticleUserBookmark> resultList = articleUserBookmarkService.listForAdmin(articleUserBookmarkEntity.getAppId(), articleUserBookmarkEntity.getArticleId(), articleUserBookmarkEntity.getUseId(), articleUserBookmarkEntity.getPageIndex(), articleUserBookmarkEntity.getPageSize());

        validateResponse(
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户收藏信息")
    @RequestMapping(value = "/article/user/bookmark/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ArticleUserBookmark articleUserBookmarkEntity = getEntry(ArticleUserBookmark.class);

        validateRequest(
                articleUserBookmarkEntity,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID
        );

        ArticleUserBookmarkView result = articleUserBookmarkService.find(articleUserBookmarkEntity.getArticleUserBookMarkId());

        validateResponse(
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ArticleUserBookmark articleUserBookmarkEntity = getEntry(ArticleUserBookmark.class);

        validateRequest(
                articleUserBookmarkEntity,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        String articleUserBookMarkId = Util.getRandomUUID();

        ArticleUserBookmark result = articleUserBookmarkService.save(articleUserBookmarkEntity, articleUserBookMarkId, articleUserBookmarkEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ArticleUserBookmark articleUserBookmarkEntity = getEntry(ArticleUserBookmark.class);

        validateRequest(
                articleUserBookmarkEntity,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID,
                ArticleUserBookmark.SYSTEM_VERSION
        );

        ArticleUserBookmark result = articleUserBookmarkService.update(articleUserBookmarkEntity, articleUserBookmarkEntity.getArticleUserBookMarkId(), articleUserBookmarkEntity.getSystemRequestUserId(), articleUserBookmarkEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ArticleUserBookmark articleUserBookmarkEntity = getEntry(ArticleUserBookmark.class);

        validateRequest(
                articleUserBookmarkEntity,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.SYSTEM_VERSION
        );

        ArticleUserBookmark result = articleUserBookmarkService.delete(articleUserBookmarkEntity.getArticleUserBookMarkId(), articleUserBookmarkEntity.getSystemRequestUserId(), articleUserBookmarkEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "文章用户收藏数据同步")
    @RequestMapping(value = "/article/user/bookmark/admin/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleUserBookmark> articleUserBookmarkList = articleUserBookmarkService.listByMysql();

        for (ArticleUserBookmark articleUserBookmark : articleUserBookmarkList) {
            ArticleUserBookmarkView articleUserBookmarkView = new ArticleUserBookmarkView();
            articleUserBookmarkView.putAll(articleUserBookmark);

            articleUserBookmarkService.update(articleUserBookmarkView);
        }

        return renderJson(true);
    }

}