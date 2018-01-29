package com.nowui.cloud.base.subscriptionconfiginfo.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.subscriptionconfiginfo.entity.SubscriptionConfig;
import com.nowui.cloud.base.subscriptionconfiginfo.rpc.SubscriptionConfigRpc;
import com.nowui.cloud.base.subscriptionconfiginfo.service.SubscriptionConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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