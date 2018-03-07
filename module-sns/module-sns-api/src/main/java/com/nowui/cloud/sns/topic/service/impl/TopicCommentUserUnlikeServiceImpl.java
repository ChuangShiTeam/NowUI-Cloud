package com.nowui.cloud.sns.topic.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Integer countForAdmin(String appId, String commentId, String memberId) {
        Integer count = count(
                new BaseWrapper<TopicCommentUserUnlike>()
                        .eq(TopicCommentUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserUnlike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserUnlike.MEMBER_ID, memberId)
                        .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<TopicCommentUserUnlike> listForAdmin(String appId, String commentId, String memberId, Integer pageIndex, Integer pageSize) {
        List<TopicCommentUserUnlike> topicCommentUserUnlikeList = list(
                new BaseWrapper<TopicCommentUserUnlike>()
                        .eq(TopicCommentUserUnlike.APP_ID, appId)
                        .likeAllowEmpty(TopicCommentUserUnlike.COMMENT_ID, commentId)
                        .likeAllowEmpty(TopicCommentUserUnlike.MEMBER_ID, memberId)
                        .eq(TopicCommentUserUnlike.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(TopicCommentUserUnlike.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicCommentUserUnlikeList;
    }

	@Override
	public TopicCommentUserUnlike deleteByCommentIdAndMemberId(String commentId, String appId, String memberId, String systemRequestUserId) {
		// 先根据commentId 和 userId 找记录的id
		TopicCommentUserUnlikeView userUnlike = findTheCommentUserUnlike(appId, commentId , memberId);
		
		if (Util.isNullOrEmpty(userUnlike)) {
			return null;
		}
//		Boolean delete = delete(userUnlike.getCommentUserUnlikeId(), appId, TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_DELETE, systemRequestUserId, userUnlike.getSystemVersion());
		TopicCommentUserUnlike result = delete(userUnlike.getCommentUserUnlikeId(), systemRequestUserId, userUnlike.getSystemVersion());
		
		if (result != null) {
			return result;
		}else {
			return null;
		}
	}

	@Override
	public TopicCommentUserUnlikeView findTheCommentUserUnlike(String appId, String commentId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicCommentUserUnlikeView.APP_ID).is(appId)
                .and(TopicCommentUserUnlikeView.COMMENT_ID).is(commentId)
                .and(TopicCommentUserUnlikeView.MEMBER_ID).is(memberId)
                .and(TopicCommentUserUnlikeView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<TopicCommentUserUnlikeView> topicCommentUserUnlikeList = list(query);
        
        if (Util.isNullOrEmpty(topicCommentUserUnlikeList)) {
			return null;
		}

        return topicCommentUserUnlikeList.get(0);
	}
}
