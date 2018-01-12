package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserUnbookmarkMapper;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
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
	public TopicUserUnbookmark findUnBookMark(String appId, String topicId, String userId) {
		List<TopicUserUnbookmark> topicUserUnbookmarkList = list(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserUnbookmark.USER_ID, userId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnbookmark.SYSTEM_CREATE_TIME))
        );

		if (topicUserUnbookmarkList != null || topicUserUnbookmarkList.size() == 0) {
			return null;
		}
        return topicUserUnbookmarkList.get(0);
	}

	@Override
	public List<TopicUserUnbookmark> allUnBookMarkListByTopic(String appId, String topicId) {
		List<TopicUserUnbookmark> topicUserUnbookmarkList = list(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnbookmark.SYSTEM_CREATE_TIME))
        );

        return topicUserUnbookmarkList;
	}

}