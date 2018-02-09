package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.view.TopicUserUnbookmarkView;

import java.util.List;

/**
 * 话题用户取消收藏关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserUnbookmarkService extends SuperService<TopicUserUnbookmark, TopicUserUnbookmarkView> {

    /**
     * 话题用户取消收藏关联统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return Integer 话题用户取消收藏关联统计
     */
    Integer countForAdmin(String appId, String topicId, String userId);

    /**
     * 话题用户取消收藏关联列表
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserUnbookmark> 话题用户取消收藏关联列表
     */
    List<TopicUserUnbookmark> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号和用户编号查询单条取消收藏记录
     * 
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return TopicUserUnbookmark 用户取消收藏单条记录
     */
    TopicUserUnbookmarkView findByTopicIdAndUserId(String topicId, String userId);
    
    /**
     * 根据话题编号查询取消收藏记录
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return List<TopicUserUnbookmark> 用户取消收藏记录列表
     */
    List<TopicUserUnbookmark> listByTopicId(String topicId);
    
    /**
     * 根据话题编号逻辑删除用户取消收藏记录
     * 
     * @param topicId
     * @param appId
     * @param systemRequestUserId
     */
    void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
    
    /**
     * 根据话题编号和用户编号逻辑删除用户取消收藏记录
     * 
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param appId 应用编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功    false 失败
     */
    TopicUserUnbookmark deleteByTopicIdAndUserId(String topicId, String userId, String appId, String systemRequestUserId);
    
}
