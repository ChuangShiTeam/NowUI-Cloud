package com.nowui.cloud.member.member.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.member.member.service.MemberDeliveryAddressService;
import com.nowui.cloud.member.member.view.MemberDeliveryAddressView;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(value = "会员收货地址列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDeliveryAddressView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME, value = "姓名", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/member/delivery/address/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore MemberDeliveryAddressView memberDeliveryAddressView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDeliveryAddressView,
                MemberDeliveryAddressView.APP_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE
        );
        
        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = memberDeliveryAddressService.countForAdmin(memberDeliveryAddressView.getAppId(), memberDeliveryAddressView.getMemberDeliveryAddressName(), memberDeliveryAddressView.getMemberDeliveryAddressPhone());
        List<MemberDeliveryAddressView> resultList = memberDeliveryAddressService.listForAdmin(memberDeliveryAddressView.getAppId(), memberDeliveryAddressView.getMemberDeliveryAddressName(), memberDeliveryAddressView.getMemberDeliveryAddressPhone(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员收货地址信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDeliveryAddressView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID, value = "会员收货地址编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/delivery/address/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore MemberDeliveryAddressView memberDeliveryAddressView) {
        validateRequest(
                memberDeliveryAddressView,
                MemberDeliveryAddressView.APP_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID
        );

        MemberDeliveryAddressView result = memberDeliveryAddressService.find(memberDeliveryAddressView.getMemberDeliveryAddressId());

        validateResponse(
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddressView.MEMBER_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增会员收货地址", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDeliveryAddressView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_ID, value = "会员编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME, value = "姓名", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE, value = "省", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY, value = "市", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA, value = "区", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS, value = "详细地址", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE, value = "邮编", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT, value = "是否默认地址", required = true, paramType = "query", dataType = "boolean"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/delivery/address/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore MemberDeliveryAddress memberDeliveryAddress, @ApiIgnore MemberDeliveryAddressView memberDeliveryAddressView, @ApiIgnore CommonView commonView) {
        validateRequest(
                commonView,
                MemberDeliveryAddressView.APP_ID,
                MemberDeliveryAddressView.MEMBER_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.save(memberDeliveryAddress, Util.getRandomUUID(), commonView.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            // 保存到会员视图
            memberDeliveryAddressView.copy(result);
            
            memberDeliveryAddressService.save(memberDeliveryAddressView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改会员收货地址", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDeliveryAddressView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID, value = "会员收货地址编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_ID, value = "会员编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME, value = "姓名", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE, value = "省", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY, value = "市", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA, value = "区", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS, value = "详细地址", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE, value = "邮编", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT, value = "是否默认地址", required = true, paramType = "query", dataType = "boolean"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.SYSTEM_VERSION, value = "是否默认地址", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/delivery/address/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore MemberDeliveryAddress memberDeliveryAddress, @ApiIgnore MemberDeliveryAddressView memberDeliveryAddressView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDeliveryAddressView,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddressView.APP_ID,
                MemberDeliveryAddressView.MEMBER_ID,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_NAME,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PHONE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_PROVINCE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_CITY,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_AREA,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ADDRESS,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_POSTCODE,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_IS_DEFAULT,
                MemberDeliveryAddressView.SYSTEM_VERSION
        );
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.update(memberDeliveryAddress, memberDeliveryAddress.getMemberDeliveryAddressId(), memberDeliveryAddress.getAppId(), commonView.getSystemUpdateUserId(), commonView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 更新会员收货地址视图信息
            memberDeliveryAddressView.copy(result);
            
            memberDeliveryAddressService.update(memberDeliveryAddressView, memberDeliveryAddressView.getMemberDeliveryAddressId());
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除会员收货地址", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDeliveryAddressView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID, value = "会员收货地址编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDeliveryAddressView.SYSTEM_VERSION, value = "是否默认地址", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/delivery/address/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore MemberDeliveryAddressView memberDeliveryAddressView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDeliveryAddressView,
                MemberDeliveryAddressView.MEMBER_DELIVERY_ADDRESS_ID,
                MemberDeliveryAddressView.APP_ID,
                MemberDeliveryAddressView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        MemberDeliveryAddress result = memberDeliveryAddressService.delete(memberDeliveryAddressView.getMemberDeliveryAddressId(), memberDeliveryAddressView.getAppId(), commonView.getSystemRequestUserId(), memberDeliveryAddressView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除会员收货地址视图信息
            memberDeliveryAddressView.copy(result);
            
            memberDeliveryAddressService.delete(memberDeliveryAddressView, memberDeliveryAddressView.getMemberDeliveryAddressId());
            
            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "会员收货地址信息数据同步", httpMethod = "POST")
    @RequestMapping(value = "/member/delivery/address/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<MemberDeliveryAddress> memberDeliveryAddressList = memberDeliveryAddressService.listByMysql();

        for (MemberDeliveryAddress memberDeliveryAddress : memberDeliveryAddressList) {
            MemberDeliveryAddressView memberDeliveryAddressView = new MemberDeliveryAddressView();
            memberDeliveryAddressView.copy(memberDeliveryAddress);

            memberDeliveryAddressService.saveOrUpdate(memberDeliveryAddressView, memberDeliveryAddressView.getMemberDeliveryAddressId());
        }

        return renderJson(true);
    }

}