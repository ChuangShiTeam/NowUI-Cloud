package com.nowui.cloud.sns.member.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.member.entity.MemberFollow;
import com.nowui.cloud.sns.member.mapper.MemberFollowMapper;
import com.nowui.cloud.sns.member.repository.MemberFollowRepository;
import com.nowui.cloud.sns.member.service.MemberFollowService;
import com.nowui.cloud.sns.member.view.MemberFollowView;

/**
 * 会员关注业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class MemberFollowServiceImpl extends SuperServiceImpl<MemberFollowMapper, MemberFollow, MemberFollowRepository, MemberFollowView> implements MemberFollowService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String userId) {
        Integer count = count(
                new BaseWrapper<MemberFollow>()
                        .eq(MemberFollow.APP_ID, appId)
                        .eqAllowEmpty(MemberFollow.MEMBER_ID, memberId)
                        .eqAllowEmpty(MemberFollow.USER_ID, userId)
                        .eq(MemberFollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberFollow> listForAdmin(String appId, String memberId, String userId, Integer pageIndex, Integer pageSize) {
        List<MemberFollow> memberFollowList = list(
                new BaseWrapper<MemberFollow>()
                        .eq(MemberFollow.APP_ID, appId)
                        .eqAllowEmpty(MemberFollow.MEMBER_ID, memberId)
                        .eqAllowEmpty(MemberFollow.USER_ID, userId)
                        .eq(MemberFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberFollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
        return memberFollowList;
    }

    @Override
    public Boolean checkIsFollow(String userId, String followUserId) {
        Criteria criteria = Criteria.where(MemberFollowView.USER_ID).is(userId)
                .and(MemberFollowView.FOLLOW_USER_ID).is(followUserId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        Integer count = count(query);
        
        return count > 0;
    }

    @Override
    public Integer countFollow(String userId) {
        Criteria criteria = Criteria.where(MemberFollowView.USER_ID).is(userId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        Integer count = count(query);
        
        return count;
    }

    @Override
    public Integer countBeFollowed(String userId) {
        Criteria criteria = Criteria.where(MemberFollowView.FOLLOW_USER_ID).is(userId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        Integer count = count(query);
        
        return count;
    }

    @Override
    public List<MemberFollowView> listByUserId(String userId) {
        Criteria criteria = Criteria.where(MemberFollowView.USER_ID).is(userId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, MemberFollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        Query query = new Query(criteria);
        query.with(sort);
        
        List<MemberFollowView> memberFollowViewList = list(query, sort);

        return memberFollowViewList;
    }
    
    @Override
    public List<MemberFollowView> listByMemberId(String memberId) {
        Criteria criteria = Criteria.where(MemberFollowView.MEMBER_ID).is(memberId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, MemberFollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        Query query = new Query(criteria);
        query.with(sort);
        
        List<MemberFollowView> memberFollowViewList = list(query, sort);

        return memberFollowViewList;
    }

    @Override
    public List<MemberFollowView> listByFollowUserId(String followUserId) {
        Criteria criteria = Criteria.where(MemberFollowView.FOLLOW_USER_ID).is(followUserId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, MemberFollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        Query query = new Query(criteria);
        query.with(sort);
        
        List<MemberFollowView> memberFollowViewList = list(query, sort);

        return memberFollowViewList;
    }
    
    @Override
    public List<MemberFollowView> listByFollowMemberId(String followMemberId) {
        Criteria criteria = Criteria.where(MemberFollowView.FOLLOW_MEMBER_ID).is(followMemberId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, MemberFollowView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        Query query = new Query(criteria);
        query.with(sort);
        
        List<MemberFollowView> memberFollowViewList = list(query, sort);

        return memberFollowViewList;
    }

    @Override
    public MemberFollowView findByUserIdAndFollowUserId(String userId, String followUserId) {
        Criteria criteria = Criteria.where(MemberFollowView.USER_ID).is(userId)
                .and(MemberFollowView.FOLLOW_USER_ID).is(followUserId)
                .and(MemberFollowView.SYSTEM_STATUS).is(true);
        
        MemberFollowView memberFollowView = find(new Query(criteria));
        
        return memberFollowView;
    }

}