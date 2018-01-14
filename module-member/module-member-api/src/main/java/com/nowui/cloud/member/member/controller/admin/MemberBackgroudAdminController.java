package com.nowui.cloud.member.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.member.member.entity.MemberBackgroud;
import com.nowui.cloud.member.member.service.MemberBackgroudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员背景管理端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "会员背景", description = "会员背景管理端接口管理")
@RestController
public class MemberBackgroudAdminController extends BaseController {

    @Autowired
    private MemberBackgroudService memberBackgroudService;

    @ApiOperation(value = "会员背景列表")
    @RequestMapping(value = "/member/backgroud/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberBackgroud body) {
        validateRequest(
                body,
                MemberBackgroud.APP_ID,
                MemberBackgroud.MEMBER_ID,
                MemberBackgroud.MEMBER_BACKGROUND_FILE_ID,
                MemberBackgroud.PAGE_INDEX,
                MemberBackgroud.PAGE_SIZE
        );

        Integer resultTotal = memberBackgroudService.countForAdmin(body.getAppId() , body.getMemberId(), body.getMemberBackgroundFileId());
        List<MemberBackgroud> resultList = memberBackgroudService.listForAdmin(body.getAppId(), body.getMemberId(), body.getMemberBackgroundFileId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberBackgroud.MEMBER_BACKGROUND_ID,
                MemberBackgroud.MEMBER_ID,
                MemberBackgroud.MEMBER_BACKGROUND_FILE_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员背景信息")
    @RequestMapping(value = "/member/backgroud/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberBackgroud body) {
        validateRequest(
                body,
                MemberBackgroud.APP_ID,
                MemberBackgroud.MEMBER_BACKGROUND_ID
        );

        MemberBackgroud result = memberBackgroudService.find(body.getMemberBackgroundId());

        validateResponse(
                MemberBackgroud.MEMBER_BACKGROUND_ID,
                MemberBackgroud.MEMBER_ID,
                MemberBackgroud.MEMBER_BACKGROUND_FILE_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberBackgroud body) {
        validateRequest(
                body,
                MemberBackgroud.APP_ID,
                MemberBackgroud.MEMBER_ID,
                MemberBackgroud.MEMBER_BACKGROUND_FILE_ID
        );

        Boolean result = memberBackgroudService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberBackgroud body) {
        validateRequest(
                body,
                MemberBackgroud.MEMBER_BACKGROUND_ID,
                MemberBackgroud.APP_ID,
                MemberBackgroud.MEMBER_ID,
                MemberBackgroud.MEMBER_BACKGROUND_FILE_ID,
                MemberBackgroud.SYSTEM_VERSION
        );

        Boolean result = memberBackgroudService.update(body, body.getMemberBackgroundId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员背景")
    @RequestMapping(value = "/member/backgroud/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberBackgroud body) {
        validateRequest(
                body,
                MemberBackgroud.MEMBER_BACKGROUND_ID,
                MemberBackgroud.APP_ID,
                MemberBackgroud.SYSTEM_VERSION
        );

        Boolean result = memberBackgroudService.delete(body.getMemberBackgroundId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}