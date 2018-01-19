package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicMedia;

import java.util.List;

/**
 * 话题多媒体业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicMediaService extends BaseService<TopicMedia> {

    /**
     * 话题多媒体统计
     *
     * @param appId 应用编号
     * @param topicId 话题id
     * @param topicMedia 多媒体Id
     * @param topicMediaType 多媒体类型
     * @return Integer 话题多媒体统计
     */
    Integer countForAdmin(String appId, String topicId, String topicMedia, String topicMediaType);

    /**
     * 话题多媒体列表
     *
     * @param appId 应用编号
     * @param topicId 话题id
     * @param topicMedia 多媒体Id
     * @param topicMediaType 多媒体类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicMedia> 话题多媒体列表
     */
    List<TopicMedia> listForAdmin(String appId, String topicId, String topicMedia, String topicMediaType, Integer pageIndex, Integer pageSize);

    /**
     * 根据topicId查询所有图片
     * 
     * @param appId 应用编号
     * @param topicId 话题id
     * @param topicMedia 多媒体Id
     * @param topicMediaType 多媒体类型
     * @return List<TopicMedia> 话题多媒体列表
     */
	List<TopicMedia> listAllMediaByTopicId(String appId, String topicId, String topicMedia, String topicMediaType);
}
