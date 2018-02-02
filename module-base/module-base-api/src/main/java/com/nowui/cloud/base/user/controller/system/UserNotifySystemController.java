package com.nowui.cloud.base.user.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.rpc.UserNotifyRpc;

import io.swagger.annotations.Api;

/**
 * 用户消息队列表系统端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "用户消息队列表", description = "用户消息队列表系统端接口管理")
@RestController
public class UserNotifySystemController implements UserNotifyRpc {

}