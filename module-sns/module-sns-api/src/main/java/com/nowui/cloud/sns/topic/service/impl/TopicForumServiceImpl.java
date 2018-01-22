package com.nowui.cloud.sns.topic.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.mapper.TopicForumMapper;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.util.DateUtil;

/**
 * 话题论坛关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicForumServiceImpl extends BaseServiceImpl<TopicForumMapper, TopicForum> implements TopicForumService {

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
    public Boolean deleteByForumId(String appId, String forumId , String systemUpdateUserId) {
    	//从论坛动态表中查找所有有ForumId的主键
    	List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.FORUM_ID, forumId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
        );
    	//遍历删除
    	for (TopicForum topicForum : topicForumList) {
    		Boolean delResult = delete(topicForum.getTopicForumId(), systemUpdateUserId, topicForum.getSystemVersion());
    		if (delResult == false) {
				return false;
			}
		}

    	return true;
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
	public List<TopicForum> listByTopicId(String appId, String topicId) {
		List<TopicForum> topicForumList = list(
                new BaseWrapper<TopicForum>()
                        .eq(TopicForum.APP_ID, appId)
                        .likeAllowEmpty(TopicForum.TOPIC_ID, topicId)
                        .eq(TopicForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicForum.SYSTEM_CREATE_TIME))
        );
        return topicForumList;
	}

}