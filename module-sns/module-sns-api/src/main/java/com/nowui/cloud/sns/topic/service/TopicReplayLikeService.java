package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicReplayLike;

import java.util.List;

/**
 * 话题回复业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicReplayLikeService extends BaseService<TopicReplayLike> {

    /**
     * 话题回复统计
     *
     * @param appId 应用编号
     * @param topicCommentId 话题评论id
     * @param userId 用户id
     * @return Integer 话题回复统计
     */
    Integer adminCount(String appId, String topicCommentId, String userId);

    /**
     * 话题回复列表
     *
     * @param appId 应用编号
     * @param topicCommentId 话题评论id
     * @param userId 用户id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicReplayLike> 话题回复列表
     */
    List<TopicReplayLike> adminList(String appId, String topicCommentId, String userId, Integer pageIndex, Integer pageSize);
}
