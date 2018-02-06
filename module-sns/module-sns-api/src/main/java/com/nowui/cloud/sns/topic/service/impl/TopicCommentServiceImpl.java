package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.mapper.TopicCommentMapper;
import com.nowui.cloud.sns.topic.repository.TopicCommentRepository;
import com.nowui.cloud.sns.topic.router.TopicCommentRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 话题评论业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicCommentServiceImpl extends SuperServiceImpl<TopicCommentMapper, TopicComment, TopicCommentRepository, TopicCommentView> implements TopicCommentService {

    public static final String TOPIC_COMMENT_COUNT_BY_TOPIC_ID = "topic_comment_count_by_topic_id_";
    
    @Override
    public Integer countForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId) {
        Integer count = count(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.USER_ID, userId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLAY_USER_ID, topicReplayUserId)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_COMMENT_ID, topicReplyCommentId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicComment> listForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId, Integer pageIndex, Integer pageSize) {
        List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.USER_ID, userId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLAY_USER_ID, topicReplayUserId)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_COMMENT_ID, topicReplyCommentId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentList;
    }
    
    @Override
    public Integer countByTopicId(String topicId) {
//        Integer count = (Integer) redisTemplate.opsForValue().get(TOPIC_COMMENT_COUNT_BY_TOPIC_ID);
//        
//        if (count == null) {
//            count = count(
//                    new BaseWrapper<TopicComment>()
//                            .eq(TopicComment.TOPIC_ID, topicId)
//                            .eq(TopicComment.SYSTEM_STATUS, true)
//            );
//            // 更新话题评论数缓存
//            redisTemplate.opsForValue().set(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId, count);
//        }
//        
//        return count;
    	
    	Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicCommentView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    	
    }
    
	@Override
	public List<TopicCommentView> listByTopicId(String topicId) {
//		List<TopicComment> topicCommentList = list(
//                new BaseWrapper<TopicComment>()
//                        .eq(TopicComment.TOPIC_ID, topicId)
//                        .eq(TopicComment.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME))
//        );
//
//        return topicCommentList;
		
		Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicCommentView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicCommentView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicCommentView> topicCommentList = list(query, sort);

        return topicCommentList;
	}

    @Override
    public List<TopicCommentView> listByTopicId(String topicId, Integer pageIndex, Integer pageSize) {
//        List<TopicComment> topicCommentList = list(
//                new BaseWrapper<TopicComment>()
//                        .eq(TopicComment.TOPIC_ID, topicId)
//                        .eq(TopicComment.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
//                pageIndex,
//                pageSize
//        );
//
//        return topicCommentList;
    	
    	
    	Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicCommentView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicCommentView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicCommentView> topicCommentList = list(query, sort, pageIndex, pageSize);

        return topicCommentList;
    }
    
    @Override
	public List<TopicCommentView> listByTopicId(String appId, String topicId, List<String> excludeCommentIdList, Date systemCreateTime,
			Integer pageIndex, Integer pageSize) {
//    	List<TopicComment> topicCommentList = list(
//                new BaseWrapper<TopicComment>()
//                        .eq(TopicComment.TOPIC_ID, topicId)
//                        .notIn(TopicComment.TOPIC_COMMENT_ID, excludeCommentIdList)
//                        .eq(TopicComment.SYSTEM_STATUS, true)
//                        .le(TopicComment.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(systemCreateTime))
//                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
//                pageIndex,
//                pageSize
//        );
//
//        return topicCommentList;
    	
    	Criteria criteria = Criteria.where(TopicCommentView.APP_ID).is(appId)
                .and(TopicCommentView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicCommentView.TOPIC_COMMENT_ID).nin(excludeCommentIdList)
                .and(TopicCommentView.SYSTEM_CREATE_TIME).lte(DateUtil.getDateTimeString(systemCreateTime))
                .and(TopicCommentView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicCommentView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicCommentView> topicCommentList = list(query, sort, pageIndex, pageSize);

        return topicCommentList;
	}
    

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicCommentView> topicCommentList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicCommentList)) {
//            topicCommentList.stream().forEach(topicComment -> delete(topicComment.getTopicCommentId(), appId, TopicCommentRouter.TOPIC_COMMENT_V1_DELETE, systemRequestUserId, topicComment.getSystemVersion()));
        	topicCommentList.stream().forEach(topicComment -> delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion()));
        }
//        redisTemplate.delete(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId);
    }

    @Override
    public Boolean save(String appId, String topicId, String userId, TopicComment topicComment, String systemRequestUserId) {
        //TODO 不用缓存,所以不用这个方法 
//        topicComment.setAppId(appId);
//        topicComment.setUserId(userId);
//        topicComment.setTopicId(topicId);
//        
//        Boolean result = save(topicComment, appId, TopicCommentRouter.TOPIC_COMMENT_V1_SAVE, Util.getRandomUUID(), systemRequestUserId);
//        
//        if (result) {
//            // 更新话题评论数缓存
//            Integer count = countByTopicId(topicId);
//            redisTemplate.opsForValue().set(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId, (count + 1));
//        }
//        return result;
    	return null;
    }


}