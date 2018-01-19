package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.mapper.TopicMediaMapper;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题多媒体业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicMediaServiceImpl extends BaseServiceImpl<TopicMediaMapper, TopicMedia> implements TopicMediaService {

    @Override
    public Integer countForAdmin(String appId, String topicId, String topicMedia, String topicMediaType) {
        Integer count = count(
                new BaseWrapper<TopicMedia>()
                        .eq(TopicMedia.APP_ID, appId)
                        .likeAllowEmpty(TopicMedia.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA, topicMedia)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_TYPE, topicMediaType)
                        .eq(TopicMedia.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicMedia> listForAdmin(String appId, String topicId, String topicMedia, String topicMediaType, Integer pageIndex, Integer pageSize) {
        List<TopicMedia> topicMediaList = list(
                new BaseWrapper<TopicMedia>()
                        .eq(TopicMedia.APP_ID, appId)
                        .likeAllowEmpty(TopicMedia.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA, topicMedia)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_TYPE, topicMediaType)
                        .eq(TopicMedia.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicMedia.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicMediaList;
    }
    
    @Override
    public List<TopicMedia> listAllMediaByTopicId(String appId, String topicId, String topicMedia, String topicMediaType) {
        List<TopicMedia> topicMediaList = list(
                new BaseWrapper<TopicMedia>()
                        .eq(TopicMedia.APP_ID, appId)
                        .likeAllowEmpty(TopicMedia.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA, topicMedia)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_TYPE, topicMediaType)
                        .eq(TopicMedia.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicMedia.SYSTEM_CREATE_TIME))
        );

        return topicMediaList;
    }

}