package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题用户取消点赞关联服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicUserUnlikeRpc")
@FeignClient(name = "module-sns")
public interface TopicUserUnlikeRpc {

}