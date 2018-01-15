package com.nowui.cloud.member.member.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.member.member.rpc.MemberBackgroundRpc;

import io.swagger.annotations.Api;

/**
 * 会员背景系统端控制器
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Api(value = "会员背景", description = "会员背景系统端接口管理")
@RestController
public class MemberBackgroundSystemController implements MemberBackgroundRpc {

}