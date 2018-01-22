package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
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
    
    @Override
    public Integer countForAdmin(String appId, String forumId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.FORUM_ID, forumId)
                        .likeAllowEmpty(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicForum> listForAdmin(String appId, String forumId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.FORUM_ID, forumId)
                        .likeAllowEmpty(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicForumList;
    }
    
    @Override
    public void deleteByForumId(String appId, String forumId , String systemUpdateUserId) {
    	// 从论坛动态表中查找所有有ForumId的主键
    	List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
        );
    	//遍历删除
    	if (!Util.isNullOrEmpty(topicForumList)) {
    	    topicForumList.stream().forEach(topicForum -> {
    	        delete(topicForum.getTopicForumId(), systemUpdateUserId, topicForum.getSystemVersion());
    	    });
    	}

    }

	@Override
	public Integer countForToday(String appId, String forumId, String topicId) {
		Integer count = count(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.FORUM_ID, forumId)
                        .likeAllowEmpty(TopicForum.TOPIC_ID, topicId)
                        .ge(TopicForum.SYSTEM_CREATE_TIME, DateUtil.getTodayStartDateTime() )
                        .eq(TopicForum.SYSTEM_STATUS, true)
        );
        return count;
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
            topicForumList.stream().forEach(topicForum -> delete(topicForum.getTopicForumId(), systemRequestUserId, topicForum.getSystemVersion()));
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
                
                save(topicForum, topicForumId, systemRequestUserId);
                
                topicForumIdList.add(topicForumId);
            }
        }
        // 缓存话题论坛编号列表
        redis.opsForValue().set(TOPIC_FORUM_ID_LIST_BY_TOPIC_ID + topicId, topicForumIdList);
    }

}