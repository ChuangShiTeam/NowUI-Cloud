package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.member.entity.MemberInform;
import com.nowui.cloud.base.member.service.MemberInformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/member/inform/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody MemberInform body) {
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

        Integer resultTotal = memberInformService.adminCount(body.getAppId() , body.getMemberId(), body.getUserId(), body.getInformUserId(), body.getInformMemberId());
        List<MemberInform> resultList = memberInformService.adminList(body.getAppId(), body.getMemberId(), body.getUserId(), body.getInformUserId(), body.getInformMemberId(), body.getPageIndex(), body.getPageSize());

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
    @RequestMapping(value = "/member/inform/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody MemberInform body) {
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
    @RequestMapping(value = "/member/inform/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody MemberInform body) {
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
    @RequestMapping(value = "/member/inform/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody MemberInform body) {
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
    @RequestMapping(value = "/member/inform/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody MemberInform body) {
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