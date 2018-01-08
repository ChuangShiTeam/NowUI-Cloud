package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;

import java.util.List;

/**
 * 话题用户取消收藏关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserUnbookmarkService extends BaseService<TopicUserUnbookmark> {

    /**
     * 话题用户取消收藏关联统计
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 用户ID
     * @return Integer 话题用户取消收藏关联统计
     */
    Integer adminCount(String appId, String topicId, String userId);

    /**
     * 话题用户取消收藏关联列表
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 用户ID
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserUnbookmark> 话题用户取消收藏关联列表
     */
    List<TopicUserUnbookmark> adminList(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
}
