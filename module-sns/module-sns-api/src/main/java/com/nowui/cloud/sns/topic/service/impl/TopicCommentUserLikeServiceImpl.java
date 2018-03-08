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
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.mapper.TopicCommentUserLikeMapper;
import com.nowui.cloud.sns.topic.repository.TopicCommentUserLikeRepository;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserLikeView;
import com.nowui.cloud.util.Util;

/**
 * 话题的评论用户点赞业务实现
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Service
public class TopicCommentUserLikeServiceImpl extends SuperServiceImpl<TopicCommentUserLikeMapper, TopicCommentUserLike, TopicCommentUserLikeRepository, TopicCommentUserLikeView> implements TopicCommentUserLikeService {
	
	public static final String TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID = "topic_comment_user_like_count_by_comment_id_";

    @Override
    public Integer countForAdmin(String appId, String commentId, String memberId) {
        Integer count = count(
                new BaseWrapper<TopicCommentUserLike>()
                        .eq(TopicCommentUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserLike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserLike.MEMBER_ID, memberId)
                        .eq(TopicCommentUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicCommentUserLike> listForAdmin(String appId, String commentId, String memberId, Integer pageIndex, Integer pageSize) {
        List<TopicCommentUserLike> topicCommentUserLikeList = list(
                new BaseWrapper<TopicCommentUserLike>()
                        .eq(TopicCommentUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserLike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserLike.MEMBER_ID, memberId)
                        .eq(TopicCommentUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicCommentUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentUserLikeList;
    }

	@Override
	public TopicCommentUserLikeView findTheCommentUserLike(String appId, String commentId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.APP_ID).is(appId)
                .and(TopicCommentUserLikeView.COMMENT_ID).is(commentId)
                .and(TopicCommentUserLikeView.MEMBER_ID).is(memberId)
                .and(TopicCommentUserLikeView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicCommentUserLikeView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicCommentUserLikeView> topicCommentUserLikeList = list(query, sort);
        
        if (Util.isNullOrEmpty(topicCommentUserLikeList)) {
			return null;
		}

        return topicCommentUserLikeList.get(0);
	}

	@Override
	public TopicCommentUserLike deleteByCommentIdAndMemberIdWithRedis(String commentId, String appId, String memberId, String systemRequestUserId) {
		// 先查询点赞表查询记录
		TopicCommentUserLikeView userLike = findTheCommentUserLike(appId, commentId, memberId);
//		// 没有: 返回true
		if (Util.isNullOrEmpty(userLike)) {
			return null;
		}
		// 有: 删除
		TopicCommentUserLike result = delete(userLike.getCommentUserLikeId(), systemRequestUserId, userLike.getSystemVersion());
		
		if (result != null) {
			return result;
		}else {
			return null;
		}
	}

	@Override
	public TopicCommentUserLike save(String appId, String commentId, String memberId, String systemRequestUserId) {
		TopicCommentUserLike body = new TopicCommentUserLike();
		body.setAppId(appId);
		body.setCommentId(commentId);
		body.setMemberId(memberId);
		
		TopicCommentUserLike result = save(body, Util.getRandomUUID(), systemRequestUserId);
		
		return result;
	}

	@Override
	public Integer countByCommentIdWithRedis(String appId, String commentId) {
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.APP_ID).is(appId)
                .and(TopicCommentUserLikeView.COMMENT_ID).is(commentId)
                .and(TopicCommentUserLikeView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
		
	}

	@Override
	public boolean deleteAllCommentLikeByCommentIdWithRedis(String commentId, String appId, String systemRequestUserId) {
		//先从点赞记录表查找所有commentId的点赞记录
		List<TopicCommentUserLikeView> likeList = listByCommentIdWithoutPage(commentId);
		//然后删除所有记录
		if (!Util.isNullOrEmpty(likeList)) {
			likeList.stream().forEach(commentUserLike -> delete(commentUserLike.getCommentUserLikeId(), systemRequestUserId, commentUserLike.getSystemVersion()));
			
		}

		return true;
	}

	@Override
	public List<TopicCommentUserLikeView> listByCommentIdWithoutPage(String commentId) {
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.COMMENT_ID).is(commentId)
                .and(TopicCommentUserLikeView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicCommentUserLikeView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicCommentUserLikeView> topicCommentUserLikeList = list(query, sort);

        return topicCommentUserLikeList;
	}
}