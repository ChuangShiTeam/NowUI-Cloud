package com.nowui.cloud.base.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;

/**
 * 短信验证码消息队列接口
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component
public class SmsCaptchaListener {
    
    @Autowired
    private SmsCaptchaService smsCaptchaService;
    
    @RabbitListener(queues = "topic.sms.captcha.aliyun.send")
    public void receiveSmsCaptchaAliyunSend(String message) {
        System.out.println("收到保存用户消息：" + message);
        try {
            SmsCaptcha smsCaptcha = JSONObject.parseObject(message, SmsCaptcha.class);
            smsCaptchaService.aliyunSend(smsCaptcha.getAppId(), smsCaptcha.getSmsCaptchaType(), smsCaptcha.getSmsCaptchaMobile(), smsCaptcha.getSmsCaptchaIpAddress(), smsCaptcha.getInteger(SmsCaptcha.SMS_CAPTCHA_MINUTE), smsCaptcha.getSystemRequestUserId());
        } catch (Exception e) {
            
        }
        
    }

}
