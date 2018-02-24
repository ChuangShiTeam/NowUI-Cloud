package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.mapper.TopicForumMapper;
import com.nowui.cloud.sns.topic.repository.TopicForumRepository;
import com.nowui.cloud.sns.topic.router.TopicForumRouter;
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
//		Integer count = count(
//                new BaseWrapper<TopicForum>()
//                        .eq(TopicForum.FORUM_ID, forumId)
//                        .ge(TopicForum.SYSTEM_CREATE_TIME, DateUtil.getTodayStartDateTime())
//                        .eq(TopicForum.SYSTEM_STATUS, true)
//        );
//        return count;

		Criteria criteria = Criteria.where(TopicForumView.FORUM_ID).regex(".*?" + forumId + ".*")
			    .and(TopicForumView.SYSTEM_CREATE_TIME).gte(DateUtil.getTodayStartDateTime())
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
	}
	
	@Override
    public Integer countByForumId(String appId, String forumId) {
//        Integer forumTopicCount = (Integer) redisTemplate.opsForValue().get(TOPIC_FORUM_COUNT_BY_FORUM_ID + forumId);
//        
//        if (forumTopicCount == null) {
//            forumTopicCount = count(
//                    new BaseWrapper<TopicForum>()
//                    .eq(TopicForum.FORUM_ID, forumId)
//                    .eq(TopicForum.SYSTEM_STATUS, true)
//            );
//            
//            redisTemplate.opsForValue().set(TOPIC_FORUM_COUNT_BY_FORUM_ID + forumId, forumTopicCount);
//        }
//        
//        return forumTopicCount;
		
		Criteria criteria = Criteria.where(TopicForumView.APP_ID).is(appId)
                .and(TopicForumView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<TopicForumView> listByForumId(String appId, String forumId, Integer pageIndex, Integer pageSize) {
//        List<TopicForum> topicForumList = list(
//                new BaseWrapper<TopicForum>()
//                        .eq(TopicForum.FORUM_ID, forumId)
//                        .eq(TopicForum.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
//                pageIndex,
//                pageSize
//        );
//
//        return topicForumList;
    	
    	
    	Criteria criteria = Criteria.where(TopicForumView.APP_ID).is(appId)
                .and(TopicForumView.FORUM_ID).regex(".*?" + forumId + ".*")
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
                .and(TopicForumView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(TopicForumView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicForumView> topicForumList = list(query);

        return topicForumList;
	}
    
    @Override
	public List<TopicForumView> listByForumId(String forumId, List<String> excludeTopicIdList, Date systemCreateTime,
			Integer pageIndex, Integer pageSize) {
//    	List<TopicForum> topicForumList = list(
//                new BaseWrapper<TopicForum>()
//                        .eq(TopicForum.FORUM_ID, forumId)
//                        .eq(TopicForum.SYSTEM_STATUS, true)
//                        .notIn(Topic.TOPIC_ID, excludeTopicIdList)
//                        .le(Topic.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(systemCreateTime))
//                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
//                pageIndex,
//                pageSize
//        );
//
//        return topicForumList;
			
        
//		Criteria criteria = Criteria.where(TopicForum.FORUM_ID).regex(".*?" + forumId + ".*")
//				.and(TopicForumView.TOPIC_ID).nin(excludeTopicIdList)
//				.and(Topic.SYSTEM_CREATE_TIME).lt(DateUtil.getDateTimeString(systemCreateTime))
//				.and(TopicForumView.SYSTEM_STATUS).is(true);
    	long time = systemCreateTime.getTime();
    	Criteria criteria = Criteria.where(TopicForum.FORUM_ID).regex(".*?" + forumId + ".*")
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
//        List<String> topicForumIdList = (List<String>) redisTemplate.opsForValue().get(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId);
        
        // TODO 暂时注释掉,返回null值,回来再调
//        if (topicForumIdList == null) {
//            List<TopicForumView> topicForumList = list(
//                    new BaseWrapper<TopicForumView>()
//                            .eq(TopicForum.TOPIC_ID, topicId)
//                            .eq(TopicForum.SYSTEM_STATUS, true)
//                            .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
//            );
//            
//            topicForumIdList = topicForumList.stream().map(topicForum -> topicForum.getTopicForumId()).collect(Collectors.toList());
//            // 缓存话题论坛编号列表
//            redisTemplate.opsForValue().set(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId, topicForumIdList);
//            
//            return topicForumList;
//        }
		
//        return topicForumIdList.stream().map(topicForumId -> find(topicForumId)).collect(Collectors.toList());
        
        Criteria criteria = Criteria.where(TopicForumView.TOPIC_ID).regex(".*?" + topicId + ".*")
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
    public void batchSave(String appId, String topicId, List<TopicForum> topicForumList, String systemRequestUserId) {
        List<String> topicForumIdList = new ArrayList<String>();
        
        if (!Util.isNullOrEmpty(topicForumList)) {
            for (TopicForum topicForum : topicForumList) {
                topicForum.setTopicId(topicId);
                topicForum.setAppId(appId);
                String topicForumId = Util.getRandomUUID();
                
//TODO 在外面处理消息         Boolean flag = save(topicForum, topicForumId, appId, TopicForumRouter.TOPIC_FORUM_V1_SAVE, systemRequestUserId);
                TopicForum result = save(topicForum, topicForumId, systemRequestUserId);
                
                if (result == null) {
                    throw new BusinessException("保存失败");
                }
                                
                topicForumIdList.add(topicForumId);
            }
            
// TODO 不用缓存了
//            topicForumIdList.forEach(topicForumId -> {
//            	TopicForumView topicForum = find(topicForumId);
//            	
//            	// 论坛话题数缓存加一
//                Integer forumTopicCount = countByForumId(topicForum.getForumId());
//                
//                redisTemplate.opsForValue().set(TOPIC_FORUM_COUNT_BY_FORUM_ID + topicForum.getForumId(), forumTopicCount + 1);
//
//            });
        }
        // 缓存话题论坛编号列表
//        redisTemplate.opsForValue().set(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId, topicForumIdList);
    }

	

	

}