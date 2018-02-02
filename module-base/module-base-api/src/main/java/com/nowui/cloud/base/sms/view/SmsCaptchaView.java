package com.nowui.cloud.base.sms.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 短信验证码视图
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
@Document(collection = "sms_captcha_info")
public class SmsCaptchaView extends BaseView {

    /**
     * 短信验证码编号
     */
    @Id
    private String smsCaptchaId;
    public static final String SMS_CAPTCHA_ID = "smsCaptchaId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 验证码类型
     */
    @Field
    private String smsCaptchaType;
    public static final String SMS_CAPTCHA_TYPE = "smsCaptchaType";

    /**
     * 手机号码
     */
    @Field
    private String smsCaptchaMobile;
    public static final String SMS_CAPTCHA_MOBILE = "smsCaptchaMobile";

    /**
     * 验证码
     */
    @Field
    private String smsCaptchaCode;
    public static final String SMS_CAPTCHA_CODE = "smsCaptchaCode";

    /**
     * IP地址
     */
    @Field
    private String smsCaptchaIpAddress;
    public static final String SMS_CAPTCHA_IP_ADDRESS = "smsCaptchaIpAddress";


    public String getSmsCaptchaId() {
        return getString(SMS_CAPTCHA_ID);
    }

    public void setSmsCaptchaId(String smsCaptchaId) {
        put(SMS_CAPTCHA_ID, smsCaptchaId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getSmsCaptchaType() {
        return getString(SMS_CAPTCHA_TYPE);
    }

    public void setSmsCaptchaType(String smsCaptchaType) {
        put(SMS_CAPTCHA_TYPE, smsCaptchaType);
    }

    public String getSmsCaptchaMobile() {
        return getString(SMS_CAPTCHA_MOBILE);
    }

    public void setSmsCaptchaMobile(String smsCaptchaMobile) {
        put(SMS_CAPTCHA_MOBILE, smsCaptchaMobile);
    }

    public String getSmsCaptchaCode() {
        return getString(SMS_CAPTCHA_CODE);
    }

    public void setSmsCaptchaCode(String smsCaptchaCode) {
        put(SMS_CAPTCHA_CODE, smsCaptchaCode);
    }

    public String getSmsCaptchaIpAddress() {
        return getString(SMS_CAPTCHA_IP_ADDRESS);
    }

    public void setSmsCaptchaIpAddress(String smsCaptchaIpAddress) {
        put(SMS_CAPTCHA_IP_ADDRESS, smsCaptchaIpAddress);
    }


}