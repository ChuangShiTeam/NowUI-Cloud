package com.nowui.cloud.wawi.wawi.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.wawi.wawi.rpc.MemberVisitForumRpc;

import io.swagger.annotations.Api;

/**
 * 会员访问圈子系统端控制器
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Api(value = "会员访问圈子", description = "会员访问圈子系统端接口管理")
@RestController
public class MemberVisitForumSystemController implements MemberVisitForumRpc {

}