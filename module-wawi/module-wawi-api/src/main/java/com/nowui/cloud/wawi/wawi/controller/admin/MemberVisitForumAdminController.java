package com.nowui.cloud.wawi.wawi.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.wawi.entity.MemberVisitForum;
import com.nowui.cloud.wawi.wawi.router.MemberVisitForumRouter;
import com.nowui.cloud.wawi.wawi.service.MemberVisitForumService;
import com.nowui.cloud.wawi.wawi.view.MemberVisitForumView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员访问圈子管理端控制器
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Api(value = "会员访问圈子", description = "会员访问圈子管理端接口管理")
@RestController
public class MemberVisitForumAdminController extends BaseController {

    @Autowired
    private MemberVisitForumService memberVisitForumService;

    @ApiOperation(value = "会员访问圈子列表")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID,
                MemberVisitForum.PAGE_INDEX,
                MemberVisitForum.PAGE_SIZE
        );

        Integer resultTotal = memberVisitForumService.countForAdmin(body.getAppId() , body.getMemberId(), body.getUserId());
        List<MemberVisitForum> resultList = memberVisitForumService.listForAdmin(body.getAppId(), body.getMemberId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员访问圈子信息")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID
        );

        MemberVisitForumView result = memberVisitForumService.find(body.getMemberVisitForumId());

        validateResponse(
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID
        );

        Boolean result = memberVisitForumService.save(body, Util.getRandomUUID(), body.getAppId(), MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.APP_ID,
                MemberVisitForum.MEMBER_ID,
                MemberVisitForum.USER_ID,
                MemberVisitForum.SYSTEM_VERSION
        );

        Boolean result = memberVisitForumService.update(body, body.getMemberVisitForumId(), body.getAppId(), MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员访问圈子")
    @RequestMapping(value = "/wawi/member/visit/forum/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberVisitForum body) {
        validateRequest(
                body,
                MemberVisitForum.MEMBER_VISIT_FORUM_ID,
                MemberVisitForum.APP_ID,
                MemberVisitForum.SYSTEM_VERSION
        );

        Boolean result = memberVisitForumService.delete(body.getMemberVisitForumId(), body.getAppId(), MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}