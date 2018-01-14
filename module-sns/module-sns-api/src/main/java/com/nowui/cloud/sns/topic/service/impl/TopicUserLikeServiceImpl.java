package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.mapper.TopicUserLikeMapper;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 点赞话题关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicUserLikeServiceImpl extends BaseServiceImpl<TopicUserLikeMapper, TopicUserLike> implements TopicUserLikeService {

    @Override
    public Integer countForAdmin(String appId, String userId, String topicId) {
        Integer count = count(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicUserLike> listForAdmin(String appId, String userId, String topicId, Integer pageIndex, Integer pageSize) {
        List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicUserLikeList;
    }

	@Override
	public TopicUserLike findLike(String appId, String userId, String topicId) {
		List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.USER_ID, userId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME))
        );
		if (topicUserLikeList == null || topicUserLikeList.size() == 0) {
			return null;
		}

        return topicUserLikeList.get(0);
        
	}

	@Override
	public List<TopicUserLike> allLikeListByTopic(String appId, String topicId) {
		List<TopicUserLike> topicUserLikeList = list(
                new BaseWrapper<TopicUserLike>()
                        .eq(TopicUserLike.APP_ID, appId)
                        .likeAllowEmpty(TopicUserLike.TOPIC_ID, topicId)
                        .eq(TopicUserLike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicUserLike.SYSTEM_CREATE_TIME))
        );

        return topicUserLikeList;
	}

}