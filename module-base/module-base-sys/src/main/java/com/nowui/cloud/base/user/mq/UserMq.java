package com.nowui.cloud.base.user.mq;

/**
 * 用户消息服务接口
 * 
 * @author xupengfei
 *
 * 2018年1月5日
 */
public interface UserMq {
	
	void sendSave(String message);

}
