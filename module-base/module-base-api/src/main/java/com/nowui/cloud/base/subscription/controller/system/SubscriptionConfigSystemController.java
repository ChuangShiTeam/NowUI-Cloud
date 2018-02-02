package com.nowui.cloud.base.subscription.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.subscription.rpc.SubscriptionConfigRpc;

import io.swagger.annotations.Api;

/**
 * 订阅配置系统端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "订阅配置", description = "订阅配置系统端接口管理")
@RestController
public class SubscriptionConfigSystemController implements SubscriptionConfigRpc {

}