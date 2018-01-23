package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserUnbookmarkMapper;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.util.Util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题用户取消收藏关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserUnbookmarkServiceImpl extends BaseServiceImpl<TopicUserUnbookmarkMapper, TopicUserUnbookmark> implements TopicUserUnbookmarkService {

    @Override
    public Integer countForAdmin(String appId, String topicId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserUnbookmark.USER_ID, userId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserUnbookmark> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicUserUnbookmark> topicUserUnbookmarkList = list(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserUnbookmark.USER_ID, userId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnbookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserUnbookmarkList;
    }

	@Override
	public TopicUserUnbookmark findByTopicIdAndUserId(String topicId, String userId) {
		TopicUserUnbookmark topicUserUnbookmark = find(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .eq(TopicUserUnbookmark.USER_ID, userId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
        );

        return topicUserUnbookmark;
	}

	@Override
	public List<TopicUserUnbookmark> listByTopicId(String topicId) {
		List<TopicUserUnbookmark> topicUserUnbookmarkList = list(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnbookmark.SYSTEM_CREATE_TIME))
        );

        return topicUserUnbookmarkList;
	}

    @Override
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicUserUnbookmark> topicUserUnbookmarkList = listByTopicId(topicId);
        
        if (Util.isNullOrEmpty(topicUserUnbookmarkList)) {
            return;
        }
        
        topicUserUnbookmarkList.stream().forEach(topicUserUnbookmark -> delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion()));
        
    }

    @Override
    public Boolean deleteByTopicIdAndUserId(String topicId, String userId, String systemRequestUserId) {
        TopicUserUnbookmark topicUserUnbookmark = findByTopicIdAndUserId(topicId, userId);
        
        if (Util.isNullOrEmpty(topicUserUnbookmark)) {
            return true;
        }
        
        Boolean result = delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion());
        
        return result;
    }

}