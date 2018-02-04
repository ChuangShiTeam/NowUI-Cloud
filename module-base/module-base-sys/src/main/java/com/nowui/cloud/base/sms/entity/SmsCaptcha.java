package com.nowui.cloud.base.sms.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 短信验证码
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component
@TableName(value = "sms_captcha_info")
public class SmsCaptcha extends BaseEntity {

    /**
     * 短信验证码编号
     */
    @Id
    @TableId
    @NotNull(message = "短信验证码编号不能为空")
    @Length(max = 32, message = "短信验证码编号长度超出限制")
    private String smsCaptchaId;
    public static final String SMS_CAPTCHA_ID = "smsCaptchaId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 验证码类型
     */
    @TableField
    @NotNull(message = "验证码类型不能为空")
    @Length(max = 25, message = "验证码类型长度超出限制")
    private String smsCaptchaType;
    public static final String SMS_CAPTCHA_TYPE = "smsCaptchaType";

    /**
     * 手机号码
     */
    @TableField
    @NotNull(message = "手机号码不能为空")
    @Length(max = 11, message = "手机号码长度超出限制")
    private String smsCaptchaMobile;
    public static final String SMS_CAPTCHA_MOBILE = "smsCaptchaMobile";

    /**
     * 验证码
     */
    @TableField
    @NotNull(message = "验证码不能为空")
    @Length(max = 6, message = "验证码长度超出限制")
    private String smsCaptchaCode;
    public static final String SMS_CAPTCHA_CODE = "smsCaptchaCode";

    /**
     * IP地址
     */
    @TableField
    @NotNull(message = "IP地址不能为空")
    @Length(max = 25, message = "IP地址长度超出限制")
    private String smsCaptchaIpAddress;
    public static final String SMS_CAPTCHA_IP_ADDRESS = "smsCaptchaIpAddress";
    
    public static final String SMS_CAPTCHA_MINUTE = "smsCaptchaMinute";

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