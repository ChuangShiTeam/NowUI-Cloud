package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserFollowMapper;
import com.nowui.cloud.sns.forum.repository.ForumUserFollowRepository;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.topic.entity.TopicForum;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 论坛用户关注业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumUserFollowServiceImpl extends SuperServiceImpl<ForumUserFollowMapper, ForumUserFollow, ForumUserFollowRepository, ForumUserFollowView> implements ForumUserFollowService {

    @Override
    public Integer countForAdmin(String appId, String userId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumUserFollow> listForAdmin(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserFollowList;
    }
    
    @Override
    public Integer countByUserId(String appId, String userId) {
        Integer count = count(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );
        return count;
    }
    
    @Override
    public Integer countByForumId(String appId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );
        return count;
    }
    
    @Override
    public List<ForumUserFollow> listByUserId(String appId, String userId, Integer pageIndex, Integer pageSize) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.FORUM_USER_FOLLOW_IS_TOP))
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserFollowList;
    }
    
    @Override
    public List<ForumUserFollow> listByUserId(String appId, String userId) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.FORUM_USER_FOLLOW_IS_TOP))
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME))
        );

        return forumUserFollowList;
    }
    
    @Override
    public List<ForumUserFollow> listByUserId(String userId) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );

        return forumUserFollowList;
    }

	@Override
	public ForumUserFollow findByUserIdAndForumId(String appId, String userId, String forumId) {
		ForumUserFollow forumUserFollow = find( 
				new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
		);
		return forumUserFollow;
	}
	
	@Override
	public ForumUserFollow findByUserIdAndForumId(String userId, String forumId) {
	    ForumUserFollow forumUserFollow = find( 
				new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.USER_ID, userId)
                        .eq(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
		);
		
		return forumUserFollow;
	}

	@Override
	public List<ForumUserFollow> findByForumId(String appId, String forumId) {
		List<ForumUserFollow> forumUserFollowList = list( 
				new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(ForumUserFollow.SYSTEM_CREATE_TIME)
        		)
		);
		return forumUserFollowList;
	}

	@Override
	public boolean deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
		delete(
    			new BaseWrapper<TopicForum>()
    				.eq(TopicForum.APP_ID, appId)
    				.eq(TopicForum.FORUM_ID, forumId)
    				.eq(TopicForum.SYSTEM_STATUS, true),
    			systemUpdateUserId
    	);
		
		return false;
	}

	

}