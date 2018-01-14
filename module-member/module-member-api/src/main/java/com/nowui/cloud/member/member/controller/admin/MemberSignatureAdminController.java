package com.nowui.cloud.member.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 	会员签名管理端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "	会员签名", description = "	会员签名管理端接口管理")
@RestController
public class MemberSignatureAdminController extends BaseController {

    @Autowired
    private MemberSignatureService memberSignatureService;

    @ApiOperation(value = "	会员签名列表")
    @RequestMapping(value = "/member/signature/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberSignature body) {
        validateRequest(
                body,
                MemberSignature.APP_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.MEMBER_SIGNATURE,
                MemberSignature.PAGE_INDEX,
                MemberSignature.PAGE_SIZE
        );

        Integer resultTotal = memberSignatureService.countForAdmin(body.getAppId() , body.getMemberId(), body.getMemberSignature());
        List<MemberSignature> resultList = memberSignatureService.listForAdmin(body.getAppId(), body.getMemberId(), body.getMemberSignature(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberSignature.MEMBER_SIGNATURE_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.MEMBER_SIGNATURE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "	会员签名信息")
    @RequestMapping(value = "/member/signature/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberSignature body) {
        validateRequest(
                body,
                MemberSignature.APP_ID,
                MemberSignature.MEMBER_SIGNATURE_ID
        );

        MemberSignature result = memberSignatureService.find(body.getMemberSignatureId());

        validateResponse(
                MemberSignature.MEMBER_SIGNATURE_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.MEMBER_SIGNATURE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增	会员签名")
    @RequestMapping(value = "/member/signature/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberSignature body) {
        validateRequest(
                body,
                MemberSignature.APP_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.MEMBER_SIGNATURE
        );

        Boolean result = memberSignatureService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改	会员签名")
    @RequestMapping(value = "/member/signature/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberSignature body) {
        validateRequest(
                body,
                MemberSignature.MEMBER_SIGNATURE_ID,
                MemberSignature.APP_ID,
                MemberSignature.MEMBER_ID,
                MemberSignature.MEMBER_SIGNATURE,
                MemberSignature.SYSTEM_VERSION
        );

        Boolean result = memberSignatureService.update(body, body.getMemberSignatureId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除	会员签名")
    @RequestMapping(value = "/member/signature/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberSignature body) {
        validateRequest(
                body,
                MemberSignature.MEMBER_SIGNATURE_ID,
                MemberSignature.APP_ID,
                MemberSignature.SYSTEM_VERSION
        );

        Boolean result = memberSignatureService.delete(body.getMemberSignatureId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}