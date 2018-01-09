package com.nowui.cloud.member.member.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.member.entity.Member;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员", description = "会员管理端接口管理")
@RestController
public class MemberAdminController extends BaseController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员列表")
    @RequestMapping(value = "/member/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.MEMBER_IS_TOP,
                Member.MEMBER_IS_RECOMMED,
                Member.PAGE_INDEX,
                Member.PAGE_SIZE
        );

        Integer resultTotal = memberService.countForAdmin(body.getAppId() , body.getMemberIsTop(), body.getMemberIsRecommed());
        List<Member> resultList = memberService.listForAdmin(body.getAppId(), body.getMemberIsTop(), body.getMemberIsRecommed(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Member.MEMBER_ID,
                Member.MEMBER_IS_TOP,
                Member.MEMBER_IS_RECOMMED
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员信息")
    @RequestMapping(value = "/member/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.MEMBER_ID
        );

        Member result = memberService.find(body.getMemberId());

        validateResponse(
                Member.MEMBER_ID,
                Member.USER_ID,
                Member.MEMBER_IS_TOP,
                Member.MEMBER_TOP_LEVEL,
                Member.MEMBER_TOP_END_TIME,
                Member.MEMBER_IS_RECOMMED,
                Member.MEMBER_DESCRIPTION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员")
    @RequestMapping(value = "/member/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.USER_ID,
                Member.MEMBER_IS_TOP,
                Member.MEMBER_TOP_LEVEL,
                Member.MEMBER_TOP_END_TIME,
                Member.MEMBER_IS_RECOMMED,
                Member.MEMBER_DESCRIPTION
        );

        Boolean result = memberService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员")
    @RequestMapping(value = "/member/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Member body) {
        validateRequest(
                body,
                Member.MEMBER_ID,
                Member.APP_ID,
                Member.USER_ID,
                Member.MEMBER_IS_TOP,
                Member.MEMBER_TOP_LEVEL,
                Member.MEMBER_TOP_END_TIME,
                Member.MEMBER_IS_RECOMMED,
                Member.MEMBER_DESCRIPTION,
                Member.SYSTEM_VERSION
        );

        Boolean result = memberService.update(body, body.getMemberId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员")
    @RequestMapping(value = "/member/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Member body) {
        validateRequest(
                body,
                Member.MEMBER_ID,
                Member.APP_ID,
                Member.SYSTEM_VERSION
        );

        Boolean result = memberService.delete(body.getMemberId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}