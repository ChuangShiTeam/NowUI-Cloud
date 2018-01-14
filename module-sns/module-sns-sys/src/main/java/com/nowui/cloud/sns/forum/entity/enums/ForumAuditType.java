package com.nowui.cloud.sns.forum.entity.enums;

/**
 *审核状态
 * 
 * @author xupengfei
 *
 * 2018年1月9日
 */
public enum ForumAuditType {
	WAITAUDIT("1", "等待审核"),
	AUDITPASS("2", "审核通过"),
	AUDITNOTPASS("3", "审核未通过");
	
	private String key;
    private String value;
    
    private ForumAuditType(String key, String value) {
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
