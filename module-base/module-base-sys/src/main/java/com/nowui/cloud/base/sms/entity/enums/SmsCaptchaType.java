package com.nowui.cloud.base.sms.entity.enums;

public enum SmsCaptchaType {
    REGISTER("REGISTER", "注册"),
    EDIT_PASSWORD("EDIT_PASSWORD", "修改密码"),
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
