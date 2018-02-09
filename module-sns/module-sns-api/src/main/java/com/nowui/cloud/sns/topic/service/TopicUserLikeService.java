package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;

import java.util.List;

/**
 * 点赞话题业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserLikeService extends SuperService<TopicUserLike, TopicUserLikeView> {

    /**
     * 点赞话题统计
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题编号
     * @return Integer 点赞话题统计
     */
    Integer countForAdmin(String appId, String userId, String topicId);

    /**
     * 点赞话题列表
     *
     * @param appId 应用编号
     * @param userId 关注人
     * @param topicId 话题编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserLike> 点赞话题列表
     */
    List<TopicUserLike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号和用户编号查找单条点赞记录
     * 
     * @param topicId 话题编号
     * @param userId 用户编号
     * @return TopicUserLike 单调点赞记录
     */
    TopicUserLikeView findByTopicIdAndUserId(String topicId, String userId);
    
    /**
     * 点赞话题统计
     *
     * @param topicId 话题编号
     * @return Integer 点赞话题统计
     */
    Integer countByTopicId(String topicId);
    
    /**
     * 根据topicId查询所有列表
     * 
     * @param topicId 话题编号
     * @return List<TopicUserLike> 话题点赞列表
     */
    List<TopicUserLike> listByTopicId(String topicId);
    
    /**
     * 根据topicId查询所有列表
     * 
     * @param topicId 话题编号
     * @return List<TopicUserLike> 话题点赞列表
     */
    List<TopicUserLikeView> listByTopicIdHavePage(String topicId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号逻辑删除话题点赞
     * 
     * @param topicId 话题编号
     * @param systemRequestUserId
     */
    void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
    
    /**
     * 根据话题编号和用户编号删除话题点赞
     * 
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    TopicUserLike deleteByTopicIdAndUserId(String topicId, String userId, String appId, String systemRequestUserId);
    
    /**
     * 保存话题点赞
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     * @return  Boolean true 成功   false 失败
     */
    TopicUserLike save(String appId, String topicId, String userId, String systemRequestUserId);

}
