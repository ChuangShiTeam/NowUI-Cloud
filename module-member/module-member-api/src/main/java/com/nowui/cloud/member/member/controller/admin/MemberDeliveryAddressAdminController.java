package com.nowui.cloud.member.member.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.member.member.service.MemberDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址管理端控制器
 *
 * @author xinqing
 * <p>
 * 2018-01-14
 */
@Api(value = "会员收货地址", description = "会员收货地址管理端接口管理")
@RestController
public class MemberDeliveryAddressAdminController extends BaseController {

    @Autowired
    private MemberDeliveryAddressService memberDeliveryAddressService;

    @ApiOperation(value = "会员收货地址列表")
    @RequestMapping(value = "/member/delivery/address/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody MemberDeliveryAddress body) {
        validateRequest(
                body,
                MemberDeliveryAddress.APP_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddress.PAGE_INDEX,
                MemberDeliveryAddress.PAGE_SIZE
        );

        Integer resultTotal = memberDeliveryAddressService.countForAdmin(body.getAppId(), body.getMemberDeliveryAddressName(), body.getMemberDeliveryAddressPhone());
        List<MemberDeliveryAddress> resultList = memberDeliveryAddressService.listForAdmin(body.getAppId(), body.getMemberDeliveryAddressName(), body.getMemberDeliveryAddressPhone(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员收货地址信息")
    @RequestMapping(value = "/member/delivery/address/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody MemberDeliveryAddress body) {
        validateRequest(
                body,
                MemberDeliveryAddress.APP_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ID
        );

        MemberDeliveryAddressView result = memberDeliveryAddressService.find(body.getMemberDeliveryAddressId());
//        MemberDeliveryAddress result = memberDeliveryAddressService.find(body.getMemberDeliveryAddressId());

        validateResponse(
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddress.MEMBER_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员收货地址")
    @RequestMapping(value = "/member/delivery/address/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody MemberDeliveryAddress body) {
        validateRequest(
                body,
                MemberDeliveryAddress.APP_ID,
                MemberDeliveryAddress.MEMBER_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.save(body, Util.getRandomUUID(), body.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改会员收货地址")
    @RequestMapping(value = "/member/delivery/address/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody MemberDeliveryAddress body) {
        validateRequest(
                body,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddress.APP_ID,
                MemberDeliveryAddress.MEMBER_ID,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT,
                MemberDeliveryAddress.SYSTEM_VERSION
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.update(body, body.getMemberDeliveryAddressId(), body.getSystemUpdateUserId(), body.getSystemVersion());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除会员收货地址")
    @RequestMapping(value = "/member/delivery/address/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody MemberDeliveryAddress body) {
        validateRequest(
                body,
                MemberDeliveryAddress.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddress.APP_ID,
                MemberDeliveryAddress.SYSTEM_VERSION
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.delete(body.getMemberDeliveryAddressId(), body.getSystemUpdateUserId(), body.getSystemVersion());
//        Boolean result = memberDeliveryAddressService.delete(body.getMemberDeliveryAddressId(), body.getSystemRequestUserId(), body.getSystemVersion());

        Boolean success = false;

        if (result != null) {

            success = true;
        }

        return renderJson(success);
    }

}