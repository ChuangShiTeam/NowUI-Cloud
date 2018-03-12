package com.nowui.cloud.sns.topic.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserUnbookmarkMapper;
import com.nowui.cloud.sns.topic.repository.TopicUserUnbookmarkRepository;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.view.TopicUserUnbookmarkView;
import com.nowui.cloud.util.Util;

/**
 * 话题用户取消收藏关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserUnbookmarkServiceImpl extends BaseServiceImpl<TopicUserUnbookmarkMapper, TopicUserUnbookmark, TopicUserUnbookmarkRepository, TopicUserUnbookmarkView> implements TopicUserUnbookmarkService {

    @Override
    public Integer countForAdmin(String appId, String topicId, String memberId) {
        Integer count = count(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserUnbookmark.MEMBER_ID, memberId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserUnbookmark> listForAdmin(String appId, String topicId, String memberId, Integer pageIndex, Integer pageSize) {
        List<TopicUserUnbookmark> topicUserUnbookmarkList = list(
                new BaseWrapper<TopicUserUnbookmark>()
                        .eq(TopicUserUnbookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnbookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserUnbookmark.MEMBER_ID, memberId)
                        .eq(TopicUserUnbookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnbookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserUnbookmarkList;
    }

	@Override
	public TopicUserUnbookmarkView findByTopicIdAndMemberId(String topicId, String mmeberId) {
		
		Criteria criteria = Criteria.where(TopicUserUnbookmarkView.TOPIC_ID).is(topicId)
                .and(TopicUserUnbookmarkView.MEMBER_ID).is(mmeberId)
                .and(TopicUserUnbookmarkView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicUserUnbookmarkView> topicUserUnbookmarkList = list(query);
        if (Util.isNullOrEmpty(topicUserUnbookmarkList)) {
			return null;
		}
        return topicUserUnbookmarkList.get(0);
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
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicUserUnbookmark> topicUserUnbookmarkList = listByTopicId(topicId);
        
        if (Util.isNullOrEmpty(topicUserUnbookmarkList)) {
            return;
        }
        topicUserUnbookmarkList.stream().forEach(topicUserUnbookmark -> delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion()));
        
    }

    @Override
    public TopicUserUnbookmark deleteByTopicIdAndMemberId(String topicId, String memberId, String appId, String systemRequestUserId) {
        TopicUserUnbookmarkView topicUserUnbookmark = findByTopicIdAndMemberId(topicId, memberId);
        
        if (Util.isNullOrEmpty(topicUserUnbookmark)) {
            return null;
        }
        
        TopicUserUnbookmark result = delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion());
        
        if (result != null) {
			return result;
		}else {
	        return null;
		}
    }
}