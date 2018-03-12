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
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.mapper.TopicTipMapper;
import com.nowui.cloud.sns.topic.repository.TopicTipRepository;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.sns.topic.view.TopicTipView;
import com.nowui.cloud.util.Util;

/**
 * 话题提醒业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicTipServiceImpl extends SuperServiceImpl<TopicTipMapper, TopicTip, TopicTipRepository, TopicTipView> implements TopicTipService {
    
    public static final String TOPIC_TIP_ID_LIST_BY_TOPIC_ID = "topic_tip_id_list_by_topic_id_";

    @Override
    public Integer countForAdmin(String appId, String topicId, String memberId) {
        Integer count = count(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.MEMBER_ID, memberId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicTip> listForAdmin(String appId, String topicId, String memberId, Integer pageIndex, Integer pageSize) {
        List<TopicTip> topicTipList = list(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.MEMBER_ID, memberId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicTip.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicTipList;
    }

    @Override
    public List<TopicTipView> listByTopicId(String topicId) {
    	
    	Criteria criteria = Criteria.where(TopicTipView.TOPIC_ID).is(topicId)
                .and(TopicTipView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicTipView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicTipView> topicTipList = list(query, sort);

        return topicTipList;
    }

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicTipView> topicTipList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicTipList)) {
            topicTipList.stream().forEach(topicTip -> delete(topicTip.getTopicTipId(), systemRequestUserId, topicTip.getSystemVersion()));
            
        }
        
    }

    @Override
    public List<TopicTip> batchSave(String appId, String topicId, List<TopicTip> topicTipList, String systemRequestUserId) {
        List<String> topicTipIdList = new ArrayList<String>();
        ArrayList<TopicTip> theReturnTopicTipList = new ArrayList<>();
        
        if (!Util.isNullOrEmpty(topicTipList)) {
            for (TopicTip topicTip : topicTipList) {
                topicTip.setTopicId(topicId);
                topicTip.setAppId(appId);
                String topicTipId = Util.getRandomUUID();
                
                TopicTip result = save(topicTip, topicTipId, systemRequestUserId);
                theReturnTopicTipList.add(result);
                topicTipIdList.add(topicTipId);
            }
        }
        return theReturnTopicTipList;
    }

}