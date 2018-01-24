package com.nowui.cloud.sns.topic.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 话题的评论用户点赞服务调用
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Component(value = "topicCommentUserLikeRpc")
@FeignClient(name = "module-sns")
public interface TopicCommentUserLikeRpc {

}