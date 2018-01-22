package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.mapper.TopicTipMapper;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.util.Util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 话题提醒业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicTipServiceImpl extends BaseServiceImpl<TopicTipMapper, TopicTip> implements TopicTipService {
    
    public static final String TOPIC_TIP_ID_LIST_BY_TOPIC_ID = "topic_tip_id_list_by_topic_id_";

    @Override
    public Integer countForAdmin(String appId, String topicId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.USER_ID, userId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicTip> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicTip> topicTipList = list(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.USER_ID, userId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicTip.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicTipList;
    }

    @Override
    public List<TopicTip> listByTopicId(String topicId) {
        // 查询缓存
        List<String> topicTipIdList = (List<String>) redis.opsForValue().get(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId);
        
        if (topicTipIdList == null) {
            List<TopicTip> topicTipList = list(
                    new BaseWrapper<TopicTip>()
                            .eq(TopicTip.TOPIC_ID, topicId)
                            .eq(TopicTip.SYSTEM_STATUS, true)
                            .orderAsc(Arrays.asList(TopicTip.SYSTEM_CREATE_TIME))
            );
            
            // 缓存话题提醒编号列表
            topicTipIdList = topicTipList.stream().map(topicTip -> topicTip.getTopicTipId()).collect(Collectors.toList());
            redis.opsForValue().set(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId, topicTipIdList);
            
            return topicTipList;
        } 
        
        return topicTipIdList.stream().map(topicTipId -> find(topicTipId)).collect(Collectors.toList());
    }

    @Override
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicTip> topicTipList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicTipList)) {
            topicTipList.stream().forEach(topicTip -> delete(topicTip.getTopicTipId(), systemRequestUserId, topicTip.getSystemVersion()));
        }
        
        // 清空缓存
        redis.delete(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId);
    }

    @Override
    public void batchSave(String appId, String topicId, List<TopicTip> topicTipList, String systemRequestUserId) {
        List<String> topicTipIdList = new ArrayList<String>();
        
        if (!Util.isNullOrEmpty(topicTipList)) {
            for (TopicTip topicTip : topicTipList) {
                topicTip.setTopicId(topicId);
                topicTip.setAppId(appId);
                String topicTipId = Util.getRandomUUID();
                
                save(topicTip, topicTipId, systemRequestUserId);
                
                topicTipIdList.add(topicTipId);
            }
        }
        // 缓存话题提醒编号列表
        redis.opsForValue().set(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId, topicTipIdList);
        
    }

}