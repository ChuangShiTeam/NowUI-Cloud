package com.nowui.cloud.base.sms.rpc;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 短信验证码服务调用
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component(value = "smsCaptchaRpc")
@FeignClient(name = "module-base")
public interface SmsCaptchaRpc {
    
    /**
     * 发送阿里云短信
     * 
     * @param appId 应用编号
     * @param smsCaptchaType 验证码业务类型
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaIpAddress IP地址
     * @param captchaMinute 限制发送时间
     * @param systemRequestUserId 请求用户编号
     */
    public void aliyunSend(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress,
            int captchaMinute, String systemRequestUserId);
    
    /**
     * 验证验证码正确性
     * 
     * @param appId 应用编号
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaCode 验证码
     * @param startDate 发送开始时间
     * @return true 正确  false 不正确
     */
    public Boolean checkCaptchaCode(String appId, String smsCaptchaMobile, String smsCaptchaCode, Date startDate);
}