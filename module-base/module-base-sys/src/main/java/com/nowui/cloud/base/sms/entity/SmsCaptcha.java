package com.nowui.cloud.base.sms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 短信验证码
 *
 * @author marcus
 *
 * 2018-03-15
 */
@Component

@TableName(value = "sms_captcha_info")
public class SmsCaptcha extends BaseEntity {

    /**
     * 短信验证码编号
     */
    @TableId
    @TableField
    private String smsCaptchaId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 验证码类型
     */
    @TableField
    private String smsCaptchaType;

    /**
     * 手机号码
     */
    @TableField
    private String smsCaptchaMobile;

    /**
     * 验证码
     */
    @TableField
    private String smsCaptchaCode;

    /**
     * IP地址
     */
    @TableField
    private String smsCaptchaIpAddress;


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