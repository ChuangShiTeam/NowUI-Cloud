package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题提醒服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicTipRpc")
@FeignClient(name = "module-sns")
public interface TopicTipRpc {

}