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
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserBookmarkMapper;
import com.nowui.cloud.sns.topic.repository.TopicUserBookmarkRepository;
import com.nowui.cloud.sns.topic.router.TopicUserBookmarkRouter;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.view.TopicForumView;
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
    public Integer countForAdmin(String appId, String topicId, String memberId) {
        Integer count = count(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.MEMBER_ID, memberId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserBookmark> listForAdmin(String appId, String topicId, String memberId, Integer pageIndex, Integer pageSize) {
        List<TopicUserBookmark> topicUserBookmarkList = list(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.MEMBER_ID, memberId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserBookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserBookmarkList;
    }

	@Override
	public TopicUserBookmarkView findByTopicIdAndMemberId(String topicId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).is(topicId)
                .and(TopicUserBookmarkView.MEMBER_ID).is(memberId)
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicUserBookmarkView> topicUserBookmarkList = list(query);
        
        if (Util.isNullOrEmpty(topicUserBookmarkList)) {
			return null;
		}

        return topicUserBookmarkList.get(0);
	}
	
	@Override
	public List<TopicUserBookmarkView> listByTopicIdAndMemberId(String topicId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).is(topicId)
                .and(TopicUserBookmarkView.MEMBER_ID).is(memberId)
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicUserBookmarkView> topicUserBookmarkList = list(query);
        
        if (Util.isNullOrEmpty(topicUserBookmarkList)) {
			return null;
		}

        return topicUserBookmarkList;
	}

    @Override
    public Integer countByTopicId(String topicId) {
    	
    	Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).is(topicId)
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    	
    }

    @Override
    public List<TopicUserBookmarkView> listByTopicId(String topicId) {
    	
    	Criteria criteria = Criteria.where(TopicUserBookmarkView.TOPIC_ID).is(topicId)
                .and(TopicUserBookmarkView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicUserBookmarkView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicUserBookmarkView> topicUserBookmarkList = list(query, sort);

        return topicUserBookmarkList;
    }

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        List<TopicUserBookmarkView> topicUserBookmarkList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicUserBookmarkList)) {
       	 topicUserBookmarkList.stream().forEach(topicUserBookmark -> delete(topicUserBookmark.getTopicUserBookmarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion()));
        }
    }

    @Override
    public TopicUserBookmark save(String appId, String topicId, String memberId, String systemRequestUserId) {
        TopicUserBookmark topicUserBookmark = new TopicUserBookmark();
        topicUserBookmark.setAppId(appId);
        topicUserBookmark.setTopicId(topicId);
        topicUserBookmark.setMemberId(memberId);
        
        TopicUserBookmark result = save(topicUserBookmark, Util.getRandomUUID(), systemRequestUserId);
        
        if (result != null) {
        	return result;
        }else {
        	 return null;
        }
    }

    @Override
    public TopicUserBookmark deleteByTopicIdAndMemberId(String topicId, String memberId, String appId, String systemRequestUserId) {
        TopicUserBookmarkView topicUserBookmark = findByTopicIdAndMemberId(topicId, memberId);
        
        if (Util.isNullOrEmpty(topicUserBookmark)) {
            return null;
        }
        TopicUserBookmark result = delete(topicUserBookmark.getTopicUserBookmarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion());
        
        if (result != null) {
        	return result;
        }else {
        	return null;
		}
    }
}