package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;

import java.util.List;

/**
 * 话题用户取消点赞关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserUnlikeService extends BaseService<TopicUserUnlike> {

    /**
     * 话题用户取消点赞关联统计
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题Id
     * @return Integer 话题用户取消点赞关联统计
     */
    Integer adminCount(String appId, String userId, String topicId);

    /**
     * 话题用户取消点赞关联列表
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserUnlike> 话题用户取消点赞关联列表
     */
    List<TopicUserUnlike> adminList(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize);
}
