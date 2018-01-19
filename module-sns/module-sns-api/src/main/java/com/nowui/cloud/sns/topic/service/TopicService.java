package com.nowui.cloud.sns.topic.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.Topic;

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
     * @param topicForumId 论坛编号
     * @param topicSummary 动态
     * @param userId 用户编号
     * @param latitude 经度
     * @param longtitude 纬度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param boolean2 置顶
     * @param boolean3 是否推荐
     * @param integer2 置顶级别
     * @return Integer 话题信息统计
     */
    Integer countForAdmin(String appId, String topicForumId, String topicSummary, String userId, String topicLocation, Boolean topicIsLocation);

    /**
     * 话题信息列表
     *
     * @param appId 应用编号
     * @param topicForumId 论坛编号
     * @param topicSummary 动态
     * @param userId 用户编号
     * @param latitude 经度
     * @param longtitude 纬度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param topicIsTop 置顶
     * @param topicIsRecommend 是否推荐
     * @param topTopLevel 置顶级别
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Topic> 话题信息列表
     */
    List<Topic> listForAdmin(String appId, String topicForumId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topTopLevel, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据forumId查找全部话题信息
     * @param body Topic对象
     * @return List<Topic> 话题信息列表
     */
    List<Topic> allTopicListByForumId(Topic body);

    /**
     * 根据用户编号查找所有topic记录
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param pageIndex 分页开始页数
     * @param pageSize 分页记录数
     * @return List<Topic> 话题信息列表
     */
	List<Topic> listByUserId(String appId, String userId, Integer pageIndex, Integer pageSize);
	
	/**
     * 根据userId查找全部话题信息
     * @param body Topic对象
     * @return List<Topic> 话题信息列表
     */
    List<Topic> allTopicListByUserId(Topic body);
    
    /**
     * 根据topicId查询话题详情
     * 
     * @param body
     * @return
     */
    Topic findTheTopicDetails(Topic body);


    /**
     * 根据userId的list集合使用in方法统计所有话题信息数量
     * (根据我关注的人的用户编号的list 统计话题数量)
     * 
     * @param appId 应用编号
     * @param userIdList 用户编号列表
     * @return
     */
    Integer countByUserIdList(String appId, List<String> userIdList);
    
    /**
     * 根据userId的list集合使用in方法查询所有话题信息
     * (根据我关注的人的用户编号的list 查询所有话题信息)
     * 
     * @param appId 应用编号
     * @param userIdList 用户编号列表
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<Topic> listByUserIdList(String appId, List<String> userIdList, Integer pageIndex, Integer pageSize);

    /**
     * 处理要返回的话题列表信息(用户信息,收藏数,点赞数,是否关注等)
     * 
     * @param body 使用前端带来的system参数
     * @param topicList 要处理的话题列表
     * @return List<Topic> 处理后的话题列表
     */
    List<Topic> handleTopic(Topic body, List<Topic> topicList);

}
