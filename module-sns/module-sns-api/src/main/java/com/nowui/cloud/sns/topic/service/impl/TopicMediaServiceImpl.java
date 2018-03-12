package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.mapper.TopicMediaMapper;
import com.nowui.cloud.sns.topic.repository.TopicMediaRepository;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.view.TopicMediaView;
import com.nowui.cloud.util.Util;

/**
 * 话题多媒体业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicMediaServiceImpl extends BaseServiceImpl<TopicMediaMapper, TopicMedia, TopicMediaRepository, TopicMediaView> implements TopicMediaService {
    
    public static final String TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID = "topic_media_id_list_by_topic_id_";

    @Override
    public Integer countForAdmin(String appId, String topicId, String topicMedia, String topicMediaType) {
        Integer count = count(
                new BaseWrapper<TopicMedia>()
                        .eq(TopicMedia.APP_ID, appId)
                        .likeAllowEmpty(TopicMedia.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_FILE_ID, topicMedia)
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
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_FILE_ID, topicMedia)
                        .likeAllowEmpty(TopicMedia.TOPIC_MEDIA_TYPE, topicMediaType)
                        .eq(TopicMedia.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicMedia.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicMediaList;
    }
    
    @Override
    public List<TopicMediaView> listByTopicId(String topicId) {
    	
    	Criteria criteria = Criteria.where(TopicMediaView.TOPIC_ID).is(topicId)
                .and(TopicMediaView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicMediaView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicMediaView> topicMediaList = list(query, sort);

        return topicMediaList;
    }
    
    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicMediaView> topicMediaList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicMediaList)) {
            topicMediaList.stream().forEach(topicMedia -> delete(topicMedia.getTopicMediaId(), systemRequestUserId, topicMedia.getSystemVersion()));
        }
        
    }

    @Override
    public void batchSave(String appId, String topicId, List<TopicMedia> topicMediaList,
            String systemRequestUserId) {
        List<String> topicMediaIdList = new ArrayList<String>();
        
        if (!Util.isNullOrEmpty(topicMediaList)) {
            for (TopicMedia topicMedia : topicMediaList) {
                topicMedia.setTopicId(topicId);
                topicMedia.setAppId(appId);
                String topicMediaId = Util.getRandomUUID();
                
                save(topicMedia, topicMediaId, systemRequestUserId);
                
                topicMediaIdList.add(topicMediaId);
            }
        }
    }

}