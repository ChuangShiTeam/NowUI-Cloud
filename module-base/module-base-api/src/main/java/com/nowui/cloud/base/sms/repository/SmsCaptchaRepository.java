package com.nowui.cloud.base.sms.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.sms.view.SmsCaptchaView;
import org.springframework.stereotype.Component;

/**
 * 短信验证码视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface SmsCaptchaRepository extends BaseRepository<SmsCaptchaView> {

}
