package com.nowui.cloud.sns.topic.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;

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
     * @param topicSummary 动态
     * @param userId 用户编号
     * @param latitude 纬度
     * @param longtitude 经度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param boolean2 置顶
     * @param boolean3 是否推荐
     * @param integer2 置顶级别
     * @return Integer 话题信息统计
     */
    Integer countForAdmin(String appId, String topicSummary, String userId, String topicLocation, Boolean topicIsLocation);

    /**
     * 话题信息列表
     *
     * @param appId 应用编号
     * @param topicSummary 动态
     * @param userId 用户编号
     * @param latitude 纬度
     * @param longtitude 经度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param topicIsTop 置顶
     * @param topicIsRecommend 是否推荐
     * @param topicTopLevel 置顶级别
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Topic> 话题信息列表
     */
    List<Topic> listForAdmin(String appId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topicTopLevel, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据forumId查找全部话题信息
     * @param body TopicForum对象
     * @return List<Topic> 话题信息列表
     */
    List<Topic> allTopicListByForumId(TopicForum body);

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
     * 根据userId的list集合查询所有话题信息
     * 
     * @param appId 应用编号
     * @param userIdList 用户编号列表
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<Topic> listByUserIdList(String appId, List<String> userIdList, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据userId的list集合查询所有话题相关
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userIdList 用户编号列表
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<Topic> listDetailByUserIdList(String appId, String userId, List<String> userIdList, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号删除话题相关信息
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param systemRequestUserId 请求用户编号
     * @param systemVersion 版本号
     * @return
     */
    Boolean deleteByTopicId(String appId, String topicId, String systemRequestUserId, Integer systemVersion);

}
