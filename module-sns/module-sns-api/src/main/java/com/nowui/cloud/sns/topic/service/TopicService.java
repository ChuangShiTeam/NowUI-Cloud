package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.Topic;

import java.util.List;

/**
 * 话题信息业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicService extends BaseService<Topic> {

    /**
     * 话题信息统计
     *
     * @param appId 应用编号
     * @param topicForumId 论坛Id
     * @param topicSummary 动态
     * @param userId 用户ID
     * @param latitude 经度
     * @param longtitude 纬度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param boolean2 置顶
     * @param boolean3 是否推荐
     * @param integer2 置顶级别
     * @return Integer 话题信息统计
     */
    Integer countForAdmin(String appId, String topicForumId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecomand, Integer topicTopLevel);

    /**
     * 话题信息列表
     *
     * @param appId 应用编号
     * @param topicForumId 论坛Id
     * @param topicSummary 动态
     * @param userId 用户ID
     * @param latitude 经度
     * @param longtitude 纬度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param topicIsTop 置顶
     * @param topicIsRecomand 是否推荐
     * @param topTopLevel 置顶级别
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Topic> 话题信息列表
     */
    List<Topic> listForAdmin(String appId, String topicForumId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecomand, Integer topTopLevel, Integer pageIndex, Integer pageSize);
}
