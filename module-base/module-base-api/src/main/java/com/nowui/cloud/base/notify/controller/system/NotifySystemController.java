package com.nowui.cloud.base.notify.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.notify.rpc.NotifyRpc;

import io.swagger.annotations.Api;

/**
 * 消息系统端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "消息", description = "消息系统端接口管理")
@RestController
public class NotifySystemController implements NotifyRpc {

}