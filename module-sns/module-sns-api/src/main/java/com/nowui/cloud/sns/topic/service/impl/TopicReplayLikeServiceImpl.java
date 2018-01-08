package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicReplayLike;
import com.nowui.cloud.sns.topic.mapper.TopicReplayLikeMapper;
import com.nowui.cloud.sns.topic.service.TopicReplayLikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题回复业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicReplayLikeServiceImpl extends BaseServiceImpl<TopicReplayLikeMapper, TopicReplayLike> implements TopicReplayLikeService {

    @Override
    public Integer adminCount(String appId, String topicCommentId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicReplayLike>()
                        .eq(TopicReplayLike.APP_ID, appId)
                        .likeAllowEmpty(TopicReplayLike.TOPIC_COMMENT_ID, topicCommentId)
                        .likeAllowEmpty(TopicReplayLike.USER_ID, userId)
                        .eq(TopicReplayLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicReplayLike> adminList(String appId, String topicCommentId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicReplayLike> topicReplayLikeList = list(
                new BaseWrapper<TopicReplayLike>()
                        .eq(TopicReplayLike.APP_ID, appId)
                        .likeAllowEmpty(TopicReplayLike.TOPIC_COMMENT_ID, topicCommentId)
                        .likeAllowEmpty(TopicReplayLike.USER_ID, userId)
                        .eq(TopicReplayLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicReplayLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicReplayLikeList;
    }

}