package com.nowui.cloud.base.notifyinfo.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.notifyinfo.entity.Notify;
import com.nowui.cloud.base.notifyinfo.rpc.NotifyRpc;
import com.nowui.cloud.base.notifyinfo.service.NotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息表系统端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "消息表", description = "消息表系统端接口管理")
@RestController
public class NotifySystemController implements NotifyRpc {

}