package com.nowui.cloud.base.usernotifyinfo.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.usernotifyinfo.entity.UserNotify;
import com.nowui.cloud.base.usernotifyinfo.rpc.UserNotifyRpc;
import com.nowui.cloud.base.usernotifyinfo.service.UserNotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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