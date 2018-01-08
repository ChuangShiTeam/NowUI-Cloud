package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicTip;

import java.util.List;

/**
 * 话题提醒业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicTipService extends BaseService<TopicTip> {

    /**
     * 话题提醒统计
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 发送用户
     * @return Integer 话题提醒统计
     */
    Integer adminCount(String appId, String topicId, String userId);

    /**
     * 话题提醒列表
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 发送用户
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicTip> 话题提醒列表
     */
    List<TopicTip> adminList(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
}
