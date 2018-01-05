package com.nowui.cloud.base.sms.rpc;

import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.rpc.fallback.SmsCaptchaRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短信验证码服务调用
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component(value = "SmsCaptchaRpc")
@FeignClient(name = "module-base", fallback = SmsCaptchaRpcFallback.class)
public interface SmsCaptchaRpc {

}