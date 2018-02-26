package com.nowui.cloud.base.sms.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.rpc.BaseRpc;

/**
 * 短信验证码服务调用
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component(value = "smsCaptchaRpc")
@FeignClient(name = "module-base")
public interface SmsCaptchaRpc extends BaseRpc {
    
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
    @RequestMapping(value = "/sms/captcha/system/v1/aliyun/send", method = RequestMethod.POST)
    public void aliyunSendV1(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "smsCaptchaType", required = true) String smsCaptchaType, 
            @RequestParam(value = "smsCaptchaMobile", required = true) String smsCaptchaMobile, 
            @RequestParam(value = "smsCaptchaIpAddress", required = true) String smsCaptchaIpAddress,
            @RequestParam(value = "captchaMinute", required = true) int captchaMinute, 
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId);
    
    /**
     * 验证验证码正确性
     * 
     * @param appId 应用编号
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaCode 验证码
     * @param startDate 发送开始时间
     * @return true 正确  false 不正确
     */
    @RequestMapping(value = "/sms/captcha/system/v1/check/captcha/code", method = RequestMethod.POST)
    public Boolean checkCaptchaCodeV1(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "smsCaptchaMobile", required = true) String smsCaptchaMobile, 
            @RequestParam(value = "smsCaptchaCode", required = true) String smsCaptchaCode, 
            @RequestParam(value = "startDate", required = true) String startDate);
}