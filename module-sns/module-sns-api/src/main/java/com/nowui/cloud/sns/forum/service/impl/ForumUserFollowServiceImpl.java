package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserFollowMapper;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
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
public class ForumUserFollowServiceImpl extends BaseServiceImpl<ForumUserFollowMapper, ForumUserFollow> implements ForumUserFollowService {

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
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserFollowList;
    }

	@Override
	public ForumUserFollow findByUserIdAndForumId(String appId, String userId, String forumId) {
		List<ForumUserFollow> forumUserFollowList = list( 
				new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_CREATE_TIME)
        		)
		);
		if (forumUserFollowList == null || forumUserFollowList.size() == 0) {
			return null;
		}
		return forumUserFollowList.get(0);
	}

	@Override
	public List<ForumUserFollow> findByForumId(String appId, String forumId) {
		List<ForumUserFollow> forumUserFollowList = list( 
				new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_CREATE_TIME)
        		)
		);
		return forumUserFollowList;
	}

}