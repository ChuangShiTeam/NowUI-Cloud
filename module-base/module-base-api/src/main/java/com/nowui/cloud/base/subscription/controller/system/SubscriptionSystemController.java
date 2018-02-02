package com.nowui.cloud.base.subscription.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.subscription.rpc.SubscriptionRpc;

import io.swagger.annotations.Api;

/**
 * 用户订阅表系统端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "用户订阅表", description = "用户订阅表系统端接口管理")
@RestController
public class SubscriptionSystemController implements SubscriptionRpc {

}