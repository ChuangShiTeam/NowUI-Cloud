package com.nowui.cloud.member.member.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.member.member.rpc.MemberDeliveryAddressRpc;

import io.swagger.annotations.Api;

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