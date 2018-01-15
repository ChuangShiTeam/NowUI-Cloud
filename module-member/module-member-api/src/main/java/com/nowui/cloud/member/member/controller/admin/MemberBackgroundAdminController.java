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
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员背景管理端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "会员背景", description = "会员背景管理端接口管理")
@RestController
public class MemberBackgroundAdminController extends BaseController {

    @Autowired
    private MemberBackgroundService memberBackgroundService;

    @ApiOperation(value = "会员背景列表")
    @RequestMapping(value = "/member/backgroud/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberBackground body) {
        validateRequest(
                body,
                MemberBackground.APP_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_ID,
                MemberBackground.PAGE_INDEX,
                MemberBackground.PAGE_SIZE
        );

        Integer resultTotal = memberBackgroundService.countForAdmin(body.getAppId() , body.getMemberId(), body.getMemberBackgroundFileId());
        List<MemberBackground> resultList = memberBackgroundService.listForAdmin(body.getAppId(), body.getMemberId(), body.getMemberBackgroundFileId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberBackground.MEMBER_BACKGROUND_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员背景信息")
    @RequestMapping(value = "/member/backgroud/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberBackground body) {
        validateRequest(
                body,
                MemberBackground.APP_ID,
                MemberBackground.MEMBER_BACKGROUND_ID
        );

        MemberBackground result = memberBackgroundService.find(body.getMemberBackgroundId());

        validateResponse(
                MemberBackground.MEMBER_BACKGROUND_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberBackground body) {
        validateRequest(
                body,
                MemberBackground.APP_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_ID
        );

        Boolean result = memberBackgroundService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberBackground body) {
        validateRequest(
                body,
                MemberBackground.MEMBER_BACKGROUND_ID,
                MemberBackground.APP_ID,
                MemberBackground.MEMBER_ID,
                MemberBackground.MEMBER_BACKGROUND_FILE_ID,
                MemberBackground.SYSTEM_VERSION
        );

        Boolean result = memberBackgroundService.update(body, body.getMemberBackgroundId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberBackground body) {
        validateRequest(
                body,
                MemberBackground.MEMBER_BACKGROUND_ID,
                MemberBackground.APP_ID,
                MemberBackground.SYSTEM_VERSION
        );

        Boolean result = memberBackgroundService.delete(body.getMemberBackgroundId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}