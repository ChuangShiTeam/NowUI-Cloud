package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.mapper.TopicCommentMapper;
import com.nowui.cloud.sns.topic.repository.TopicCommentRepository;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
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
    public Integer countForAdmin(String appId, String memberId, String topicId, String topicCommentContent, String topicReplayMemberId, String topicReplyCommentId) {
        Integer count = count(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_MEMBER_ID, topicReplayMemberId)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_COMMENT_ID, topicReplyCommentId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicComment> listForAdmin(String appId, String memberId, String topicId, String topicCommentContent, String topicReplayMemberId, String topicReplyCommentId, Integer pageIndex, Integer pageSize) {
        List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_MEMBER_ID, topicReplayMemberId)
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
    	
    	Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).is(topicId)
                .and(TopicCommentView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    	
    }
    
	@Override
	public List<TopicCommentView> listByTopicId(String topicId) {
		
		Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).is(topicId)
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
    	
    	Criteria criteria = Criteria.where(TopicCommentView.TOPIC_ID).is(topicId)
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
    	
    	Criteria criteria = Criteria.where(TopicCommentView.APP_ID).is(appId)
                .and(TopicCommentView.TOPIC_ID).is(topicId)
                .and(TopicCommentView.TOPIC_COMMENT_ID).nin(excludeCommentIdList)
                .and(TopicCommentView.SYSTEM_CREATE_TIME).lte(systemCreateTime.getTime())
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
        	topicCommentList.stream().forEach(topicComment -> delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion()));
        }
    }

    @Override
    public Boolean save(String appId, String topicId, String memberId, TopicComment topicComment, String systemRequestUserId) {
        //TODO 不用缓存,所以不用这个方法 
    	return null;
    }


}