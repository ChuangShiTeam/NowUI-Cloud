package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 论坛用户关注服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "forumUserFollowRpc")
@FeignClient(name = "module-sns")
public interface ForumUserFollowRpc {

}