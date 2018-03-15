package com.nowui.cloud.base.sms.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 短信验证码视图
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component
@Document(collection = "sms_captcha_info")
public class SmsCaptchaView extends BaseView {

    /**
     * 短信验证码编号
     */
    @KeyId
    @Field
    @NotNull(message = "短信验证码编号不能为空")
    @Length(max = 32, message = "短信验证码编号长度超出限制")
    private String smsCaptchaId;
    public static final String SMS_CAPTCHA_ID = "smsCaptchaId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 验证码类型
     */
    @Field
    @NotNull(message = "验证码类型不能为空")
    @Length(max = 25, message = "验证码类型长度超出限制")
    private String smsCaptchaType;
    public static final String SMS_CAPTCHA_TYPE = "smsCaptchaType";

    /**
     * 手机号码
     */
    @Field
    @NotNull(message = "手机号码不能为空")
    @Length(max = 11, message = "手机号码长度超出限制")
    private String smsCaptchaMobile;
    public static final String SMS_CAPTCHA_MOBILE = "smsCaptchaMobile";

    /**
     * 验证码
     */
    @Field
    @NotNull(message = "验证码不能为空")
    @Length(max = 6, message = "验证码长度超出限制")
    private String smsCaptchaCode;
    public static final String SMS_CAPTCHA_CODE = "smsCaptchaCode";

    /**
     * IP地址
     */
    @Field
    @NotNull(message = "IP地址不能为空")
    @Length(max = 25, message = "IP地址长度超出限制")
    private String smsCaptchaIpAddress;
    public static final String SMS_CAPTCHA_IP_ADDRESS = "smsCaptchaIpAddress";


    public String getSmsCaptchaId() {
        return smsCaptchaId;
    }

    public void setSmsCaptchaId(String smsCaptchaId) {
        this.smsCaptchaId = smsCaptchaId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getSmsCaptchaType() {
        return smsCaptchaType;
    }

    public void setSmsCaptchaType(String smsCaptchaType) {
        this.smsCaptchaType = smsCaptchaType;
    }
    
    public String getSmsCaptchaMobile() {
        return smsCaptchaMobile;
    }

    public void setSmsCaptchaMobile(String smsCaptchaMobile) {
        this.smsCaptchaMobile = smsCaptchaMobile;
    }
    
    public String getSmsCaptchaCode() {
        return smsCaptchaCode;
    }

    public void setSmsCaptchaCode(String smsCaptchaCode) {
        this.smsCaptchaCode = smsCaptchaCode;
    }
    
    public String getSmsCaptchaIpAddress() {
        return smsCaptchaIpAddress;
    }

    public void setSmsCaptchaIpAddress(String smsCaptchaIpAddress) {
        this.smsCaptchaIpAddress = smsCaptchaIpAddress;
    }
    

}