package com.nowui.cloud.base.sms.rpc.fallback;

import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.rpc.SmsCaptchaRpc;
import org.springframework.stereotype.Component;

/**
 * 短信验证码服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component(value = "SmsCaptchaRpcFallback")
public class SmsCaptchaRpcFallback implements SmsCaptchaRpc {

}
