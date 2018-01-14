package com.nowui.cloud.member.member.controller.mobile;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.MemberDialogue;
import com.nowui.cloud.member.member.service.MemberDialogueRecordService;
import com.nowui.cloud.member.member.service.MemberDialogueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员对话移动端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员对话", description = "会员对话移动端接口管理")
@RestController
public class MemberDialogueMobileController extends BaseController {
    
    @Autowired
    private MemberDialogueService memberDialogueService;
    
    @Autowired
    private MemberDialogueRecordService memberDialogueRecordService;
    
    @Autowired
    private UserRpc userRpc;
    
    @ApiOperation(value = "会员对话查找")
    @RequestMapping(value = "/member/dialogue/mobile/v1/find", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberDialogue body) {
        validateRequest(
                body,
                MemberDialogue.APP_ID,
                MemberDialogue.RESPOND_USER_ID,
                MemberDialogue.PAGE_INDEX,
                MemberDialogue.PAGE_SIZE
        );
        
        User initiateUser = userRpc.findV1(body.getSystemCreateUserId());
        
        User respondUser = userRpc.findV1(body.getRespondUserId());
        
        MemberDialogue memberDialogue = memberDialogueService.findByInitiateUserIdAndRespondUserId(body.getSystemRequestUserId(), body.getRespondUserId());
        
        if (memberDialogue == null) {
            //创建会员对话
            memberDialogue = new MemberDialogue();
            memberDialogue.setAppId(body.getAppId());
            memberDialogue.setInitiateUserId(body.getSystemRequestUserId());
            memberDialogue.setInitiateMemberId(initiateUser.getObjectId());
            memberDialogue.setRespondUserId(body.getRespondUserId());
            memberDialogue.setRespondMemberId(respondUser.getObjectId());
            memberDialogue.put(MemberDialogue.MEMBER_DIALOGUE_RECORD_LIST, new ArrayList<>());
            memberDialogue.put(MemberDialogue.MEMBER_DIALOGUE_RECORD_COUNT, 0);
        } else {
            
        }
        
        validateResponse(
                MemberDialogue.MEMBER_DIALOGUE_ID,
                MemberDialogue.MEMBER_DIALOGUE_RECORD_LIST,
                MemberDialogue.MEMBER_DIALOGUE_RECORD_COUNT
        );

        return renderJson(memberDialogue);
    }

}