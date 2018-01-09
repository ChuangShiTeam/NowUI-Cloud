package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.mapper.TopicTipMapper;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题提醒业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicTipServiceImpl extends BaseServiceImpl<TopicTipMapper, TopicTip> implements TopicTipService {

    @Override
    public Integer countForAdmin(String appId, String topicId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.USER_ID, userId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicTip> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicTip> topicTipList = list(
                new BaseWrapper<TopicTip>()
                        .eq(TopicTip.APP_ID, appId)
                        .likeAllowEmpty(TopicTip.TOPIC_ID, topicId)
                        .likeAllowEmpty(TopicTip.USER_ID, userId)
                        .eq(TopicTip.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicTip.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicTipList;
    }

}