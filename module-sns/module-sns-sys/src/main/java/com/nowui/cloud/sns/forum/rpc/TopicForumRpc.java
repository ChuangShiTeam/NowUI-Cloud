package com.nowui.cloud.sns.forum.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题论坛关联服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicForumRpc")
@FeignClient(name = "module-sns")
public interface TopicForumRpc {

}