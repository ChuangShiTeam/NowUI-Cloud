package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.member.entity.Member;
import com.nowui.cloud.base.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员", description = "会员管理端接口管理")
@RestController
public class MemberAdminController extends BaseController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员列表")
    @RequestMapping(value = "/member/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.USER_ID,
                Member.PAGE_INDEX,
                Member.PAGE_SIZE
        );

        Integer resultTotal = memberService.adminCount(body.getAppId() , body.getUserId());
        List<Member> resultList = memberService.adminList(body.getAppId(), body.getUserId(), body.getM(), body.getN());

        validateResponse(
                Member.MEMBER_ID,
                Member.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员信息")
    @RequestMapping(value = "/member/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.MEMBER_ID
        );

        Member result = memberService.find(body.getMemberId());

        validateResponse(
                Member.MEMBER_ID,
                Member.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员")
    @RequestMapping(value = "/member/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Member body) {
        validateRequest(
                body,
                Member.APP_ID,
                Member.USER_ID
        );

        Boolean result = memberService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员")
    @RequestMapping(value = "/member/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Member body) {
        validateRequest(
                body,
                Member.MEMBER_ID,
                Member.APP_ID,
                Member.USER_ID,
                Member.SYSTEM_VERSION
        );

        Boolean result = memberService.update(body, body.getMemberId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员")
    @RequestMapping(value = "/member/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Member body) {
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