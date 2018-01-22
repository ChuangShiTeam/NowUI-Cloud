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
     * @param userId 用户编号
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
     * @param userId 用户编号
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyCommentId 被回复评论id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 话题评论统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return Integer 话题评论统计
     */
    Integer countByTopicId(String appId, String topicId);

    
    /**
     * 根据话题编号查询话题评论列表
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listByTopicId(String appId, String topicId);
    
    /**
     * 根据话题编号查询话题评论分页列表
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listByTopicId(String appId, String topicId, Integer pageSize, Integer pageIndex);

}
