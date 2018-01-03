package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章多媒体管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章多媒体", description = "文章多媒体管理端接口管理")
@RestController
public class ArticleMediaAdminController extends BaseController {

    @Autowired
    private ArticleMediaService articleMediaService;

    @ApiOperation(value = "文章多媒体列表")
    @RequestMapping(value = "/article/media/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleMedia body) {
        validateRequest(
                body,
                ArticleMedia.APP_ID,
                ArticleMedia.ARTICLE_ID,
                ArticleMedia.FILE_ID,
                ArticleMedia.PAGE_INDEX,
                ArticleMedia.PAGE_SIZE
        );

        Integer resultTotal = articleMediaService.adminCount(body.getAppId() , body.getArticleId(), body.getFileId());
        List<ArticleMedia> resultList = articleMediaService.adminList(body.getAppId(), body.getArticleId(), body.getFileId(), body.getM(), body.getN());

        validateResponse(
                ArticleMedia.ARTICLE_MEDIA_ID,
                ArticleMedia.ARTICLE_ID,
                ArticleMedia.FILE_ID,
                ArticleMedia.ARTICLE_MEDIA_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章多媒体信息")
    @RequestMapping(value = "/article/media/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleMedia body) {
        validateRequest(
                body,
                ArticleMedia.APP_ID,
                ArticleMedia.ARTICLE_MEDIA_ID
        );

        ArticleMedia result = articleMediaService.find(body.getArticleMediaId());

        validateResponse(
                ArticleMedia.ARTICLE_MEDIA_ID,
                ArticleMedia.ARTICLE_ID,
                ArticleMedia.FILE_ID,
                ArticleMedia.ARTICLE_MEDIA_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章多媒体")
    @RequestMapping(value = "/article/media/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleMedia body) {
        validateRequest(
                body,
                ArticleMedia.APP_ID,
                ArticleMedia.ARTICLE_ID,
                ArticleMedia.FILE_ID,
                ArticleMedia.ARTICLE_MEDIA_SORT
        );

        Boolean result = articleMediaService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章多媒体")
    @RequestMapping(value = "/article/media/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleMedia body) {
        validateRequest(
                body,
                ArticleMedia.ARTICLE_MEDIA_ID,
                ArticleMedia.APP_ID,
                ArticleMedia.ARTICLE_ID,
                ArticleMedia.FILE_ID,
                ArticleMedia.ARTICLE_MEDIA_SORT,
                ArticleMedia.SYSTEM_VERSION
        );

        Boolean result = articleMediaService.update(body, body.getArticleMediaId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章多媒体")
    @RequestMapping(value = "/article/media/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleMedia body) {
        validateRequest(
                body,
                ArticleMedia.ARTICLE_MEDIA_ID,
                ArticleMedia.APP_ID,
                ArticleMedia.SYSTEM_VERSION
        );

        Boolean result = articleMediaService.delete(body.getArticleMediaId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}