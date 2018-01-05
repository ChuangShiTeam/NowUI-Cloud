package com.nowui.cloud.base.sms.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信验证码系统端控制器
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Api(value = "短信验证码", description = "短信验证码系统端接口管理")
@RestController
public class SmsCaptchaSystemController implements SmsCaptchaRpc {

}