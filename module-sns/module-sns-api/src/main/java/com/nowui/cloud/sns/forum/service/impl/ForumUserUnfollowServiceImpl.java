package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserUnfollowMapper;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 论坛用户取关关联业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumUserUnfollowServiceImpl extends BaseServiceImpl<ForumUserUnfollowMapper, ForumUserUnfollow> implements ForumUserUnfollowService {

    @Override
    public Integer countForAdmin(String appId, String userId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eqAllowEmpty(ForumUserUnfollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumUserUnfollow> listForAdmin(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumUserUnfollow> forumUserUnfollowList = list(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eqAllowEmpty(ForumUserUnfollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserUnfollowList;
    }

	@Override
	public ForumUserUnfollow findByUserIdAndForumId(String appId, String userId, String forumId) {
		List<ForumUserUnfollow> forumUserUnfollowList = list(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eq(ForumUserUnfollow.USER_ID, userId)
                        .eq(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME))
        );
		if (forumUserUnfollowList == null || forumUserUnfollowList.size() == 0) {
			return null;
		}
		return forumUserUnfollowList.get(0);
	}

	@Override
	public boolean deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
		List<ForumUserUnfollow> forumUserUnfollowList = list(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eq(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME))
        );
		//如果没有记录就返回true
		if (forumUserUnfollowList == null || forumUserUnfollowList.size() == 0) {
			return true;
		}
		
		//遍历
		for (ForumUserUnfollow forumUserUnfollow : forumUserUnfollowList) {
			Boolean delete = delete(forumUserUnfollow.getforumUserUnfollowId(), systemUpdateUserId, forumUserUnfollow.getSystemVersion());
			if (delete == false) {
				return false;
			}
		}
		
		return true;
	}
}