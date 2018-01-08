package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛信息服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "forumRpc")
@FeignClient(name = "module-sns")
public interface ForumRpc {

}