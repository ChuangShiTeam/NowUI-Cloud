package com.nowui.cloud.sns.topic.service;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;

/**
 * 话题论坛关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicForumService extends BaseService<TopicForum> {

    /**
     * 话题论坛关联统计
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param topicId 话题编号
     * @return Integer 话题论坛关联统计
     */
    Integer countForAdmin(String appId, String forumId, String topicId);

    /**
     * 话题论坛关联列表
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param topicId 话题编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicForum> 话题论坛关联列表
     */
    List<TopicForum> listForAdmin(String appId, String forumId, String topicId, Integer pageIndex, Integer pageSize);

   /**
    * 根据论坛编号删除论坛话题关联
    * 
    * @param appId 应用编号
    * @param forumId 论坛编号
    * @param systemRequestUserId 请求用户编号
    */
    void deleteByForumId(String appId, String forumId, String systemRequestUserId);
    
    /**
     * 论坛话题数量
     * 
     * @param forumId 论坛编号
     * @return Integer 话题数量统计
     */
    Integer countByForumId(String forumId);
    
    /**
     * 当日论坛话题数量
     * 
     * @param forumId 论坛编号
     * @return Integer 当日话题数量统计
     */
    Integer countTodayByForumId(String forumId);
    
    /**
     * 根据论坛编号查询话题论坛列表
     * 
     * @param forumId 论坛编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicForum> 话题论坛列表
     */
    List<TopicForum> listByForumId(String forumId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据论坛编号查询话题论坛列表(根据上次查询排除)
     * 
     * @param forumId 论坛编号
     * @param excludeTopicIdList 排除的topic
     * @param systemCreateTime 上次查询的最后一个话题的发布时间
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicForum> 话题论坛列表
     */
    List<TopicForum> listByForumId(String forumId, List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 根据话题编号查询话题论坛列表
     * 
     * @param topicId 话题编号
     * @return List<TopicForum> 话题论坛列表
     */
    List<TopicForum> listByTopicId(String topicId);
    
    /**
     * 根据话题编号逻辑删除话题论坛信息
     * 
     * @param topicId 话题编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByTopicId(String topicId, String systemRequestUserId);
    
    /**
     * 批量保存话题论坛
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param topicForumList 话题多论坛列表
     * @param systemRequestUserId 请求用户编号
     */
    void batchSave(String appId, String topicId, List<TopicForum> topicForumList, String systemRequestUserId);
    
}
