package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.cms.article.entity.ArticleBookmark;
import com.nowui.cloud.cms.article.service.ArticleBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章收藏管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章收藏", description = "文章收藏管理端接口管理")
@RestController
public class ArticleBookmarkAdminController extends BaseController {

    @Autowired
    private ArticleBookmarkService articleBookmarkService;

    @ApiOperation(value = "文章收藏列表")
    @RequestMapping(value = "/article/bookmark/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleBookmark body) {
        validateRequest(
                body,
                ArticleBookmark.APP_ID,
                ArticleBookmark.ARTICLE_ID,
                ArticleBookmark.USE_ID,
                ArticleBookmark.PAGE_INDEX,
                ArticleBookmark.PAGE_SIZE
        );

        Integer resultTotal = articleBookmarkService.adminCount(body.getAppId() , body.getArticleId(), body.getUseId());
        List<ArticleBookmark> resultList = articleBookmarkService.adminList(body.getAppId(), body.getArticleId(), body.getUseId(), body.getM(), body.getN());

        validateResponse(
                ArticleBookmark.ARTICLE_BOOK_MARK_ID,
                ArticleBookmark.ARTICLE_ID,
                ArticleBookmark.USE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章收藏信息")
    @RequestMapping(value = "/article/bookmark/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleBookmark body) {
        validateRequest(
                body,
                ArticleBookmark.APP_ID,
                ArticleBookmark.ARTICLE_BOOK_MARK_ID
        );

        ArticleBookmark result = articleBookmarkService.find(body.getArticleBookMarkId());

        validateResponse(
                ArticleBookmark.ARTICLE_BOOK_MARK_ID,
                ArticleBookmark.ARTICLE_ID,
                ArticleBookmark.USE_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章收藏")
    @RequestMapping(value = "/article/bookmark/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleBookmark body) {
        validateRequest(
                body,
                ArticleBookmark.APP_ID,
                ArticleBookmark.ARTICLE_ID,
                ArticleBookmark.USE_ID
        );

        Boolean result = articleBookmarkService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章收藏")
    @RequestMapping(value = "/article/bookmark/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleBookmark body) {
        validateRequest(
                body,
                ArticleBookmark.ARTICLE_BOOK_MARK_ID,
                ArticleBookmark.APP_ID,
                ArticleBookmark.ARTICLE_ID,
                ArticleBookmark.USE_ID,
                ArticleBookmark.SYSTEM_VERSION
        );

        Boolean result = articleBookmarkService.update(body, body.getArticleBookMarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章收藏")
    @RequestMapping(value = "/article/bookmark/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleBookmark body) {
        validateRequest(
                body,
                ArticleBookmark.ARTICLE_BOOK_MARK_ID,
                ArticleBookmark.APP_ID,
                ArticleBookmark.SYSTEM_VERSION
        );

        Boolean result = articleBookmarkService.delete(body.getArticleBookMarkId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}