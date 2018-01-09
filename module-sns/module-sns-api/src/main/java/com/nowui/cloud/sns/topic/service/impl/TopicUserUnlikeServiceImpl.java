package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.mapper.TopicUserUnlikeMapper;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题用户取消点赞关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserUnlikeServiceImpl extends BaseServiceImpl<TopicUserUnlikeMapper, TopicUserUnlike> implements TopicUserUnlikeService {

    @Override
    public Integer countForAdmin(String appId, String userId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicUserUnlike>()
                        .eq(TopicUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnlike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserUnlike.TOPIC_ID, topicId)
                        .eq(TopicUserUnlike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserUnlike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicUserUnlike> topicUserUnlikeList = list(
                new BaseWrapper<TopicUserUnlike>()
                        .eq(TopicUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserUnlike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserUnlike.TOPIC_ID, topicId)
                        .eq(TopicUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserUnlike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserUnlikeList;
    }

}