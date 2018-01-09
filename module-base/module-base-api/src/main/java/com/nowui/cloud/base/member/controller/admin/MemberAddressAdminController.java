package com.nowui.cloud.base.member.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.member.entity.MemberAddress;
import com.nowui.cloud.base.member.service.MemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员地址管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员地址", description = "会员地址管理端接口管理")
@RestController
public class MemberAddressAdminController extends BaseController {

    @Autowired
    private MemberAddressService memberAddressService;

    @ApiOperation(value = "会员地址列表")
    @RequestMapping(value = "/member/address/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody MemberAddress body) {
        validateRequest(
                body,
                MemberAddress.APP_ID,
                MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.PAGE_INDEX,
                MemberAddress.PAGE_SIZE
        );

        Integer resultTotal = memberAddressService.adminCount(body.getAppId() , body.getMemberAddressProvince(), body.getMemberAddressCity(), body.getMemberAddressArea());
        List<MemberAddress> resultList = memberAddressService.adminList(body.getAppId(), body.getMemberAddressProvince(), body.getMemberAddressCity(), body.getMemberAddressArea(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberAddress.MEMBER_ADDRESS_ID,
                MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员地址信息")
    @RequestMapping(value = "/member/address/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody MemberAddress body) {
        validateRequest(
                body,
                MemberAddress.APP_ID,
                MemberAddress.MEMBER_ADDRESS_ID
        );

        MemberAddress result = memberAddressService.find(body.getMemberAddressId());

        validateResponse(
                MemberAddress.MEMBER_ADDRESS_ID,
                MemberAddress.MEMBER_ID,
                MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员地址")
    @RequestMapping(value = "/member/address/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody MemberAddress body) {
        validateRequest(
                body,
                MemberAddress.APP_ID,
                MemberAddress.MEMBER_ID,
                MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS
        );

        Boolean result = memberAddressService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改会员地址")
    @RequestMapping(value = "/member/address/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody MemberAddress body) {
        validateRequest(
                body,
                MemberAddress.MEMBER_ADDRESS_ID,
                MemberAddress.APP_ID,
                MemberAddress.MEMBER_ID,
                MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.SYSTEM_VERSION
        );

        Boolean result = memberAddressService.update(body, body.getMemberAddressId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除会员地址")
    @RequestMapping(value = "/member/address/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody MemberAddress body) {
        validateRequest(
                body,
                MemberAddress.MEMBER_ADDRESS_ID,
                MemberAddress.APP_ID,
                MemberAddress.SYSTEM_VERSION
        );

        Boolean result = memberAddressService.delete(body.getMemberAddressId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}