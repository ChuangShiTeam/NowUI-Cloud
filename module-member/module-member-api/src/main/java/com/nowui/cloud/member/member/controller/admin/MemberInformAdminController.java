package com.nowui.cloud.member.member.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.MemberInform;
import com.nowui.cloud.member.member.service.MemberInformService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员举报管理端控制器
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Api(value = "会员举报", description = "会员举报管理端接口管理")
@RestController
public class MemberInformAdminController extends BaseController {

    @Autowired
    private MemberInformService memberInformService;

    @ApiOperation(value = "会员举报列表")
    @RequestMapping(value = "/member/inform/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberInform body) {
        validateRequest(
                body,
                MemberInform.APP_ID,
                MemberInform.MEMBER_ID,
                MemberInform.USER_ID,
                MemberInform.INFORM_USER_ID,
                MemberInform.INFORM_MEMBER_ID,
                MemberInform.PAGE_INDEX,
                MemberInform.PAGE_SIZE
        );

        Integer resultTotal = memberInformService.countForAdmin(body.getAppId() , body.getMemberId(), body.getUserId(), body.getInformUserId(), body.getInformMemberId());
        List<MemberInform> resultList = memberInformService.listForAdmin(body.getAppId(), body.getMemberId(), body.getUserId(), body.getInformUserId(), body.getInformMemberId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberInform.MEMBER_INFORM_ID,
                MemberInform.MEMBER_ID,
                MemberInform.USER_ID,
                MemberInform.INFORM_USER_ID,
                MemberInform.INFORM_MEMBER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员举报信息")
    @RequestMapping(value = "/member/inform/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberInform body) {
        validateRequest(
                body,
                MemberInform.APP_ID,
                MemberInform.MEMBER_INFORM_ID
        );

        MemberInform result = memberInformService.find(body.getMemberInformId());

        validateResponse(
                MemberInform.MEMBER_INFORM_ID,
                MemberInform.MEMBER_ID,
                MemberInform.USER_ID,
                MemberInform.INFORM_USER_ID,
                MemberInform.INFORM_MEMBER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员举报")
    @RequestMapping(value = "/member/inform/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberInform body) {
        validateRequest(
                body,
                MemberInform.APP_ID,
                MemberInform.MEMBER_ID,
                MemberInform.USER_ID,
                MemberInform.INFORM_USER_ID,
                MemberInform.INFORM_MEMBER_ID
        );

        Boolean result = memberInformService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员举报")
    @RequestMapping(value = "/member/inform/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberInform body) {
        validateRequest(
                body,
                MemberInform.MEMBER_INFORM_ID,
                MemberInform.APP_ID,
                MemberInform.MEMBER_ID,
                MemberInform.USER_ID,
                MemberInform.INFORM_USER_ID,
                MemberInform.INFORM_MEMBER_ID,
                MemberInform.SYSTEM_VERSION
        );

        Boolean result = memberInformService.update(body, body.getMemberInformId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员举报")
    @RequestMapping(value = "/member/inform/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberInform body) {
        validateRequest(
                body,
                MemberInform.MEMBER_INFORM_ID,
                MemberInform.APP_ID,
                MemberInform.SYSTEM_VERSION
        );

        Boolean result = memberInformService.delete(body.getMemberInformId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}