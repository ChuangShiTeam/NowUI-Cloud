package com.nowui.cloud.base.subscriptioninfo.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.subscriptioninfo.entity.Subscription;
import com.nowui.cloud.base.subscriptioninfo.rpc.SubscriptionRpc;
import com.nowui.cloud.base.subscriptioninfo.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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