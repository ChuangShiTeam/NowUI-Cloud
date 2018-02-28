package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题论坛服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicForumRpc")
@FeignClient(name = "module-sns")
public interface TopicForumRpc {

}