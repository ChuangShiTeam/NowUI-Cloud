package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserBookmarkMapper;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.util.Util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题收藏业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserBookmarkServiceImpl extends BaseServiceImpl<TopicUserBookmarkMapper, TopicUserBookmark> implements TopicUserBookmarkService {

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
	public TopicUserBookmark findByTopicIdAndUserId(String topicId, String userId) {
		TopicUserBookmark topicUserBookmark = find(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.TOPIC_ID, topicId)
                        .eq(TopicUserBookmark.USER_ID, userId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
        );

        return topicUserBookmark;
	}

    @Override
    public Integer countByTopicId(String topicId) {
        Integer count = (Integer) redis.opsForValue().get(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId);
        
        if (count == null) {
            count = count(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.TOPIC_ID, topicId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
            );
            
            redis.opsForValue().set(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId, count);
        }
        
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
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicUserBookmark> topicUserBookmarkList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserBookmarkList)) {
            topicUserBookmarkList.stream().forEach(topicUserBookmark -> delete(topicUserBookmark.getTopicUserBookmarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion()));
        }
        redis.delete(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId);
    }

    @Override
    public Boolean save(String appId, String topicId, String userId, String systemRequestUserId) {
        TopicUserBookmark topicUserBookmark = new TopicUserBookmark();
        topicUserBookmark.setAppId(appId);
        topicUserBookmark.setTopicId(topicId);
        topicUserBookmark.setUserId(userId);
        
        Boolean result = save(topicUserBookmark, Util.getRandomUUID(), systemRequestUserId);
        
        if (result) {
            // 更新话题收藏数缓存
            Integer count = countByTopicId(topicId);
            redis.opsForValue().set(TOPIC_USER_BOOKMARK_COUNT_BY_TOPIC_ID + topicId, (count + 1));
        }
        return result;
    }

}