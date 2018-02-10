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
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.mapper.TopicCommentUserLikeMapper;
import com.nowui.cloud.sns.topic.repository.TopicCommentUserLikeRepository;
import com.nowui.cloud.sns.topic.router.TopicCommentUserLikeRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import com.nowui.cloud.util.DateUtil;
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
//		List<TopicCommentUserLike> topicCommentUserLikeList = list(
//                new BaseWrapper<TopicCommentUserLike>()
//                        .likeAllowEmpty(TopicCommentUserLike.COMMENT_ID, commentId)
//                        .likeAllowEmpty(TopicCommentUserLike.USER_ID, userId)
//                        .eq(TopicCommentUserLike.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(TopicCommentUserLike.SYSTEM_CREATE_TIME))
//        );
//		if (topicCommentUserLikeList == null || topicCommentUserLikeList.size() == 0) {
//			return null;
//		}
//        return topicCommentUserLikeList.get(0);
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.APP_ID).is(appId)
                .and(TopicCommentUserLikeView.COMMENT_ID).regex(".*?" + commentId + ".*")
                .and(TopicCommentUserLikeView.MEMBER_ID).regex(".*?" + memberId + ".*")
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
//TODO 后面处理消息  	Boolean delete = delete(userLike.getCommentUserLikeId(), appId, TopicCommentUserLikeRouter.TOPIC_COMMENT_USER_LIKE_V1_DELETE, userId, userLike.getSystemVersion());
		TopicCommentUserLike result = delete(userLike.getCommentUserLikeId(), systemRequestUserId, userLike.getSystemVersion());
		
//	TODO 没有处理点赞数量 	if (delete) {
//			Integer count = countByCommentIdWithRedis(commentId);
//			redisTemplate.opsForValue().set(TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID + commentId, (count - 1));
//		}
		
		
		// TODO 返回不了实体类id,所以不用这个方法删除
//		TopicCommentUserLike deleteresult = delete(
//				new BaseWrapper<TopicCommentUserLike>()
//                .eq(TopicCommentUserUnlike.APP_ID, appId)
//                .eq(TopicCommentUserUnlike.COMMENT_ID, commentId)
//                .eq(TopicCommentUserUnlike.USER_ID, userId)
//                .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
//				, systemRequestUserId
//			);
		
		if (result != null) {
			return result;
		}else {
			return null;
		}
		
	}

	@Override
	public TopicCommentUserLike saveWithRedis(String appId, String commentId, String memberId, String systemRequestUserId) {
		TopicCommentUserLike body = new TopicCommentUserLike();
		body.setAppId(appId);
		body.setCommentId(commentId);
		body.setMemberId(memberId);
		
//		Boolean result = save(body, appId, TopicCommentUserLikeRouter.TOPIC_COMMENT_USER_LIKE_V1_SAVE, Util.getRandomUUID(), systemRequestUserId);
//		
//		if (result) {
//			//往缓存中存一份
//			//先从缓存中取出原来的点赞数量
//	TODO 后面处理点赞数量		Integer count = countByCommentIdWithRedis(commentId);
//			
//			//+1,然后放入缓存
//			redisTemplate.opsForValue().set(TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID + commentId, (count + 1));
//		}
		
		TopicCommentUserLike result = save(body, Util.getRandomUUID(), systemRequestUserId);
		
		return result;
	}

	@Override
	public Integer countByCommentIdWithRedis(String appId, String commentId) {
//		//先从缓存中查询有没有记录
//		Integer count = (Integer) redisTemplate.opsForValue().get(TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID + commentId);
//		
//		//没有就从数据库查询
//		if (count == null) {
//			count = count(
//                new BaseWrapper<TopicCommentUserLike>()
//                        .likeAllowEmpty(TopicCommentUserLike.COMMENT_ID, commentId)
//                        .eq(TopicCommentUserLike.SYSTEM_STATUS, true)
//	        );
//			//查询后,把结果放入缓存
//			redisTemplate.opsForValue().set(TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID + commentId, count);
//		}
//		// 有: 就直接返回
//		return count;
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.APP_ID).is(appId)
                .and(TopicCommentUserLikeView.COMMENT_ID).regex(".*?" + commentId + ".*")
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
//			likeList.stream().forEach(commentUserLike -> delete(commentUserLike.getCommentUserLikeId(), appId, TopicCommentUserLikeRouter.TOPIC_COMMENT_USER_LIKE_V1_SAVE , systemRequestUserId, commentUserLike.getSystemVersion()));
			likeList.stream().forEach(commentUserLike -> delete(commentUserLike.getCommentUserLikeId(), systemRequestUserId, commentUserLike.getSystemVersion()));
			
		}
		//从redis中此commentId的点赞数
//		Boolean delete = redisTemplate.delete(TOPIC_COMMENT_USER_LIKE_COUNT_BY_COMMENT_ID + commentId);

		return true;
	}

	@Override
	public List<TopicCommentUserLikeView> listByCommentIdWithoutPage(String commentId) {
//		List<TopicCommentUserLike> topicCommentUserLikeList = list(
//                new BaseWrapper<TopicCommentUserLike>()
//                        .eq(TopicCommentUserLike.COMMENT_ID, commentId)
//                        .eq(TopicCommentUserLike.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(TopicCommentUserLike.SYSTEM_CREATE_TIME))
//        );
//
//        return topicCommentUserLikeList;
		
		Criteria criteria = Criteria.where(TopicCommentUserLikeView.COMMENT_ID).regex(".*?" + commentId + ".*")
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