package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.member.entity.MemberDialogueRecord;
import com.nowui.cloud.base.member.service.MemberDialogueRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员对话记录管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员对话记录", description = "会员对话记录管理端接口管理")
@RestController
public class MemberDialogueRecordAdminController extends BaseController {

    @Autowired
    private MemberDialogueRecordService memberDialogueRecordService;

    @ApiOperation(value = "会员对话记录列表")
    @RequestMapping(value = "/member/dialogue/record/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberDialogueRecord body) {
        validateRequest(
                body,
                MemberDialogueRecord.APP_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_ID,
                MemberDialogueRecord.MEMBER_ID,
                MemberDialogueRecord.USER_ID,
                MemberDialogueRecord.PAGE_INDEX,
                MemberDialogueRecord.PAGE_SIZE
        );

        Integer resultTotal = memberDialogueRecordService.countForAdmin(body.getAppId() , body.getMemberDialogueId(), body.getMemberId(), body.getUserId());
        List<MemberDialogueRecord> resultList = memberDialogueRecordService.listForAdmin(body.getAppId(), body.getMemberDialogueId(), body.getMemberId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberDialogueRecord.MEMBER_DIALOGUE_RECORD_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_ID,
                MemberDialogueRecord.MEMBER_ID,
                MemberDialogueRecord.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员对话记录信息")
    @RequestMapping(value = "/member/dialogue/record/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberDialogueRecord body) {
        validateRequest(
                body,
                MemberDialogueRecord.APP_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_RECORD_ID
        );

        MemberDialogueRecord result = memberDialogueRecordService.find(body.getMemberDialogueRecordId());

        validateResponse(
                MemberDialogueRecord.MEMBER_DIALOGUE_RECORD_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_ID,
                MemberDialogueRecord.MEMBER_ID,
                MemberDialogueRecord.USER_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_CONTENT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员对话记录")
    @RequestMapping(value = "/member/dialogue/record/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberDialogueRecord body) {
        validateRequest(
                body,
                MemberDialogueRecord.APP_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_ID,
                MemberDialogueRecord.MEMBER_ID,
                MemberDialogueRecord.USER_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_CONTENT
        );

        Boolean result = memberDialogueRecordService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员对话记录")
    @RequestMapping(value = "/member/dialogue/record/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberDialogueRecord body) {
        validateRequest(
                body,
                MemberDialogueRecord.MEMBER_DIALOGUE_RECORD_ID,
                MemberDialogueRecord.APP_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_ID,
                MemberDialogueRecord.MEMBER_ID,
                MemberDialogueRecord.USER_ID,
                MemberDialogueRecord.MEMBER_DIALOGUE_CONTENT,
                MemberDialogueRecord.SYSTEM_VERSION
        );

        Boolean result = memberDialogueRecordService.update(body, body.getMemberDialogueRecordId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员对话记录")
    @RequestMapping(value = "/member/dialogue/record/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberDialogueRecord body) {
        validateRequest(
                body,
                MemberDialogueRecord.MEMBER_DIALOGUE_RECORD_ID,
                MemberDialogueRecord.APP_ID,
                MemberDialogueRecord.SYSTEM_VERSION
        );

        Boolean result = memberDialogueRecordService.delete(body.getMemberDialogueRecordId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}