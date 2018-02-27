package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题用户取消收藏关联服务调用
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component(value = "topicUserUnbookmarkRpc")
@FeignClient(name = "module-sns")
public interface TopicUserUnbookmarkRpc {

}