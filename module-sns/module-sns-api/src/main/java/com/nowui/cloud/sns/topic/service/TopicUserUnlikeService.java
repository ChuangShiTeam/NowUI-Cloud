package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;

import java.util.List;

/**
 * 话题用户取消点赞关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserUnlikeService extends SuperService<TopicUserUnlike, TopicUserUnlikeView> {

    /**
     * 话题用户取消点赞关联统计
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题编号
     * @return Integer 话题用户取消点赞关联统计
     */
    Integer countForAdmin(String appId, String userId, String topicId);

    /**
     * 话题用户取消点赞关联列表
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserUnlike> 话题用户取消点赞关联列表
     */
    List<TopicUserUnlike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号和用户编号查询话题用户取消点赞记录
     * 
     * @param userId 关注人
     * @param topicId 话题编号
     * @return TopicUserUnlike 
     */
    TopicUserUnlikeView findByTopciIdAndUserId(String topicId, String userId);
    
    /**
     * 根据话题编号逻辑删除用户取消点赞记录
     * 
     * @param topicId 话题编号
     * @param appId 应用编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
    
    /**
     * 根据话题编号和用户编号
     * 
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true  成功      false  失败
     */
    boolean deleteByTopicIdAndUserId(String topicId, String userId, String appId, String systemRequestUserId);
    
    /**
     * 根据话题编号查询话题用户取消点赞记录
     * 
     * @param topicId 话题编号
     * @return List<TopicUserUnlike> 取消点赞记录列表
     */
    List<TopicUserUnlike> listByTopicId(String topicId);

}
