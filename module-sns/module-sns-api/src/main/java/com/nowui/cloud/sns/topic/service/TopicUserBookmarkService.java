package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;

import java.util.List;

/**
 * 话题收藏业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserBookmarkService extends BaseService<TopicUserBookmark> {

    /**
     * 话题收藏统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return Integer 话题收藏统计
     */
    Integer countForAdmin(String appId, String topicId, String userId);

    /**
     * 话题收藏列表
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserBookmark> 话题收藏列表
     */
    List<TopicUserBookmark> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号和用户编号查询单条话题收藏记录
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return TopicUserBookmark 单条话题收藏记录
     */
    TopicUserBookmark findTopicUserBookmark(String appId, String topicId, String userId);
    
    /**
     * 话题收藏统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return Integer 话题收藏统计
     */
    Integer countByTopicId(String appId, String topicId);
    
    /**
     * 根据话题编号查询话题收藏列表
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return List<TopicUserBookmark> 话题收藏列表
     */
    List<TopicUserBookmark> listByTopicId(String appId, String topicId);
    
}
