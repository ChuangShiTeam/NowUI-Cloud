package com.nowui.cloud.member.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.member.member.entity.MemberDeliveryAddress;
import com.nowui.cloud.member.member.rpc.MemberDeliveryAddressRpc;
import com.nowui.cloud.member.member.service.MemberDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员收货地址系统端控制器
 *
 * @author xinqing
 *
 * 2018-01-14
 */
@Api(value = "会员收货地址", description = "会员收货地址系统端接口管理")
@RestController
public class MemberDeliveryAddressSystemController implements MemberDeliveryAddressRpc {

}