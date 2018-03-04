package com.nowui.cloud.base.sms.controller.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;

import io.swagger.annotations.Api;

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
    
    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @Override
    public void aliyunSendV1(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress,
            int captchaMinute, String systemRequestUserId) {
        smsCaptchaService.aliyunSend(appId, smsCaptchaType, smsCaptchaMobile, smsCaptchaIpAddress, captchaMinute, systemRequestUserId);
    }

    @Override
    public Boolean checkCaptchaCodeV1(String appId, String smsCaptchaMobile, String smsCaptchaCode, String startDate) {
        Integer count = smsCaptchaService.countByMobileAndCode(appId, smsCaptchaMobile, smsCaptchaCode, startDate);
        return count > 0;
    }

}