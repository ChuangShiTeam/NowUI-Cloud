package com.nowui.cloud.base.sms.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.base.sms.repository.SmsCaptchaRepository;
import com.nowui.cloud.base.sms.view.SmsCaptchaView;
import org.springframework.stereotype.Component;

/**
 * 短信验证码视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
public class SmsCaptchaRepositoryImpl extends BaseRepositoryImpl<SmsCaptchaView> implements SmsCaptchaRepository {

}
