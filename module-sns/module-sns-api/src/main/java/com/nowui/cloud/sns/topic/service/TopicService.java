package com.nowui.cloud.sns.topic.service;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.view.TopicView;

/**
 * 话题信息业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicService extends BaseService<Topic, TopicView> {

    /**
     * 话题信息统计
     *
     * @param appId 应用编号
     * @param topicSummary 动态
     * @param memberId 会员编号
     * @param latitude 纬度
     * @param longtitude 经度
     * @param topicLocation 位置
     * @param topicIsLocation 是否有位置
     * @param boolean2 置顶
     * @param boolean3 是否推荐
     * @param integer2 置顶级别
     * @return Integer 话题信息统计
     */
    Integer countForAdmin(String appId, String topicSummary, String memberId, String topicLocation, Boolean topicIsLocation);
    
    

    /**
     * 话题信息列表
     *
     * @param appId 应用编号
     * @param topicSummary 动态
     * @param memberId 会员编号
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
    List<Topic> listForAdmin(String appId, String topicSummary, String memberId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topicTopLevel, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据forumId查找全部话题信息
     * 
     * @param forumId 论坛编号
     * @param memberId 会员编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Topic> 话题信息列表
     */
    List<TopicView> listByForumId(String appId, String forumId, String memberId, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户编号查找所有topic记录
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param pageIndex 分页开始页数
     * @param pageSize 分页记录数
     * @return List<Topic> 话题信息列表
     */
	List<Topic> listByMemberId(String appId, String memberId, Integer pageIndex, Integer pageSize);
	
	/**
	 * 根据memberId查找动态数量(使用redis)
	 * 
	 * @param memberId 会员编号
	 */
	Integer countTopicByMemberIdWithRedis(String appId, String memberId);
	
	/**
	 * 根据用户id查找动态数量
	 * 
	 * @param memberId 会员编号
	 */
	Integer countTopicByMemberId(String memberId);
	
	/**
     * 根据Topic查找全部话题信息
     * @param body Topic对象
     * @return List<Topic> 话题信息列表
     */
    List<Topic> allTopicListByMemberId(Topic body);
    
    /**
     * 根据topicId查询话题详情
     * 
     * @param topicId 话题编号
     * @param memberId 会员编号
     * @return Topic 话题详情
     */
    TopicView findDetailByTopicIdAndMemberId(String topicId, String memberId);

    /**
     * 根据userId的list集合使用in方法统计所有话题信息数量
     * (根据我关注的人的用户编号的list 统计话题数量)
     * 
     * @param appId 应用编号
     * @param memberIdList 用户编号列表
     * @return
     */
    Integer countByMemberIdList(String appId, List<String> memberIdList);
    
    /**
     * 根据userId的list集合查询所有话题信息
     * 
     * @param appId 应用编号
     * @param memberIdList 用户编号列表
     * @param excludeTopicIdList 排除的话题编号列表
     * @param systemCreateTime 创建时间
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<TopicView> listByMemberIdList(String appId, List<String> memberIdList, List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据memberId的list集合查询所有话题相关
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberIdList 用户编号列表
     * @param excludeTopicIdList 排除的话题编号列表
     * @param systemCreateTime 创建时间
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<TopicView> listDetailByMemberIdList(String appId, String memberId, List<String> memberIdList, List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据topicId的list集合查询所有话题信息
     * 
     * @param topicIdList 话题编号列表
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<Topic> listByTopicIdList(List<String> topicIdList, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 根据topicId的list集合查询所有话题信息(不分页)
     * 
     * @param topicIdList 话题编号列表
     * @param pageIndex 从第几条开始
     * @param pageSize 取多少条
     * @return topic列表
     */
    List<TopicView> listByTopicIdList(List<String> topicIdList);
    
    /**
     * 根据topicId的list集合查询所有话题相关的信息(其中调用了listByTopicIdList方法)
     * 
     * @param memberId
     * @param topicIdList
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Topic> listDetailByTopicIdList(String memberId, List<String> topicIdList, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据topicId的list集合查询所有话题相关的信息(其中调用了listByTopicIdList方法)
     * (不分页)
     * 
     * @param memberId
     * @param topicIdList
     * @return
     */
    List<TopicView> listDetailByTopicIdList(String memberId, List<String> topicIdList);
    
    /**
     * 根据话题编号删除话题相关信息
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param systemRequestUserId 请求用户编号
     * @param systemVersion 版本号
     * @return
     */
    Topic deleteByTopicId(String appId, String topicId, String systemRequestUserId, Integer systemVersion);
    
    /**
     * 新增topic记录,并往redis中增加动态数
     * 
     * @param entity 要保存的实体类
     * @param id 话题Id
     * @param systemCreateUserId 创建者id
     * @return
     */
    Boolean saveWithRedis(Topic entity, String id, String systemCreateUserId);
    
    /**
     * 热门动态
     * @param excludeTopicIdList 被排除的动态id(也就是已经查看过的)
     * @param systemCreateTime 最后一次查询的最后一个动态的创建时间
     * @param pageIndex 从第几页开始
     * @param pageSize 查多少列
     * @return List<TopicView>
     */
    List<TopicView> hotTopicList(List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize);
    
    /**
     * 全部人的动态数量
     * 
     */
    Integer countAllUserTopic();
    
    
}
