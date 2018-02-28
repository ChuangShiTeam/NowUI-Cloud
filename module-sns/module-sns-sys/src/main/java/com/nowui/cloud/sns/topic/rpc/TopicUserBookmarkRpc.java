package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题收藏服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicUserBookmarkRpc")
@FeignClient(name = "module-sns")
public interface TopicUserBookmarkRpc {

}