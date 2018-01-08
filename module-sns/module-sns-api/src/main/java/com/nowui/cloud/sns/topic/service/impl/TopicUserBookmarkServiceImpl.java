package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.mapper.TopicUserBookmarkMapper;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题用户收藏关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserBookmarkServiceImpl extends BaseServiceImpl<TopicUserBookmarkMapper, TopicUserBookmark> implements TopicUserBookmarkService {

    @Override
    public Integer adminCount(String appId, String topicId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.USER_ID, userId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserBookmark> adminList(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicUserBookmark> topicUserBookmarkList = list(
                new BaseWrapper<TopicUserBookmark>()
                        .eq(TopicUserBookmark.APP_ID, appId)
                        .likeAllowEmpty(TopicUserBookmark.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicUserBookmark.USER_ID, userId)
                        .eq(TopicUserBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserBookmark.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserBookmarkList;
    }

}