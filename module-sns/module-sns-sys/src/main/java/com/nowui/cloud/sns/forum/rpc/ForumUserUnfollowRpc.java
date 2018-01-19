package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛用户取关服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "forumUserUnfollowRpc")
@FeignClient(name = "module-sns")
public interface ForumUserUnfollowRpc {

}