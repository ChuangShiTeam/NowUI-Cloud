package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.base.member.entity.MemberFollow;
import com.nowui.cloud.base.member.service.MemberFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员关注管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员关注", description = "会员关注管理端接口管理")
@RestController
public class MemberFollowAdminController extends BaseController {

    @Autowired
    private MemberFollowService memberFollowService;

    @ApiOperation(value = "会员关注列表")
    @RequestMapping(value = "/member/follow/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.APP_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                MemberFollow.PAGE_INDEX,
                MemberFollow.PAGE_SIZE
        );

        Integer resultTotal = memberFollowService.adminCount(body.getAppId() , body.getMemberId(), body.getUserId());
        List<MemberFollow> resultList = memberFollowService.adminList(body.getAppId(), body.getMemberId(), body.getUserId(), body.getM(), body.getN());

        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员关注信息")
    @RequestMapping(value = "/member/follow/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.APP_ID,
                MemberFollow.MEMBER_FOLLOW_ID
        );

        MemberFollow result = memberFollowService.find(body.getMemberFollowId());

        validateResponse(
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员关注")
    @RequestMapping(value = "/member/follow/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.APP_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID
        );

        Boolean result = memberFollowService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员关注")
    @RequestMapping(value = "/member/follow/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.APP_ID,
                MemberFollow.MEMBER_ID,
                MemberFollow.USER_ID,
                MemberFollow.FOLLOW_MEMBER_ID,
                MemberFollow.FOLLOW_USER_ID,
                MemberFollow.SYSTEM_VERSION
        );

        Boolean result = memberFollowService.update(body, body.getMemberFollowId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员关注")
    @RequestMapping(value = "/member/follow/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody MemberFollow body) {
        validateRequest(
                body,
                MemberFollow.MEMBER_FOLLOW_ID,
                MemberFollow.APP_ID,
                MemberFollow.SYSTEM_VERSION
        );

        Boolean result = memberFollowService.delete(body.getMemberFollowId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}