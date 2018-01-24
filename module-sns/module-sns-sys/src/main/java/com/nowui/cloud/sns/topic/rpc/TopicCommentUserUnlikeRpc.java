package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题评论的取消点赞服务调用
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Component(value = "topicCommentUserUnlikeRpc")
@FeignClient(name = "module-sns")
public interface TopicCommentUserUnlikeRpc {

}