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
     * 根据话题编号和userId查询单条取消收藏记录
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return TopicUserUnbookmark 用户取消收藏单条记录
     */
    TopicUserUnbookmark findUnBookMark(String appId, String topicId, String userId);
    
    /**
     * 根据话题编号查询取消收藏记录
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @return List<TopicUserUnbookmark> 用户取消收藏记录列表
     */
    List<TopicUserUnbookmark> allUnBookMarkListByTopic(String appId, String topicId);
    
}
