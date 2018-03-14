package com.nowui.cloud.member.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberPreferenceLanguage;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.mapper.MemberMapper;
import com.nowui.cloud.member.member.repository.MemberRepository;
import com.nowui.cloud.member.member.service.MemberAddressService;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.member.member.service.MemberPreferenceLanguageService;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 会员业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-08
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member, MemberRepository, MemberView> implements MemberService {

    @Autowired
    private MemberSignatureService memberSignatureService;

    @Autowired
    private MemberBackgroundService memberBackgroundService;

    @Autowired
    private MemberAddressService memberAddressService;

    @Autowired
    private MemberPreferenceLanguageService memberPreferenceLanguageService;

    @Override
    public Integer countForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed) {
        Criteria criteria = Criteria.where(MemberView.APP_ID).is(appId)
                .and(MemberView.MEMBER_IS_TOP).is(memberIsTop)
                .and(MemberView.MEMBER_IS_RECOMMED).is(memberIsRecommed)
                .and(MemberView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        return count(query);
    }

    @Override
    public List<MemberView> listForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(MemberView.APP_ID).is(appId)
                .and(MemberView.MEMBER_IS_TOP).is(memberIsTop)
                .and(MemberView.MEMBER_IS_RECOMMED).is(memberIsRecommed)
                .and(MemberView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, MemberView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);
        
        return list(query, sort);
    }

    @Override
    public MemberView findByUserId(String userId) {
        Criteria criteria = Criteria.where(MemberView.USER_ID).is(userId)
                .and(MemberView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        return find(query);
    }
    
    @Override
    public List<MemberView> listByUserIds(List<String> userIds) {
        Criteria criteria = Criteria.where(MemberView.USER_ID).in(userIds)
                .and(MemberView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        return list(query);
    }

    @Override
    public Boolean updateMemberSignature(String appId, String memberId, String memberSignature,
            String systemRequestUserId) {
        MemberView memberView = find(memberId);
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        // 删除旧的会员签名
        memberSignatureService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 保存新的会员签名
        MemberSignature memberSignatureBean = new MemberSignature();
        memberSignatureBean.setAppId(appId);
        memberSignatureBean.setMemberId(memberId);
        memberSignatureBean.setMemberSignature(memberSignature);
        MemberSignature result = memberSignatureService.save(memberSignatureBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean success = false;
        
        if (result != null) {
            // 更新会员视图
            memberView.setMemberSignature(memberSignature);
            
            update(memberView);
            
            success = true;
        }
        return success;
    }

    @Override
    public Boolean updateMemberBackground(String appId, String memberId, String memberBackgroundFileId, String memberBackgroundFilePath,
            String systemRequestUserId) {
        MemberView memberView = find(memberId);
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        // 刪除旧的会员背景
        memberBackgroundService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 保存新的会员背景
        MemberBackground memberBackgroundBean = new MemberBackground();
        memberBackgroundBean.setAppId(appId);
        memberBackgroundBean.setMemberId(memberId);
        memberBackgroundBean.setMemberBackgroundFileId(memberBackgroundFileId);
        
        MemberBackground result = memberBackgroundService.save(memberBackgroundBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean success = false;
        
        if (result != null) {
            // 更新会员视图信息
            memberView.setMemberBackgroundFileId(memberBackgroundFileId);
            memberView.setMemberBackgroundFilePath(memberBackgroundFilePath);
            
            update(memberView);
            success = true;
        }
        return success;
    }

    @Override
    public Boolean updateMemberPreferenceLanguage(String appId, String memberId,
            String memberPreferenceLanguage, String systemRequestUserId) {
        MemberView memberView = find(memberId);
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        // 刪除旧的会员偏好语言
        memberPreferenceLanguageService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 保存新的会员偏好语言
        MemberPreferenceLanguage memberPreferenceLanguageBean = new MemberPreferenceLanguage();
        memberPreferenceLanguageBean.setAppId(appId);
        memberPreferenceLanguageBean.setMemberId(memberId);
        memberPreferenceLanguageBean.setMemberPreferenceLanguage(memberPreferenceLanguage);
        
        MemberPreferenceLanguage result = memberPreferenceLanguageService.save(memberPreferenceLanguageBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean success = false;
        
        if (result != null) {
            // 更新会员视图信息
            memberView.setMemberPreferenceLanguage(memberPreferenceLanguage);
            
            update(memberView);
            success = true;
        }
        return success;
    }

    @Override
    public Boolean updateMemberAddress(String appId, String memberId, MemberAddress memberAddress,
            String systemRequestUserId) {
        MemberView memberView = find(memberId);
        
        if (memberView == null) {
            throw new RuntimeException("会员不存在");
        }
        // 刪除旧的会员地址
        memberAddressService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 保存新的会员背景
        memberAddress.setAppId(appId);
        memberAddress.setMemberId(memberId);
        
        MemberAddress result = memberAddressService.save(memberAddress, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean success = false;
        
        if (result != null) {
            // 更新会员视图信息
            memberView.setMemberAddressProvince(memberAddress.getMemberAddressProvince());
            memberView.setMemberAddressCity(memberAddress.getMemberAddressCity());
            memberView.setMemberAddressArea(memberAddress.getMemberAddressArea());
            memberView.setMemberAddressAddress(memberAddress.getMemberAddressAddress());
            
            update(memberView);
            
            success = true;
        }
        return success;
    }

}