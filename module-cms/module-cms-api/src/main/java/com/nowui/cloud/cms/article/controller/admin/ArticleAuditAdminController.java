package com.nowui.cloud.cms.article.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.service.ArticleAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文章审核管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章审核", description = "文章审核管理端接口管理")
@RestController
public class ArticleAuditAdminController extends BaseController {

    @Autowired
    private ArticleAuditService articleAuditService;

    @ApiOperation(value = "文章审核列表")
    @RequestMapping(value = "/article/audit/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ArticleAudit body) {
        validateRequest(
                body,
                ArticleAudit.APP_ID,
                ArticleAudit.ARTICLE_ID,
                ArticleAudit.USER_ID,
                ArticleAudit.ARTICLE_AUDIT_STATUS,
                ArticleAudit.ARTICLE_AUDIT_TIME,
                ArticleAudit.PAGE_INDEX,
                ArticleAudit.PAGE_SIZE
        );

        Integer resultTotal = articleAuditService.adminCount(body.getAppId() , body.getArticleId(), body.getUserId(), body.getArticleAuditStatus());
        List<ArticleAudit> resultList = articleAuditService.adminList(body.getAppId(), body.getArticleId(), body.getUserId(), body.getArticleAuditStatus(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ArticleAudit.ARTICLE_AUDIT_ID,
                ArticleAudit.ARTICLE_ID,
                ArticleAudit.USER_ID,
                ArticleAudit.ARTICLE_AUDIT_STATUS,
                ArticleAudit.ARTICLE_AUDIT_TIME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文章审核信息")
    @RequestMapping(value = "/article/audit/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ArticleAudit body) {
        validateRequest(
                body,
                ArticleAudit.APP_ID,
                ArticleAudit.ARTICLE_AUDIT_ID
        );

        ArticleAudit result = articleAuditService.find(body.getArticleAuditId());

        validateResponse(
                ArticleAudit.ARTICLE_AUDIT_ID,
                ArticleAudit.ARTICLE_ID,
                ArticleAudit.USER_ID,
                ArticleAudit.ARTICLE_AUDIT_STATUS,
                ArticleAudit.ARTICLE_AUDIT_TIME
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增文章审核")
    @RequestMapping(value = "/article/audit/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ArticleAudit body) {
        validateRequest(
                body,
                ArticleAudit.APP_ID,
                ArticleAudit.ARTICLE_ID,
                ArticleAudit.USER_ID,
                ArticleAudit.ARTICLE_AUDIT_STATUS,
                ArticleAudit.ARTICLE_AUDIT_TIME
        );

        Boolean result = articleAuditService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文章审核")
    @RequestMapping(value = "/article/audit/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ArticleAudit body) {
        validateRequest(
                body,
                ArticleAudit.ARTICLE_AUDIT_ID,
                ArticleAudit.APP_ID,
                ArticleAudit.ARTICLE_ID,
                ArticleAudit.USER_ID,
                ArticleAudit.ARTICLE_AUDIT_STATUS,
                ArticleAudit.ARTICLE_AUDIT_TIME,
                ArticleAudit.SYSTEM_VERSION
        );

        Boolean result = articleAuditService.update(body, body.getArticleAuditId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除文章审核")
    @RequestMapping(value = "/article/audit/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ArticleAudit body) {
        validateRequest(
                body,
                ArticleAudit.ARTICLE_AUDIT_ID,
                ArticleAudit.APP_ID,
                ArticleAudit.SYSTEM_VERSION
        );

        Boolean result = articleAuditService.delete(body.getArticleAuditId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}