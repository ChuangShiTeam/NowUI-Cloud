package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.mapper.TopicCommentUserUnlikeMapper;
import com.nowui.cloud.sns.topic.repository.TopicCommentUserUnlikeRepository;
import com.nowui.cloud.sns.topic.router.TopicCommentUserUnlikeRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;
import com.nowui.cloud.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 话题评论的取消点赞业务实现
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Service
public class TopicCommentUserUnlikeServiceImpl extends SuperServiceImpl<TopicCommentUserUnlikeMapper, TopicCommentUserUnlike, TopicCommentUserUnlikeRepository, TopicCommentUserUnlikeView> implements TopicCommentUserUnlikeService {
	

    @Override
    public Integer countForAdmin(String appId, String commentId, String userId) {
        Integer count = count(
                new BaseWrapper<TopicCommentUserUnlike>()
                        .eq(TopicCommentUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserUnlike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserUnlike.USER_ID, userId)
                        .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicCommentUserUnlike> listForAdmin(String appId, String commentId, String userId, Integer pageIndex, Integer pageSize) {
        List<TopicCommentUserUnlike> topicCommentUserUnlikeList = list(
                new BaseWrapper<TopicCommentUserUnlike>()
                        .eq(TopicCommentUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserUnlike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserUnlike.USER_ID, userId)
                        .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicCommentUserUnlike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentUserUnlikeList;
    }

	@Override
	public boolean deleteByCommentIdAndUserId(String commentId, String appId, String userId, String systemRequestUserId) {
		// 先根据commentId 和 userId 找记录的id
		TopicCommentUserUnlike userUnlike = findTheCommentUserUnlike(commentId , userId);
		if (Util.isNullOrEmpty(userUnlike)) {
			return true;
		}
		Boolean delete = delete(userUnlike.getCommentUserUnlikeId(), appId, TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_DELETE, systemRequestUserId, userUnlike.getSystemVersion());
		return delete;
	}

	@Override
	public TopicCommentUserUnlike findTheCommentUserUnlike(String commentId, String userId) {
		List<TopicCommentUserUnlike> topicCommentUserUnlikeList = list(
                new BaseWrapper<TopicCommentUserUnlike>()
                        .likeAllowEmpty(TopicCommentUserUnlike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserUnlike.USER_ID, userId)
                        .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicCommentUserUnlike.SYSTEM_CREATE_TIME))
        );
		if (topicCommentUserUnlikeList == null || topicCommentUserUnlikeList.size() == 0) {
			return null;
		}
        return topicCommentUserUnlikeList.get(0);
	}

}