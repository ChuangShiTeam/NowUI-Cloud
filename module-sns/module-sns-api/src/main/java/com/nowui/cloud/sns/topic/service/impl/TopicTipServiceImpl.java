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
import com.nowui.cloud.sns.topic.router.TopicTipRouter;
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
        // 查询缓存
//        List<String> topicTipIdList = (List<String>) redisTemplate.opsForValue().get(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId);
        
        //TODO 先注释掉,回来再调试,默认返回null
//        if (topicTipIdList == null) {
//            List<TopicTip> topicTipList = list(
//                    new BaseWrapper<TopicTip>()
//                            .eq(TopicTip.TOPIC_ID, topicId)
//                            .eq(TopicTip.SYSTEM_STATUS, true)
//                            .orderAsc(Arrays.asList(TopicTip.SYSTEM_CREATE_TIME))
//            );
//            
//            // 缓存话题提醒编号列表
//            topicTipIdList = topicTipList.stream().map(topicTip -> topicTip.getTopicTipId()).collect(Collectors.toList());
//            redisTemplate.opsForValue().set(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId, topicTipIdList);
//            
//            return topicTipList;
//        } 
        
//        return topicTipIdList.stream().map(topicTipId -> find(topicTipId)).collect(Collectors.toList());
    	
    	Criteria criteria = Criteria.where(TopicTipView.TOPIC_ID).regex(".*?" + topicId + ".*")
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
//            topicTipList.stream().forEach(topicTip -> delete(topicTip.getTopicTipId(), appId, TopicTipRouter.TOPIC_TIP_V1_DELETE, systemRequestUserId, topicTip.getSystemVersion()));
            topicTipList.stream().forEach(topicTip -> delete(topicTip.getTopicTipId(), systemRequestUserId, topicTip.getSystemVersion()));
            
        }
        
        // 清空缓存
//        redisTemplate.delete(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId);
    }

    @Override
    public void batchSave(String appId, String topicId, List<TopicTip> topicTipList, String systemRequestUserId) {
        List<String> topicTipIdList = new ArrayList<String>();
        
        if (!Util.isNullOrEmpty(topicTipList)) {
            for (TopicTip topicTip : topicTipList) {
                topicTip.setTopicId(topicId);
                topicTip.setAppId(appId);
                String topicTipId = Util.getRandomUUID();
                
//TODO 消息放在 topicMobileController的保存提醒谁看
//                save(topicTip, topicTipId, appId, TopicTipRouter.TOPIC_TIP_V1_SAVE, systemRequestUserId);
                save(topicTip, topicTipId, systemRequestUserId);
                
                topicTipIdList.add(topicTipId);
            }
        }
        // 缓存话题提醒编号列表
//        redisTemplate.opsForValue().set(TOPIC_TIP_ID_LIST_BY_TOPIC_ID + topicId, topicTipIdList);
        
    }

}