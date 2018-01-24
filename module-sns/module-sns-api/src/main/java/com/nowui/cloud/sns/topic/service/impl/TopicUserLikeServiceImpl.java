package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.mapper.TopicUserLikeMapper;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.util.Util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 点赞话题关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserLikeServiceImpl extends BaseServiceImpl<TopicUserLikeMapper, TopicUserLike> implements TopicUserLikeService {

    public static final String TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID = "topic_user_like_count_by_topic_id_";
    
    @Override
    public Integer countForAdmin(String appId, String userId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserLike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserLikeList;
    }

	@Override
	public TopicUserLike findByTopicIdAndUserId(String topicId, String userId) {
		TopicUserLike topicUserLike = find(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.USER_ID, userId)
                        .eq(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
        );

        return topicUserLike;
	}
	
	@Override
    public Integer countByTopicId(String topicId) {
	    
	    Integer count = (Integer) redis.opsForValue().get(TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID + topicId);
	    
	    if (count == null) {
	        count = count(
	                new BaseWrapper<TopicUserLike>()
	                        .eq(TopicUserLike.TOPIC_ID, topicId)
	                        .eq(TopicUserLike.SYSTEM_STATUS, true)
	        );
	        redis.opsForValue().set(TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID + topicId, count);
	   }
	    
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
	public List<TopicUserLike> listByTopicIdHavePage(String topicId, Integer pageIndex, Integer pageSize) {
		List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserLikeList;
	}

    @Override
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicUserLike> topicUserLikeList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserLikeList)) {
            topicUserLikeList.stream().forEach(topicUserLike -> delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion()));
        }
        redis.delete(TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID + topicId);
    }

    @Override
    public Boolean save(String appId, String topicId, String userId, String systemRequestUserId) {
        TopicUserLike topicUserLike = new TopicUserLike();
        topicUserLike.setAppId(appId);
        topicUserLike.setTopicId(topicId);
        topicUserLike.setUserId(userId);
        
        Boolean result = save(topicUserLike, Util.getRandomUUID(), systemRequestUserId);
        
        if (result) {
            // 更新话题点赞数缓存
            Integer count = countByTopicId(topicId);
            redis.opsForValue().set(TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID + topicId, (count + 1));
        }
        return result;
    }

    @Override
    public Boolean deleteByTopicIdAndUserId(String topicId, String userId, String systemRequestUserId) {
        TopicUserLike topicUserLike = findByTopicIdAndUserId(topicId, userId);
        
        if (Util.isNullOrEmpty(topicUserLike)) {
            return true;
        }
        
        Integer count = countByTopicId(topicId);
        
        Boolean result = delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion());
        
        if (result) {
            // 更新话题点赞数缓存
            redis.opsForValue().set(TOPIC_USER_LIKE_COUNT_BY_TOPIC_ID + topicId, (count - 1));
        }
        
        return result;
    }

	

}