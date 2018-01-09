package com.nowui.cloud.member.member.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.member.rpc.MemberAddressRpc;

import io.swagger.annotations.Api;

/**
 * 会员地址系统端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "会员地址", description = "会员地址系统端接口管理")
@RestController
public class MemberAddressSystemController implements MemberAddressRpc {

}