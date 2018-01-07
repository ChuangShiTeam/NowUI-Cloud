package com.nowui.cloud.base.user.entity.enums;

/**
 * 用户类型枚举
 * 
 * @author marcus
 *
 * 2018年1月7日
 */
public enum UserType {
    ADMIN("ADMIN", "管理员"),
    MEMBER("MEMBER", "会员");

    private String key;
    private String value;

    private UserType(String key, String value) {
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
