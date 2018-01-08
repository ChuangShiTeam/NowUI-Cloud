package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.ForumAudit;
import com.nowui.cloud.sns.forum.service.ForumAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛审核信息管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛审核信息", description = "论坛审核信息管理端接口管理")
@RestController
public class ForumAuditAdminController extends BaseController {

    @Autowired
    private ForumAuditService forumAuditService;

    @ApiOperation(value = "论坛审核信息列表")
    @RequestMapping(value = "/forum/audit/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody ForumAudit body) {
        validateRequest(
                body,
                ForumAudit.APP_ID,
                ForumAudit.FORUM_AUDIT_STATUS,
                ForumAudit.AUDIT_SUGGEST_CONTENT,
                ForumAudit.FORUM_ID,
                ForumAudit.PAGE_INDEX,
                ForumAudit.PAGE_SIZE
        );

        Integer resultTotal = forumAuditService.adminCount(body.getAppId() , body.getForumAuditStatus(), body.getForumId());
        List<ForumAudit> resultList = forumAuditService.adminList(body.getAppId(), body.getForumAuditStatus(), body.getForumId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ForumAudit.FORUM_AUDIT_ID,
                ForumAudit.FORUM_AUDIT_STATUS,
                ForumAudit.AUDIT_SUGGEST_CONTENT,
                ForumAudit.FORUM_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛审核信息信息")
    @RequestMapping(value = "/forum/audit/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody ForumAudit body) {
        validateRequest(
                body,
                ForumAudit.APP_ID,
                ForumAudit.FORUM_AUDIT_ID
        );

        ForumAudit result = forumAuditService.find(body.getForumAuditId());

        validateResponse(
                ForumAudit.FORUM_AUDIT_ID,
                ForumAudit.FORUM_AUDIT_STATUS,
                ForumAudit.AUDIT_SUGGEST_CONTENT,
                ForumAudit.FORUM_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛审核信息")
    @RequestMapping(value = "/forum/audit/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody ForumAudit body) {
        validateRequest(
                body,
                ForumAudit.APP_ID,
                ForumAudit.FORUM_AUDIT_STATUS,
                ForumAudit.AUDIT_SUGGEST_CONTENT,
                ForumAudit.FORUM_ID
        );

        Boolean result = forumAuditService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改论坛审核信息")
    @RequestMapping(value = "/forum/audit/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody ForumAudit body) {
        validateRequest(
                body,
                ForumAudit.FORUM_AUDIT_ID,
                ForumAudit.APP_ID,
                ForumAudit.FORUM_AUDIT_STATUS,
                ForumAudit.AUDIT_SUGGEST_CONTENT,
                ForumAudit.FORUM_ID,
                ForumAudit.SYSTEM_VERSION
        );

        Boolean result = forumAuditService.update(body, body.getForumAuditId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除论坛审核信息")
    @RequestMapping(value = "/forum/audit/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody ForumAudit body) {
        validateRequest(
                body,
                ForumAudit.FORUM_AUDIT_ID,
                ForumAudit.APP_ID,
                ForumAudit.SYSTEM_VERSION
        );

        Boolean result = forumAuditService.delete(body.getForumAuditId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}