package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛审核信息服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "forumAuditRpc")
@FeignClient(name = "module-sns")
public interface ForumAuditRpc {

}