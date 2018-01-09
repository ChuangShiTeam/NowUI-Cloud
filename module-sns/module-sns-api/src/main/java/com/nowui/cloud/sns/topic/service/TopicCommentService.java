package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicComment;

import java.util.List;

/**
 * 话题评论业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicCommentService extends BaseService<TopicComment> {

    /**
     * 话题评论统计
     *
     * @param appId 应用编号
     * @param userId 用户id
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyContent 回复内容
     * @return Integer 话题评论统计
     */
    Integer countForAdmin(String appId, String userId, String topicCommentContent, String topicReplayUserId, String topicReplyContent);

    /**
     * 话题评论列表
     *
     * @param appId 应用编号
     * @param userId 用户id
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyContent 回复内容
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listForAdmin(String appId, String userId, String topicCommentContent, String topicReplayUserId, String topicReplyContent, Integer pageIndex, Integer pageSize);
}
