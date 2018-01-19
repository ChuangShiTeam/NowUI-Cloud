package com.nowui.cloud.sns.topic.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
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
     * @param forumId 论坛Id
     * @param topicId 话题Id
     * @return Integer 话题论坛关联统计
     */
    Integer countForAdmin(String appId, String forumId, String topicId);

    /**
     * 话题论坛关联列表
     *
     * @param appId 应用编号
     * @param forumId 论坛Id
     * @param topicId 话题Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicForum> 话题论坛关联列表
     */
    List<TopicForum> listForAdmin(String appId, String forumId, String topicId, Integer pageIndex, Integer pageSize);

   /**
    * 删除根据论坛id
    * 
    * @param appId 应用编号
    * @param forumId 论坛Id
    * @param systemUpdateUserId 
    * @param systemVersion 版本号
    * @return
    */
    Boolean deleteByForumId(String appId, String forumId , String systemUpdateUserId);
    
    /**
     * 当日论坛话题数量
     * 
     * @param appId 应用编号
     * @param forumId 论坛Id
     * @param topicId 话题id
     * @return Integer 当日话题数量统计
     */
    Integer countForToday(String appId, String forumId, String topicId);
    
    /**
     * 查询所有话题论坛列表
     * 
     * @param appId 应用编号
     * @param forumId 论坛id
     * @param topicId 话题id
     * @return List<TopicForum> 话题论坛关联列表
     */
    List<TopicForum> allTopicForumList(String appId, String forumId, String topicId);

    
}
