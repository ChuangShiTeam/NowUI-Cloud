package com.nowui.cloud.sns.forum.entity.enums;

/**
 *审核状态
 * 
 * @author xupengfei
 *
 * 2018年1月9日
 */
public enum ForumAuditStatus {
	WAIT_AUDIT("WAIT_AUDIT", "等待审核"),
	AUDIT_PASS("AUDIT_PASS", "审核通过"),
	AUDIT_NOT_PASS("AUDIT_NOT_PASS", "审核未通过");
	
	private String key;
    private String value;
    
    private ForumAuditStatus(String key, String value) {
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
