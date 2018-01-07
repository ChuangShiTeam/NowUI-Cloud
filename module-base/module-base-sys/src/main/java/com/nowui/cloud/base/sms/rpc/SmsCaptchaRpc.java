package com.nowui.cloud.base.sms.rpc;

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

}