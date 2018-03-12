package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.view.TopicMediaView;

import java.util.List;

/**
 * 话题多媒体业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicMediaService extends BaseService<TopicMedia, TopicMediaView> {

    /**
     * 话题多媒体统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param topicMedia 多媒体Id
     * @param topicMediaType 多媒体类型
     * @return Integer 话题多媒体统计
     */
    Integer countForAdmin(String appId, String topicId, String topicMedia, String topicMediaType);
    
    /**
     * 话题多媒体列表
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param topicMedia 多媒体Id
     * @param topicMediaType 多媒体类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicMedia> 话题多媒体列表
     */
    List<TopicMedia> listForAdmin(String appId, String topicId, String topicMedia, String topicMediaType, Integer pageIndex, Integer pageSize);

    /**
     * 根据话题编号查询话题多媒体列表
     * 
     * @param topicId 话题编号
     * @return List<TopicMedia> 话题多媒体列表
     */
	List<TopicMediaView> listByTopicId(String topicId);
	
	/**
	 * 根据话题编号逻辑删除话题多媒体信息
	 * 
	 * @param topicId 话题编号
	 * @param appId 应用编号
	 * @param systemRequestUserId 请求用户编号
	 */
	void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
	
	/**
	 * 批量保存话题多媒体
	 * 
	 * @param appId 应用编号
	 * @param topicId 话题编号
	 * @param topicMediaList 话题多媒体列表
	 * @param systemRequestUserId 请求用户编号
	 */
	void batchSave(String appId, String topicId, List<TopicMedia> topicMediaList, String systemRequestUserId);
}
