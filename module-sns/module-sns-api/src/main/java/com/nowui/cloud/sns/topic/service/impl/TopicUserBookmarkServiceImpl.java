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
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserBookmarkMapper;
import com.nowui.cloud.sns.topic.repository.TopicUserBookmarkRepository;
import com.nowui.cloud.sns.topic.router.TopicUserBookmarkRouter;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.util.Util;

/**
 * 话题收藏业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserBookmarkServiceImpl extends SuperServiceImpl<TopicUserBookmarkMapper, TopicUserBookmark, TopicUserBookmarkRepository, TopicUserBookmarkView> implements TopicUserBookmarkService {

    public static final String TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID = "topic_user_bookmark_count_by_topic_id_";

    @Override
    public Integer countForAdmin(String appId, String topicId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.USER_ID, userId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserBookmark> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicUserBookmark> topicUserBookmarkList = list(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.USER_ID, userId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserBookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserBookmarkList;
    }

	@Override
	public TopicUserBookmarkView findByTopicIdAndUserId(String topicId, String userId) {
//		TopicUserBookmark topicUserBookmark = find(
//                new BaseWrapper<TopicUserBookmark>()
//                        .eq(TopicUserBookmark.TOPIC_ID, topicId)
//                        .eq(TopicUserBookmark.USER_ID, userId)
//                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
//        );
//
//        return topicUserBookmark;
		
		Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicUserBookmarkView.USER_ID).regex(".*?" + userId + ".*")
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicUserBookmarkView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicUserBookmarkView> topicUserBookmarkList = list(query, sort);
        
        if (Util.isNullOrEmpty(topicUserBookmarkList)) {
			return null;
		}

        return topicUserBookmarkList.get(0);
	}

    @Override
    public Integer countByTopicId(String topicId) {
//        Integer count = (Integer) redisTemplate.opsForValue().get(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId);
//        
//        if (count == null) {
//            count = count(
//                new BaseWrapper<TopicUserBookmark>()
//                        .eq(TopicUserBookmark.TOPIC_ID, topicId)
//                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
//            );
//            
//            redisTemplate.opsForValue().set(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId, count);
//        }
//        
//        return count;
    	
    	Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).regex(".*?" + topicId + ".*")
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    	
    }

    @Override
    public List<TopicUserBookmark> listByTopicId(String topicId) {
        List<TopicUserBookmark> topicUserBookmarkList = list(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.TOPIC_ID, topicId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserBookmark.SYSTEM_CREATE_TIME))
        );

        return topicUserBookmarkList;
    }

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicUserBookmark> topicUserBookmarkList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserBookmarkList)) {
            topicUserBookmarkList.stream().forEach(topicUserBookmark -> delete(topicUserBookmark.getTopicUserBookmarkId(), appId, TopicUserBookmarkRouter.TOPIC_USER_BOOKMARK_V1_DELETE, systemRequestUserId, topicUserBookmark.getSystemVersion()));
        }
        redisTemplate.delete(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId);
    }

    @Override
    public Boolean save(String appId, String topicId, String userId, String systemRequestUserId) {
        TopicUserBookmark topicUserBookmark = new TopicUserBookmark();
        topicUserBookmark.setAppId(appId);
        topicUserBookmark.setTopicId(topicId);
        topicUserBookmark.setUserId(userId);
        
        Boolean result = save(topicUserBookmark, Util.getRandomUUID(), appId, TopicUserBookmarkRouter.TOPIC_USER_BOOKMARK_V1_SAVE,systemRequestUserId);
        
        if (result) {
            // 更新话题收藏数缓存
            Integer count = countByTopicId(topicId);
            redisTemplate.opsForValue().set(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId, (count + 1));
        }
        return result;
    }

    @Override
    public Boolean deleteByTopicIdAndUserId(String topicId, String userId, String appId, String systemRequestUserId) {
        TopicUserBookmark topicUserBookmark = findByTopicIdAndUserId(topicId, userId);
        
        if (Util.isNullOrEmpty(topicUserBookmark)) {
            return true;
        }
        // 查询缓存收藏数
        Integer count = countByTopicId(topicId);  
        
        Boolean result = delete(topicUserBookmark.getTopicUserBookmarkId(), appId, TopicUserBookmarkRouter.TOPIC_USER_BOOKMARK_V1_DELETE,systemRequestUserId, topicUserBookmark.getSystemVersion());
        
        if (result) {
            // 更新话题收藏数缓存
        	redisTemplate.opsForValue().set(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId, (count - 1));
        }
        
        return result;
    }

}