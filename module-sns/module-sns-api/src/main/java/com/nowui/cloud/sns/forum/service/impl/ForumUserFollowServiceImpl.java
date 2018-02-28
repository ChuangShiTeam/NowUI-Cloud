package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserFollowMapper;
import com.nowui.cloud.sns.forum.repository.ForumUserFollowRepository;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
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
 * 论坛用户关注业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumUserFollowServiceImpl extends SuperServiceImpl<ForumUserFollowMapper, ForumUserFollow, ForumUserFollowRepository, ForumUserFollowView> implements ForumUserFollowService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumUserFollow> listForAdmin(String appId, String memberId, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserFollowList;
    }
    
    @Override
    public Integer countByMemberId(String appId, String memberId) {
//        Integer count = count(
//                new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.APP_ID, appId)
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//        );
//        return count;

    	Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).is(appId)
                .and(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }
    
    @Override
    public Integer countByForumId(String appId, String forumId) {
//        Integer count = count(
//                new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.APP_ID, appId)
//                        .eq(ForumUserFollow.FORUM_ID, forumId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//        );
//        return count;
    	
    	Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).is(appId)
                .and(ForumUserFollowView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }
    
    @Override
    public List<ForumUserFollowView> listByMemberId(String appId, String memberId, Integer pageIndex, Integer pageSize) {
//        List<ForumUserFollow> forumUserFollowList = list(
//                new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.APP_ID, appId)
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(ForumUserFollow.FORUM_USER_FOLLOW_IS_TOP))
//                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME)),
//                pageIndex,
//                pageSize
//        );
//
//        return forumUserFollowList;
    	
    	Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).is(appId)
                .and(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, ForumUserFollow.FORUM_USER_FOLLOW_IS_TOP));
        orders.add(new Order(Sort.Direction.DESC, ForumUserFollowView.SYSTEM_UPDATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        List<ForumUserFollowView> forumUserFollowList = list(query, sort, pageIndex, pageSize);

        return forumUserFollowList;
    }
    
    @Override
    public List<ForumUserFollowView> listByMemberId(String appId, String memberId) {
//        List<ForumUserFollow> forumUserFollowList = list(
//                new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.APP_ID, appId)
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//                        .orderDesc(Arrays.asList(ForumUserFollow.FORUM_USER_FOLLOW_IS_TOP))
//                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_UPDATE_TIME))
//        );
//
//        return forumUserFollowList;
    	
    	Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).regex(".*?" + appId + ".*")
    			.and(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<ForumUserFollowView> forumUserFollow = list(query);
        
        return forumUserFollow;
    }
    
    @Override
    public List<ForumUserFollowView> listByMemberId(String memberId) {
//        List<ForumUserFollow> forumUserFollowList = list(
//                new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//        );
//
//        return forumUserFollowList;
    	
    	Criteria criteria = Criteria.where(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<ForumUserFollowView> forumUserFollow = list(query);
        
        return forumUserFollow;
    }

	@Override
	public ForumUserFollowView findByMemberIdAndForumId(String appId, String memberId, String forumId) {
//		ForumUserFollow forumUserFollow = find( 
//				new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.APP_ID, appId)
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.FORUM_ID, forumId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//		);
//		return forumUserFollow;
		
		Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).is(appId)
                .and(ForumUserFollowView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<ForumUserFollowView> forumUserFollow = list(query);
        
        if (Util.isNullOrEmpty(forumUserFollow)) {
			return null;
		}
        
        return forumUserFollow.get(0);
	}
	
	@Override
	public ForumUserFollowView findByMemberIdAndForumId(String memberId, String forumId) {
//	    ForumUserFollow forumUserFollow = find( 
//				new BaseWrapper<ForumUserFollow>()
//                        .eq(ForumUserFollow.USER_ID, userId)
//                        .eq(ForumUserFollow.FORUM_ID, forumId)
//                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
//		);
//		
//		return forumUserFollow;
		
		
		Criteria criteria = Criteria.where(ForumUserFollowView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(ForumUserFollowView.MEMBER_ID).regex(".*?" + memberId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        List<ForumUserFollowView> forumUserFollow = list(query);
        
        if (Util.isNullOrEmpty(forumUserFollow)) {
			return null;
		}
        
        return forumUserFollow.get(0);
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
	public ForumUserFollow updateTopForum(String appId, String forumId, String memberId, String systemRequestUserId) {
		ForumUserFollow forumUserFollow = new ForumUserFollow();
		forumUserFollow.setForumUserFollowIsTop(true);

		update(
				forumUserFollow,
				new BaseWrapper<ForumUserFollow>()
	                .eq(ForumUserFollow.APP_ID, appId)
	                .eq(ForumUserFollow.FORUM_ID, forumId)
	                .eq(ForumUserFollow.MEMBER_ID, memberId)
	                .eq(ForumUserFollow.SYSTEM_STATUS, true),
	            systemRequestUserId
		);

		return forumUserFollow;
	}

	@Override
	public ForumUserFollow deleteByForumId(String appId, String forumId, String systemUpdateUserId) {
        ForumUserFollow delete = delete(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .eq(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true),
                systemUpdateUserId
        );

		return delete;
	}

	@Override
	public List<ForumUserFollowView> listByforumId(String appId, String forumId, Integer pageIndex, Integer pageSize) {
		
		Criteria criteria = Criteria.where(ForumUserFollowView.APP_ID).is(appId)
                .and(ForumUserFollowView.FORUM_ID).regex(".*?" + forumId + ".*")
                .and(ForumUserFollowView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.ASC, ForumUserFollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ForumUserFollowView> productViewList = list(query, sort, pageIndex, pageSize);

        return productViewList;
	}

	

}