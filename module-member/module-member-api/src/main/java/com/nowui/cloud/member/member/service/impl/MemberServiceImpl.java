package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberPerferenceLanguage;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.mapper.MemberMapper;
import com.nowui.cloud.member.member.service.MemberAddressService;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.member.member.service.MemberPerferenceLanguageService;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 会员业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements MemberService {
    
    public static final String MEMBER_ITEM_BY_USER_ID = "member_item_by_user_id_";
    
    @Autowired
    private MemberSignatureService memberSignatureService;
    
    @Autowired
    private MemberBackgroundService memberBackgroundService;
    
    @Autowired
    private MemberAddressService memberAddressService;
    
    @Autowired
    private MemberPerferenceLanguageService memberPerferenceLanguageService;
    
    @Override
    public Integer countForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed) {
        Integer count = count(
                new BaseWrapper<Member>()
                        .eq(Member.APP_ID, appId)
                        .eqAllowEmpty(Member.MEMBER_IS_TOP, memberIsTop)
                        .eqAllowEmpty(Member.MEMBER_IS_RECOMMED, memberIsRecommed)
                        .eq(Member.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Member> listForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed, Integer pageIndex, Integer pageSize) {
        List<Member> memberList = list(
                new BaseWrapper<Member>()
                        .eq(Member.APP_ID, appId)
                        .eqAllowEmpty(Member.MEMBER_IS_TOP, memberIsTop)
                        .eqAllowEmpty(Member.MEMBER_IS_RECOMMED, memberIsRecommed)
                        .eq(Member.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Member.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberList;
    }

    @Override
    public Member findByUserId(String userId) {
        Member member = (Member) redis.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
        
        if (member == null) {
            member = find(
                    new BaseWrapper<Member>()
                    .eq(Member.USER_ID, userId)
                    .eq(Member.SYSTEM_STATUS, true)
            );
            
            if (member == null) {
                return null;
            }
            // 会员个性签名
            MemberSignature memberSignature = memberSignatureService.findByMemberId(member.getMemberId());
            member.put(Member.MEMBER_SIGNATURE, memberSignature == null ? "" : memberSignature.getMemberSignature());
            
            // 会员背景
            MemberBackground memberBackground = memberBackgroundService.findByMemberId(member.getMemberId());
            member.put(Member.MEMBER_BACKGROUND, memberBackground == null ? "" : memberBackground.getMemberBackground());

            // 会员地址
            MemberAddress memberAddress = memberAddressService.findByMemberId(member.getMemberId());
            member.put(Member.MEMBER_ADDRESS_PROVINCE, memberAddress == null ? "" : memberAddress.getMemberAddressProvince());
            member.put(Member.MEMBER_ADDRESS_CITY, memberAddress == null ? "" : memberAddress.getMemberAddressCity());
            member.put(Member.MEMBER_ADDRESS_AREA, memberAddress == null ? "" : memberAddress.getMemberAddressArea());
            
            // 会员偏好语言
            MemberPerferenceLanguage memberPerferenceLanguage = memberPerferenceLanguageService.findByMemberId(member.getMemberId());
            member.put(Member.MEMBER_PREFERENCE_LANGUAGE, memberPerferenceLanguage == null ? "" : memberPerferenceLanguage.getMemberPreferenceLanguage());
            
            redis.opsForValue().set(MEMBER_ITEM_BY_USER_ID + userId, member);
        }
        
        String userTableName = Util.getTableName(User.class);
        
        if (!Util.isNullOrEmpty(userTableName)) {
            
            User user = (User) redis.opsForValue().get(getItemCacheName(userTableName, userId));
            
            member.put(Member.MEMBER_USER, user);
        }
        
        return member;
    }

    @Override
    public Boolean saveMemberAddress(String appId, String memberId, MemberAddress memberAddress, String memberAddressId,
            String systemRequestUserId) {
        memberAddress.setAppId(appId);
        memberAddress.setMemberId(memberId);
        
        Boolean result = memberAddressService.save(memberAddress, memberAddressId, systemRequestUserId);
        
        if (result) {
            // 更新会员地址缓存
            Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
            if (member != null) {
                MemberAddress bean = memberAddressService.find(memberAddressId);
                member.put(Member.MEMBER_ADDRESS_PROVINCE, bean == null ? "" : bean.getMemberAddressProvince());
                member.put(Member.MEMBER_ADDRESS_CITY, bean == null ? "" : bean.getMemberAddressCity());
                member.put(Member.MEMBER_ADDRESS_AREA, bean == null ? "" : bean.getMemberAddressArea());
                
                redis.opsForValue().set(getItemCacheName(memberId), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberAddressByMemberId(String memberId, String systemRequestUserId) {
        memberAddressService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 更新会员地址缓存
        Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
        if (member != null) {
            member.put(Member.MEMBER_ADDRESS_PROVINCE, "");
            member.put(Member.MEMBER_ADDRESS_CITY, "");
            member.put(Member.MEMBER_ADDRESS_AREA, "");
            redis.opsForValue().set(getItemCacheName(memberId), member);
        }
    }

    @Override
    public Boolean saveMemberSignature(String appId, String memberId, MemberSignature memberSignature,
            String memberSignatureId, String systemRequestUserId) {
        memberSignature.setAppId(appId);
        memberSignature.setMemberId(memberId);
        
        Boolean result = memberSignatureService.save(memberSignature, memberSignatureId, systemRequestUserId);
        
        if (result) {
            // 更新会员签名缓存
            Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
            if (member != null) {
                MemberSignature bean = memberSignatureService.find(memberSignatureId);
                member.put(Member.MEMBER_SIGNATURE, bean == null ? "" : bean.getMemberSignature());
                
                redis.opsForValue().set(getItemCacheName(memberId), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberSignatureByMemberId(String memberId, String systemRequestUserId) {
        memberSignatureService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 更新会员签名缓存
        Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
        if (member != null) {
            member.put(Member.MEMBER_SIGNATURE, "");
            redis.opsForValue().set(getItemCacheName(memberId), member);
        }
    }

    @Override
    public Boolean saveMemberPerferenceLanguage(String appId, String memberId,
            MemberPerferenceLanguage memberPerferenceLanguage, String memberPerferenceLanguageId,
            String systemRequestUserId) {
        memberPerferenceLanguage.setAppId(appId);
        memberPerferenceLanguage.setMemberId(memberId);
        
        Boolean result = memberPerferenceLanguageService.save(memberPerferenceLanguage, memberPerferenceLanguageId, systemRequestUserId);
        
        if (result) {
            // 更新会员签名缓存
            Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
            if (member != null) {
                MemberPerferenceLanguage bean = memberPerferenceLanguageService.find(memberPerferenceLanguageId);
                member.put(Member.MEMBER_PREFERENCE_LANGUAGE, bean == null ? "" : bean.getMemberPreferenceLanguage());
                
                redis.opsForValue().set(getItemCacheName(memberId), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberPerferenceLanguageByMemberId(String memberId, String systemRequestUserId) {
        memberPerferenceLanguageService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 更新会员签名缓存
        Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
        if (member != null) {
            member.put(Member.MEMBER_PREFERENCE_LANGUAGE, "");
            redis.opsForValue().set(getItemCacheName(memberId), member);
        }
    }

    @Override
    public Boolean saveMemberBackground(String appId, String memberId, MemberBackground memberBackground,
            String memberBackgroundId, String systemRequestUserId) {
        memberBackground.setAppId(appId);
        memberBackground.setMemberId(memberId);
        
        Boolean result = memberBackgroundService.save(memberBackground, memberBackgroundId, systemRequestUserId);
        
        if (result) {
            // 更新会员背景缓存
            Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
            if (member != null) {
                MemberBackground bean = memberBackgroundService.find(memberBackgroundId);
                member.put(Member.MEMBER_BACKGROUND, bean == null ? "" : bean.getMemberBackground());
                
                redis.opsForValue().set(getItemCacheName(memberId), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberBackgroundByMemberId(String memberId, String systemRequestUserId) {
        memberBackgroundService.deleteByMemberId(memberId, systemRequestUserId);
        
        // 更新会员背景缓存
        Member member = (Member) redis.opsForValue().get(getItemCacheName(memberId));
        if (member != null) {
            member.put(Member.MEMBER_BACKGROUND, "");
            redis.opsForValue().set(getItemCacheName(memberId), member);
        }
    }
}