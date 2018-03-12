package com.nowui.cloud.sns.topic.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.mapper.TopicUserUnlikeMapper;
import com.nowui.cloud.sns.topic.repository.TopicUserUnlikeRepository;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;
import com.nowui.cloud.util.Util;

/**
 * 话题用户取消点赞关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserUnlikeServiceImpl extends BaseServiceImpl<TopicUserUnlikeMapper, TopicUserUnlike, TopicUserUnlikeRepository, TopicUserUnlikeView> implements TopicUserUnlikeService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicUserUnlike>()
                        .eq(TopicUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnlike.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicUserUnlike.TOPIC_ID, topicId)
                        .eq(TopicUserUnlike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserUnlike> listForAdmin(String appId, String memberId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicUserUnlike> topicUserUnlikeList = list(
                new BaseWrapper<TopicUserUnlike>()
                        .eq(TopicUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnlike.MEMBER_ID, memberId)
                        .likeAllowEmpty(TopicUserUnlike.TOPIC_ID, topicId)
                        .eq(TopicUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnlike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserUnlikeList;
    }

	@Override
	public TopicUserUnlikeView findByTopciIdAndMemberId(String topicId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicUserUnlikeView.MEMBER_ID).is(memberId)
                .and(TopicUserUnlikeView.TOPIC_ID).is(topicId)
                .and(TopicUserUnlikeView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicUserUnlikeView> topicUserUnlikeList = list(query);
        if (Util.isNullOrEmpty(topicUserUnlikeList)) {
			return null;
		}

        return topicUserUnlikeList.get(0);
	}

	@Override
	public List<TopicUserUnlike> listByTopicId(String topicId) {
		List<TopicUserUnlike> topicUserUnlikeList = list(
                new BaseWrapper<TopicUserUnlike>()
                        .eq(TopicUserUnlike.TOPIC_ID, topicId)
                        .eq(TopicUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnlike.SYSTEM_CREATE_TIME))
        );

        return topicUserUnlikeList;
	}

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicUserUnlike> topicUserUnlikeList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserUnlikeList)) {
            topicUserUnlikeList.stream().forEach(topicUserUnlike -> delete(topicUserUnlike.getTopicUserUnlikeId(), systemRequestUserId, topicUserUnlike.getSystemVersion()));
            
        }
    }

    @Override
    public TopicUserUnlike deleteByTopicIdAndMemberId(String topicId, String memberId, String appId, String systemRequestUserId) {
        TopicUserUnlikeView topicUserUnlike = findByTopciIdAndMemberId(topicId, memberId);
        
        if (Util.isNullOrEmpty(topicUserUnlike)) {
            return null;
        }
        
        TopicUserUnlike result = delete(topicUserUnlike.getTopicUserUnlikeId(), systemRequestUserId, topicUserUnlike.getSystemVersion());
       
        if (result != null) {
			return result;
		}else {
			return null;
		}
    }
}