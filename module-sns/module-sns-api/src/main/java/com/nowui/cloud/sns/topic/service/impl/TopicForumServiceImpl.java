package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.mapper.TopicForumMapper;
import com.nowui.cloud.sns.topic.repository.TopicForumRepository;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;

/**
 * 话题论坛关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicForumServiceImpl extends SuperServiceImpl<TopicForumMapper, TopicForum, TopicForumRepository, TopicForumView> implements TopicForumService {

    public static final String TOPIC_FORUM_ID_LIST_BY_TOPIC_ID = "topic_forum_id_list_by_topic_id_";
    
    public static final String TOPIC_FORUM_COUNT_BY_FORUM_ID = "topic_forum_count_by_forum_id_";
    
    @Override
    public Integer countForAdmin(String appId, String forumId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .eq(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicForum> listForAdmin(String appId, String forumId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .eq(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicForumList;
    }
    
    @Override
    public TopicForum deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
    	TopicForum delete = delete(
    			new BaseWrapper<TopicForum>()
    				.eq(TopicForum.APP_ID, appId)
    				.eq(TopicForum.FORUM_ID, forumId)
    				.eq(TopicForum.SYSTEM_STATUS, true),
    			systemUpdateUserId
    	);
    	
    	return delete;
    }

	@Override
	public Integer countTodayByForumId(String forumId) {
		Date todayStartDateTime = DateUtil.getTodayStartDateTime();
		long todayStartTime = todayStartDateTime.getTime();
		Criteria criteria = Criteria.where(TopicForumView.FORUM_ID).is(forumId)
			    .and(TopicForumView.SYSTEM_CREATE_TIME).gte(todayStartTime)
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
	}
	
	@Override
    public Integer countByForumId(String appId, String forumId) {
		
		Criteria criteria = Criteria.where(TopicForumView.APP_ID).is(appId)
                .and(TopicForumView.FORUM_ID).is(forumId)
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<TopicForumView> listByForumId(String appId, String forumId, Integer pageIndex, Integer pageSize) {
    	
    	Criteria criteria = Criteria.where(TopicForumView.APP_ID).is(appId)
                .and(TopicForumView.FORUM_ID).is(forumId)
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicForumView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicForumView> topicForumList = list(query, sort, pageIndex, pageSize);

        return topicForumList;
    }
    
    
    @Override
	public List<TopicForumView> listByForumId(String appId, String forumId) {
    	Criteria criteria = Criteria.where(TopicForumView.APP_ID).is(appId)
                .and(TopicForumView.FORUM_ID).is(forumId)
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicForumView> topicForumList = list(query);

        return topicForumList;
	}
    
    @Override
	public List<TopicForumView> listByForumId(String forumId, List<String> excludeTopicIdList, Date systemCreateTime,
			Integer pageIndex, Integer pageSize) {
        
    	long time = systemCreateTime.getTime();
    	Criteria criteria = Criteria.where(TopicForum.FORUM_ID).is(forumId)
				.and(TopicForumView.TOPIC_ID).nin(excludeTopicIdList)
				.and(Topic.SYSTEM_CREATE_TIME).lte(time)
				.and(TopicForumView.SYSTEM_STATUS).is(true);
	
	    List<Order> orders = new ArrayList<Order>();
	    orders.add(new Order(Sort.Direction.DESC, TopicForumView.SYSTEM_CREATE_TIME));
	    Sort sort = Sort.by(orders);
	
	    Query query = new Query(criteria);
	    query.with(sort);
	
	    List<TopicForumView> topicForumList = list(query, sort, pageIndex, pageSize);
	
	    return topicForumList;
	}

	@Override
	public List<TopicForumView> listByTopicId(String topicId) {
        
        Criteria criteria = Criteria.where(TopicForumView.TOPIC_ID).is(topicId)
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicForumView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicForumView> topicForumList = list(query, sort);

        return topicForumList;
	}

    @Override
    public void deleteByTopicId(String topicId, String appId, String systemRequestUserId) {
        delete(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .eq(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true),
                systemRequestUserId
        );
    }

    @Override
    public List<TopicForum> batchSave(String appId, String topicId, List<TopicForum> topicForumList, String systemRequestUserId) {
        List<String> topicForumIdList = new ArrayList<String>();
        List<TopicForum> theReturnTopicForumList = new ArrayList<>();
        
        if (!Util.isNullOrEmpty(topicForumList)) {
            for (TopicForum topicForum : topicForumList) {
                topicForum.setTopicId(topicId);
                topicForum.setAppId(appId);
                String topicForumId = Util.getRandomUUID();
                
                TopicForum result = save(topicForum, topicForumId, systemRequestUserId);
                
                if (result == null) {
                    throw new BusinessException("保存失败");
                }
                theReturnTopicForumList.add(result);                
                topicForumIdList.add(topicForumId);
            }
            
        }
        
        return theReturnTopicForumList;
    }


}