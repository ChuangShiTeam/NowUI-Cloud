package com.nowui.cloud.base.sms.entity.enums;

/**
 * 验证码类型枚举
 * 
 * @author marcus
 *
 * 2018年1月9日
 */
public enum SmsCaptchaType {
    /**
     * 注册
     */
    REGISTER("REGISTER", "注册"),
    /**
     * 修改密码
     */
    EDIT_PASSWORD("EDIT_PASSWORD", "修改密码"),
    /**
     * 登录
     */
    LOGIN("LOGIN", "登录");

    private String key;
    private String value;

    private SmsCaptchaType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
