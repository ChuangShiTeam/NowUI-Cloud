package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleUserBookmark;
import com.nowui.cloud.cms.article.service.ArticleUserBookmarkService;
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
    @RequestMapping(value = "/article/user/bookmark/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleUserBookmark body) {
        validateRequest(
                body,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID,
                ArticleUserBookmark.PAGE_INDEX,
                ArticleUserBookmark.PAGE_SIZE
        );

        Integer resultTotal = articleUserBookmarkService.adminCount(body.getAppId() , body.getArticleId(), body.getUseId());
        List<ArticleUserBookmark> resultList = articleUserBookmarkService.adminList(body.getAppId(), body.getArticleId(), body.getUseId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章用户收藏信息")
    @RequestMapping(value = "/article/user/bookmark/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleUserBookmark body) {
        validateRequest(
                body,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID
        );

        ArticleUserBookmark result = articleUserBookmarkService.find(body.getArticleUserBookMarkId());

        validateResponse(
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleUserBookmark body) {
        validateRequest(
                body,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID
        );

        Boolean result = articleUserBookmarkService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleUserBookmark body) {
        validateRequest(
                body,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.ARTICLE_ID,
                ArticleUserBookmark.USE_ID,
                ArticleUserBookmark.SYSTEM_VERSION
        );

        Boolean result = articleUserBookmarkService.update(body, body.getArticleUserBookMarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章用户收藏")
    @RequestMapping(value = "/article/user/bookmark/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleUserBookmark body) {
        validateRequest(
                body,
                ArticleUserBookmark.ARTICLE_USER_BOOK_MARK_ID,
                ArticleUserBookmark.APP_ID,
                ArticleUserBookmark.SYSTEM_VERSION
        );

        Boolean result = articleUserBookmarkService.delete(body.getArticleUserBookMarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}