package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 点赞话题关联服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicUserLikeRpc")
@FeignClient(name = "module-sns")
public interface TopicUserLikeRpc {

}