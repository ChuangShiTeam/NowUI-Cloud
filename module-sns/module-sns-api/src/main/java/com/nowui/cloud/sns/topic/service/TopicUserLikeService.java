package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;

import java.util.List;

/**
 * 点赞话题关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserLikeService extends BaseService<TopicUserLike> {

    /**
     * 点赞话题关联统计
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题Id
     * @return Integer 点赞话题关联统计
     */
    Integer countForAdmin(String appId, String userId, String topicId);

    /**
     * 点赞话题关联列表
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserLike> 点赞话题关联列表
     */
    List<TopicUserLike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize);
}
