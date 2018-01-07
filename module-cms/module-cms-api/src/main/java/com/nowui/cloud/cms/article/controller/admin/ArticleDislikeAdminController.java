package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleDislike;
import com.nowui.cloud.cms.article.service.ArticleDislikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章鄙视管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章鄙视", description = "文章鄙视管理端接口管理")
@RestController
public class ArticleDislikeAdminController extends BaseController {

    @Autowired
    private ArticleDislikeService articleDislikeService;

    @ApiOperation(value = "文章鄙视列表")
    @RequestMapping(value = "/article/dislike/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleDislike body) {
        validateRequest(
                body,
                ArticleDislike.APP_ID,
                ArticleDislike.ARTICLE_ID,
                ArticleDislike.USER_ID,
                ArticleDislike.PAGE_INDEX,
                ArticleDislike.PAGE_SIZE
        );

        Integer resultTotal = articleDislikeService.adminCount(body.getAppId() , body.getArticleId(), body.getUserId());
        List<ArticleDislike> resultList = articleDislikeService.adminList(body.getAppId(), body.getArticleId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleDislike.ARTICLE_DISLIKE_ID,
                ArticleDislike.ARTICLE_ID,
                ArticleDislike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章鄙视信息")
    @RequestMapping(value = "/article/dislike/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleDislike body) {
        validateRequest(
                body,
                ArticleDislike.APP_ID,
                ArticleDislike.ARTICLE_DISLIKE_ID
        );

        ArticleDislike result = articleDislikeService.find(body.getArticleDislikeId());

        validateResponse(
                ArticleDislike.ARTICLE_DISLIKE_ID,
                ArticleDislike.ARTICLE_ID,
                ArticleDislike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章鄙视")
    @RequestMapping(value = "/article/dislike/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleDislike body) {
        validateRequest(
                body,
                ArticleDislike.APP_ID,
                ArticleDislike.ARTICLE_ID,
                ArticleDislike.USER_ID
        );

        Boolean result = articleDislikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章鄙视")
    @RequestMapping(value = "/article/dislike/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleDislike body) {
        validateRequest(
                body,
                ArticleDislike.ARTICLE_DISLIKE_ID,
                ArticleDislike.APP_ID,
                ArticleDislike.ARTICLE_ID,
                ArticleDislike.USER_ID,
                ArticleDislike.SYSTEM_VERSION
        );

        Boolean result = articleDislikeService.update(body, body.getArticleDislikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章鄙视")
    @RequestMapping(value = "/article/dislike/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleDislike body) {
        validateRequest(
                body,
                ArticleDislike.ARTICLE_DISLIKE_ID,
                ArticleDislike.APP_ID,
                ArticleDislike.SYSTEM_VERSION
        );

        Boolean result = articleDislikeService.delete(body.getArticleDislikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}