package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.mapper.TopicForumMapper;
import com.nowui.cloud.sns.topic.service.TopicForumService;
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
public class TopicForumServiceImpl extends BaseServiceImpl<TopicForumMapper, TopicForum> implements TopicForumService {

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
    public void deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
    	List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .eq(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
        );
    	//遍历删除
    	if (!Util.isNullOrEmpty(topicForumList)) {
    	    topicForumList.stream().forEach(topicForum -> {
    	    	redis.delete(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicForum.TOPIC_ID);
    	        delete(topicForum.getTopicForumId(), systemUpdateUserId, topicForum.getSystemVersion());
    	    });
    	    
    	    redis.delete(TOPIC_FORUM_COUNT_BY_FORUM_ID + forumId);
    	}
    }

	@Override
	public Integer countTodayByForumId(String forumId) {
		Integer count = count(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.FORUM_ID, forumId)
                        .ge(TopicForum.SYSTEM_CREATE_TIME, DateUtil.getTodayStartDateTime())
                        .eq(TopicForum.SYSTEM_STATUS, true)
        );
        return count;
	}
	
	@Override
    public Integer countByForumId(String forumId) {
        Integer forumTopicCount = (Integer) redis.opsForValue().get(TOPIC_FORUM_COUNT_BY_FORUM_ID + forumId);
        
        if (forumTopicCount == null) {
            forumTopicCount = count(
                    new BaseWrapper<TopicForum>()
                    .eq(TopicForum.FORUM_ID, forumId)
                    .eq(TopicForum.SYSTEM_STATUS, true)
            );
            
            redis.opsForValue().set(TOPIC_FORUM_COUNT_BY_FORUM_ID + forumId, forumTopicCount);
        }
        
        return forumTopicCount;
    }

    @Override
    public List<TopicForum> listByForumId(String forumId, Integer pageIndex, Integer pageSize) {
        List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicForumList;
    }
    
    @Override
	public List<TopicForum> listByForumId(String forumId, List<String> excludeTopicIdList, Date systemCreateTime,
			Integer pageIndex, Integer pageSize) {
    	List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .notIn(Topic.TOPIC_ID, excludeTopicIdList)
                        .le(Topic.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(systemCreateTime))
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicForumList;
	}

	@Override
	public List<TopicForum> listByTopicId(String topicId) {
        List<String> topicForumIdList = (List<String>) redis.opsForValue().get(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId);
        
        if (topicForumIdList == null) {
            List<TopicForum> topicForumList = list(
                    new BaseWrapper<TopicForum>()
                            .eq(TopicForum.TOPIC_ID, topicId)
                            .eq(TopicForum.SYSTEM_STATUS, true)
                            .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
            );
            
            topicForumIdList = topicForumList.stream().map(topicForum -> topicForum.getTopicForumId()).collect(Collectors.toList());
            // 缓存话题论坛编号列表
            redis.opsForValue().set(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId, topicForumIdList);
            
            return topicForumList;
        }
		
        return topicForumIdList.stream().map(topicForumId -> find(topicForumId)).collect(Collectors.toList());
	}

    @Override
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicForum> topicForumList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicForumList)) {
        	
        	topicForumList.stream().forEach(topicForum -> {
                delete(topicForum.getTopicForumId(), systemRequestUserId, topicForum.getSystemVersion());
            });
            
        	topicForumList.stream().forEach(topicForum -> {
        		// 论坛话题数缓存减一
        		Integer forumTopicCount = countByForumId(topicForum.getForumId());
        		
                redis.opsForValue().set(TOPIC_FORUM_COUNT_BY_FORUM_ID + topicForum.getForumId(), forumTopicCount - 1);
        	});
        }
        
        // 清空缓存
        redis.delete(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId);    
    }

    @Override
    public void batchSave(String appId, String topicId, List<TopicForum> topicForumList, String systemRequestUserId) {
        List<String> topicForumIdList = new ArrayList<String>();
        
        if (!Util.isNullOrEmpty(topicForumList)) {
            for (TopicForum topicForum : topicForumList) {
                topicForum.setTopicId(topicId);
                topicForum.setAppId(appId);
                String topicForumId = Util.getRandomUUID();
                
                Boolean flag = save(topicForum, topicForumId, systemRequestUserId);
                
                if (!flag) {
                    throw new BusinessException("保存失败");
                }
                                
                topicForumIdList.add(topicForumId);
            }
            
            topicForumIdList.forEach(topicForumId -> {
            	TopicForum topicForum = find(topicForumId);
            	
            	// 论坛话题数缓存加一
                Integer forumTopicCount = countByForumId(topicForum.getForumId());
                
                redis.opsForValue().set(TOPIC_FORUM_COUNT_BY_FORUM_ID + topicForum.getForumId(), forumTopicCount + 1);

            });
        }
        // 缓存话题论坛编号列表
        redis.opsForValue().set(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId, topicForumIdList);
    }

	

}