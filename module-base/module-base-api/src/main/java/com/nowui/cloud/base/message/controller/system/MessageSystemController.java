package com.nowui.cloud.base.message.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.message.entity.Message;
import com.nowui.cloud.base.message.rpc.MessageRpc;
import com.nowui.cloud.base.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "消息", description = "消息系统端接口管理")
@RestController
public class MessageSystemController implements MessageRpc {

}