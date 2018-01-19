package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;

import java.util.List;

/**
 * 话题用户收藏关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicUserBookmarkService extends BaseService<TopicUserBookmark> {

    /**
     * 话题用户收藏关联统计
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 用户ID
     * @return Integer 话题用户收藏关联统计
     */
    Integer countForAdmin(String appId, String topicId, String userId);

    /**
     * 话题用户收藏关联列表
     *
     * @param appId 应用编号
     * @param topicId 话题Id
     * @param userId 用户ID
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicUserBookmark> 话题用户收藏关联列表
     */
    List<TopicUserBookmark> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题id和用户id查询单条话题收藏记录
     * 
     * @param appId 应用编号
     * @param topicId 话题id
     * @param userId 用户id
     * @return TopicUserBookmark 单条话题收藏记录
     */
    TopicUserBookmark findTopicUserBookmark(String appId, String topicId, String userId);
    
    /**
     * 根据topicId查找全部话题收藏列表
     * @param appId
     * @param topicId
     * @return
     */
    List<TopicUserBookmark> allListByTopicId(String appId, String topicId);
    
}
