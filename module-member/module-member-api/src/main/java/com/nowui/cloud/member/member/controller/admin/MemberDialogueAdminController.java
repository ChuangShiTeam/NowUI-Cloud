package com.nowui.cloud.member.member.controller.admin;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.member.member.router.MemberDialogueRouter;
import com.nowui.cloud.member.member.view.MemberDialogueView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.MemberDialogue;
import com.nowui.cloud.member.member.service.MemberDialogueService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员对话管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员对话", description = "会员对话管理端接口管理")
@RestController
public class MemberDialogueAdminController extends BaseController {

    @Autowired
    private MemberDialogueService memberDialogueService;

    @ApiOperation(value = "会员对话列表")
    @RequestMapping(value = "/member/dialogue/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.APP_ID,
                MemberDialogue.INITIATE_MEMBER_ID,
                MemberDialogue.INITIATE_USER_ID,
                MemberDialogue.RESPOND_MEMBER_ID,
                MemberDialogue.RESPOND_USER_ID,
                MemberDialogue.PAGE_INDEX,
                MemberDialogue.PAGE_SIZE
        );

        Integer resultTotal = memberDialogueService.countForAdmin(body.getAppId() , body.getInitiateMemberId(), body.getInitiateUserId(), body.getRespondMemberId(), body.getRespondUserId());
        List<MemberDialogue> resultList = memberDialogueService.listForAdmin(body.getAppId(), body.getInitiateMemberId(), body.getInitiateUserId(), body.getRespondMemberId(), body.getRespondUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberDialogue.MEMBER_DIALOGUE_ID,
                MemberDialogue.INITIATE_USER_ID,
                MemberDialogue.RESPOND_MEMBER_ID,
                MemberDialogue.RESPOND_USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员对话信息")
    @RequestMapping(value = "/member/dialogue/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.APP_ID,
                MemberDialogue.MEMBER_DIALOGUE_ID
        );

        MemberDialogueView result = memberDialogueService.find(body.getMemberDialogueId());
//        MemberDialogue result = memberDialogueService.find(body.getMemberDialogueId());

        validateResponse(
                MemberDialogue.MEMBER_DIALOGUE_ID,
                MemberDialogue.INITIATE_MEMBER_ID,
                MemberDialogue.INITIATE_USER_ID,
                MemberDialogue.RESPOND_MEMBER_ID,
                MemberDialogue.RESPOND_USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员对话")
    @RequestMapping(value = "/member/dialogue/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.APP_ID,
                MemberDialogue.INITIATE_MEMBER_ID,
                MemberDialogue.INITIATE_USER_ID,
                MemberDialogue.RESPOND_MEMBER_ID,
                MemberDialogue.RESPOND_USER_ID
        );

        MemberDialogue result = memberDialogueService.save(body,Util.getRandomUUID(),body.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改会员对话")
    @RequestMapping(value = "/member/dialogue/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.MEMBER_DIALOGUE_ID,
                MemberDialogue.APP_ID,
                MemberDialogue.INITIATE_MEMBER_ID,
                MemberDialogue.INITIATE_USER_ID,
                MemberDialogue.RESPOND_MEMBER_ID,
                MemberDialogue.RESPOND_USER_ID,
                MemberDialogue.SYSTEM_VERSION
        );

        MemberDialogue result = memberDialogueService.update(body,body.getMemberDialogueId(),body.getSystemUpdateUserId(),body.getSystemVersion());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除会员对话")
    @RequestMapping(value = "/member/dialogue/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.MEMBER_DIALOGUE_ID,
                MemberDialogue.APP_ID,
                MemberDialogue.SYSTEM_VERSION
        );

        MemberDialogue result = memberDialogueService.delete(body.getMemberDialogueId(),body.getSystemUpdateUserId(),body.getSystemVersion());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

}