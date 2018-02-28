package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserUnfollowMapper;
import com.nowui.cloud.sns.forum.repository.ForumUserUnfollowRepository;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
import com.nowui.cloud.util.Util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 论坛用户取关业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumUserUnfollowServiceImpl extends SuperServiceImpl<ForumUserUnfollowMapper, ForumUserUnfollow, ForumUserUnfollowRepository, ForumUserUnfollowView> implements ForumUserUnfollowService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eqAllowEmpty(ForumUserUnfollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumUserUnfollow> listForAdmin(String appId, String memberId, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumUserUnfollow> forumUserUnfollowList = list(
                new BaseWrapper<ForumUserUnfollow>()
                        .eq(ForumUserUnfollow.APP_ID, appId)
                        .eqAllowEmpty(ForumUserUnfollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(ForumUserUnfollow.FORUM_ID, forumId)
                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserUnfollowList;
    }

	@Override
	public ForumUserUnfollowView findByMemberIdAndForumId(String appId, String memberId, String forumId) {
//		List<ForumUserUnfollow> forumUserUnfollowList = list(
//                new BaseWrapper<ForumUserUnfollow>()
//                        .eqAllowEmpty(ForumUserUnfollow.APP_ID, appId)
//                        .eq(ForumUserUnfollow.USER_ID, userId)
//                        .eq(ForumUserUnfollow.FORUM_ID, forumId)
//                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME))
//        );
//		if (forumUserUnfollowList == null || forumUserUnfollowList.size() == 0) {
//			return null;
//		}
//		return forumUserUnfollowList.get(0);
		
		Criteria criteria = Criteria.where(ForumUserUnfollowView.APP_ID).is(appId)
                .and(ForumUserUnfollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserUnfollowView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(ForumUserUnfollowView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, ForumUserUnfollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ForumUserUnfollowView> forumUserUnfollowList = list(query, sort);
        if (Util.isNullOrEmpty(forumUserUnfollowList)) {
			return null;
		}

        return forumUserUnfollowList.get(0);
	}

	@Override
	public ForumUserUnfollow deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
//		List<ForumUserUnfollow> forumUserUnfollowList = list(
//                new BaseWrapper<ForumUserUnfollow>()
//                        .eq(ForumUserUnfollow.APP_ID, appId)
//                        .eq(ForumUserUnfollow.FORUM_ID, forumId)
//                        .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(ForumUserUnfollow.SYSTEM_CREATE_TIME))
//        );
//		//如果没有记录就返回true
//		if (forumUserUnfollowList == null || forumUserUnfollowList.size() == 0) {
//			return true;
//		}
//		
//		//遍历
//		for (ForumUserUnfollow forumUserUnfollow : forumUserUnfollowList) {
//			Boolean delete = delete(forumUserUnfollow.getForumUserUnfollowId(), appId, systemUpdateUserId, forumUserUnfollow.getSystemVersion());
//			if (delete == false) {
//				return false;
//			}
//		}
		
		ForumUserUnfollow userUnfollow = delete(
				new BaseWrapper<ForumUserUnfollow>()
			      .eq(ForumUserUnfollow.APP_ID, appId)
			      .eq(ForumUserUnfollow.FORUM_ID, forumId)
			      .eq(ForumUserUnfollow.SYSTEM_STATUS, true)
				  , systemUpdateUserId
			);
		
		return userUnfollow;
	}
}