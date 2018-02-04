package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.mapper.TopicMediaMapper;
import com.nowui.cloud.sns.topic.repository.TopicMediaRepository;
import com.nowui.cloud.sns.topic.router.TopicMediaRouter;
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
public class TopicMediaServiceImpl extends SuperServiceImpl<TopicMediaMapper, TopicMedia, TopicMediaRepository, TopicMediaView> implements TopicMediaService {
    
    public static final String TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID = "topic_media_id_list_by_topic_id_";

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
    public List<TopicMediaView> listByTopicId(String topicId) {
        // 查询缓存
        List<String> topicMediaIdList = (List<String>) redisTemplate.opsForValue().get(TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID + topicId);
        
        //TODO 先注释掉,固定返回null,回来再调试
//        if (topicMediaIdList == null) {
//            List<TopicMediaView> topicMediaList = list(
//                    new BaseWrapper<TopicMediaView>()
//                            .eq(TopicMedia.TOPIC_ID, topicId)
//                            .eq(TopicMedia.SYSTEM_STATUS, true)
//                            .orderAsc(Arrays.asList(TopicMedia.TOPIC_MEDIA_SORT))
//            );
//            
//            // 缓存话题多媒体编号列表
//            topicMediaIdList = topicMediaList.stream().map(topicMedia -> topicMedia.getTopicMediaId()).collect(Collectors.toList());
//            redisTemplate.opsForValue().set(TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID + topicId, topicMediaIdList);
//            
//            return topicMediaList;
//        } 
        
//        return topicMediaIdList.stream().map(topicMediaId -> find(topicMediaId)).collect(Collectors.toList());
        return null;
    }
    
    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicMediaView> topicMediaList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicMediaList)) {
            topicMediaList.stream().forEach(topicMedia -> delete(topicMedia.getTopicMediaId(), appId, TopicMediaRouter.TOPIC_MEDIA_V1_DELETE, systemRequestUserId, topicMedia.getSystemVersion()));
        }
        
        // 清空缓存
        redisTemplate.delete(TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID + topicId);
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
                
                save(topicMedia, appId, TopicMediaRouter.TOPIC_MEDIA_V1_SAVE, topicMediaId, systemRequestUserId);
                
                topicMediaIdList.add(topicMediaId);
            }
        }
        // 缓存话题多媒体编号列表
        redisTemplate.opsForValue().set(TOPIC_MEDIA_ID_LIST_BY_TOPIC_ID + topicId, topicMediaIdList);
    }

}