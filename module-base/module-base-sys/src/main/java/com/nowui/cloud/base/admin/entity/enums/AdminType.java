package com.nowui.cloud.base.admin.entity.enums;

public enum AdminType {
	ADMIN("ADMIN", "管理员");
	
	private String key;
	private String value;
	
	private AdminType(String key, String value) {
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
