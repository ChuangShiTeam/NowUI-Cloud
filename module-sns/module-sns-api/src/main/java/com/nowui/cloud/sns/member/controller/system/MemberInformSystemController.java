package com.nowui.cloud.sns.member.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.member.member.rpc.MemberInformRpc;

import io.swagger.annotations.Api;

/**
 * 会员举报系统端控制器
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Api(value = "会员举报", description = "会员举报系统端接口管理")
@RestController
public class MemberInformSystemController implements MemberInformRpc {

}