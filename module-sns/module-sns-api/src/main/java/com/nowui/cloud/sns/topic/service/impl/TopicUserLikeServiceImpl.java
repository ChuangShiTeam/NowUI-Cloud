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
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.mapper.TopicUserLikeMapper;
import com.nowui.cloud.sns.topic.repository.TopicUserLikeRepository;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.util.Util;

/**
 * 点赞话题关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserLikeServiceImpl extends BaseServiceImpl<TopicUserLikeMapper, TopicUserLike, TopicUserLikeRepository, TopicUserLikeView> implements TopicUserLikeService {

    public static final String TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID = "topic_user_like_count_by_topic_id_";
    
    @Override
    public Integer countForAdmin(String appId, String memberId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserLike> listForAdmin(String appId, String memberId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserLikeList;
    }

	@Override
	public TopicUserLikeView findByTopicIdAndMemberId(String topicId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicUserLikeView.TOPIC_ID).is(topicId)
                .and(TopicUserLikeView.MEMBER_ID).is(memberId)
                .and(TopicUserLikeView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicUserLikeView> topicUserLikeList = list(query);
        
        if (Util.isNullOrEmpty(topicUserLikeList)) {
			return null;
		}

        return topicUserLikeList.get(0);
	}
	
	@Override
    public Integer countByTopicId(String topicId) {
		
		Criteria criteria = Criteria.where(TopicUserLike.TOPIC_ID).is(topicId)
                .and(TopicUserLike.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

	@Override
	public List<TopicUserLike> listByTopicId(String topicId) {
		List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME))
        );

        return topicUserLikeList;
	}
	
	@Override
	public List<TopicUserLikeView> listByTopicIdHavePage(String topicId, Integer pageIndex, Integer pageSize) {
		
		Criteria criteria = Criteria.where(TopicUserLikeView.TOPIC_ID).is(topicId)
                .and(TopicUserLikeView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicUserLikeView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicUserLikeView> topicUserLikeList = list(query, sort, pageIndex, pageSize);

        return topicUserLikeList;
		
	}

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicUserLike> topicUserLikeList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserLikeList)) {
        	 topicUserLikeList.stream().forEach(topicUserLike -> delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion()));
        	  
        }
    }

    @Override
    public TopicUserLike save(String appId, String topicId, String memberId, String systemRequestUserId) {
        TopicUserLike topicUserLike = new TopicUserLike();
        topicUserLike.setAppId(appId);
        topicUserLike.setTopicId(topicId);
        topicUserLike.setMemberId(memberId);
        
        TopicUserLike result = save(topicUserLike, Util.getRandomUUID(), systemRequestUserId);
        
        return result;
    }

    @Override
    public TopicUserLike deleteByTopicIdAndMemberId(String topicId, String memberId, String appId, String systemRequestUserId) {
        TopicUserLikeView topicUserLike = findByTopicIdAndMemberId(topicId, memberId);
        
        if (Util.isNullOrEmpty(topicUserLike)) {
            return null;
        }
        
        TopicUserLike result = delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion());
        
        if (result != null) {
        	
        	return result;
        }else {
			return null;
		}
    }
}