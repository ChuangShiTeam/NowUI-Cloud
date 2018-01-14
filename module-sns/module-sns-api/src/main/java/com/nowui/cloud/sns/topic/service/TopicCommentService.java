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
     * @param topicReplyCommentId 被回复评论id
     * @return Integer 话题评论统计
     */
    Integer countForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId);

    /**
     * 话题评论列表
     *
     * @param appId 应用编号
     * @param userId 用户id
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyCommentId 被回复评论id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId, Integer pageIndex, Integer pageSize);
    
    /**
     * 全部话题评论列表By userId or topicId
     * 
     * @param appId
     * @param userId
     * @param topicId
     * @return List<TopicComment> 全部列表
     */
    List<TopicComment> allCommentList(String appId, String userId, String topicId);

}
