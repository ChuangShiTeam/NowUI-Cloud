package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.mapper.TopicCommentMapper;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 话题评论业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicCommentServiceImpl extends BaseServiceImpl<TopicCommentMapper, TopicComment> implements TopicCommentService {

    public static final String TOPIC_COMMENT_COUNT_BY_TOPIC_ID = "topic_comment_count_by_topic_id_";
    
    @Override
    public Integer countForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId) {
        Integer count = count(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.USER_ID, userId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLAY_USER_ID, topicReplayUserId)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_COMMENT_ID, topicReplyCommentId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicComment> listForAdmin(String appId, String userId, String topicId, String topicCommentContent, String topicReplayUserId, String topicReplyCommentId, Integer pageIndex, Integer pageSize) {
        List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.APP_ID, appId)
                        .likeAllowEmpty(TopicComment.USER_ID, userId)
                        .likeAllowEmpty(TopicComment.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicComment.TOPIC_COMMENT_CONTENT, topicCommentContent)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLAY_USER_ID, topicReplayUserId)
                        .likeAllowEmpty(TopicComment.TOPIC_REPLY_COMMENT_ID, topicReplyCommentId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentList;
    }
    
    @Override
    public Integer countByTopicId(String topicId) {
        Integer count = (Integer) redis.opsForValue().get(TOPIC_COMMENT_COUNT_BY_TOPIC_ID);
        
        if (count == null) {
            count = count(
                    new BaseWrapper<TopicComment>()
                            .eq(TopicComment.TOPIC_ID, topicId)
                            .eq(TopicComment.SYSTEM_STATUS, true)
            );
            // 更新话题评论数缓存
            redis.opsForValue().set(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId, count);
        }
        
        return count;
    }
    
	@Override
	public List<TopicComment> listByTopicId(String topicId) {
		List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.TOPIC_ID, topicId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME))
        );

        return topicCommentList;
	}

    @Override
    public List<TopicComment> listByTopicId(String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.TOPIC_ID, topicId)
                        .eq(TopicComment.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentList;
    }
    
    @Override
	public List<TopicComment> listByTopicId(String topicId, List<String> excludeCommentIdList, Date systemCreateTime,
			Integer pageIndex, Integer pageSize) {
    	List<TopicComment> topicCommentList = list(
                new BaseWrapper<TopicComment>()
                        .eq(TopicComment.TOPIC_ID, topicId)
                        .notIn(TopicComment.TOPIC_ID, excludeCommentIdList)
                        .eq(TopicComment.SYSTEM_STATUS, true)
                        .le(TopicComment.SYSTEM_CREATE_TIME, DateUtil.getDateTimeString(systemCreateTime))
                        .orderDesc(Arrays.asList(TopicComment.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentList;
	}
    

    @Override
    public void deleteByTopicId(String topicId, String systemRequestUserId) {
        List<TopicComment> topicCommentList = listByTopicId(topicId);
        
        if (!Util.isNullOrEmpty(topicCommentList)) {
            topicCommentList.stream().forEach(topicComment -> delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion()));
        }
        redis.delete(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId);
    }

    @Override
    public Boolean save(String appId, String topicId, String userId, TopicComment topicComment, String systemRequestUserId) {
        
        topicComment.setAppId(appId);
        topicComment.setUserId(userId);
        topicComment.setTopicId(topicId);
        
        Boolean result = save(topicComment, Util.getRandomUUID(), systemRequestUserId);
        
        if (result) {
            // 更新话题评论数缓存
            Integer count = countByTopicId(topicId);
            redis.opsForValue().set(TOPIC_COMMENT_COUNT_BY_TOPIC_ID + topicId, (count + 1));
        }
        return result;
    }


}