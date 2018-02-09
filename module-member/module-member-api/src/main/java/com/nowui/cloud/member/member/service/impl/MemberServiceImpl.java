package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.member.member.repository.MemberRepository;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.service.impl.SuperServiceImpl;
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
import com.nowui.cloud.util.Util;

/**
 * 会员业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-08
 */
@Service
public class MemberServiceImpl extends SuperServiceImpl<MemberMapper, Member, MemberRepository, MemberView> implements MemberService {

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
        Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);

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
            member.put(Member.MEMBER_ADDRESS_ADDRESS, memberAddress == null ? "" : memberAddress.getMemberAddressAddress());

            // 会员偏好语言
            MemberPerferenceLanguage memberPerferenceLanguage = memberPerferenceLanguageService.findByMemberId(member.getMemberId());
            member.put(Member.MEMBER_PREFERENCE_LANGUAGE, memberPerferenceLanguage == null ? "" : memberPerferenceLanguage.getMemberPreferenceLanguage());

            redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + userId, member);
        }

        String userTableName = Util.getTableName(User.class);

        if (!Util.isNullOrEmpty(userTableName)) {

            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userTableName, userId));

            member.put(Member.MEMBER_USER, user);
        }

        return member;
    }

    @Override
    public MemberAddress saveMemberAddress(String appId, String memberId, String userId, MemberAddress memberAddress, String memberAddressId,
                                           String systemRequestUserId) {
        memberAddress.setAppId(appId);
        memberAddress.setMemberId(memberId);

        MemberAddress result = memberAddressService.save(memberAddress, memberAddressId, systemRequestUserId);

        if (result != null) {
            // 更新会员地址缓存
            Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
            if (member != null) {
                member.put(Member.MEMBER_ADDRESS_PROVINCE, memberAddress.getMemberAddressProvince());
                member.put(Member.MEMBER_ADDRESS_CITY, memberAddress.getMemberAddressCity());
                member.put(Member.MEMBER_ADDRESS_AREA, memberAddress.getMemberAddressArea());
                member.put(Member.MEMBER_ADDRESS_ADDRESS, memberAddress.getMemberAddressAddress());

                redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberAddressByMemberId(String memberId, String userId, String systemRequestUserId) {
        memberAddressService.deleteByMemberId(memberId, systemRequestUserId);

        // 更新会员地址缓存
        Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
        if (member != null) {
            member.put(Member.MEMBER_ADDRESS_PROVINCE, "");
            member.put(Member.MEMBER_ADDRESS_CITY, "");
            member.put(Member.MEMBER_ADDRESS_AREA, "");
            member.put(Member.MEMBER_ADDRESS_ADDRESS, "");
            redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
        }
    }

    @Override
    public MemberSignature saveMemberSignature(String appId, String memberId, String userId, MemberSignature memberSignature,
                                               String memberSignatureId, String systemRequestUserId) {
        memberSignature.setAppId(appId);
        memberSignature.setMemberId(memberId);

        MemberSignature result = memberSignatureService.save(memberSignature, memberSignatureId, systemRequestUserId);
//        memberSignature, memberSignatureId, systemRequestUserId
        if (result != null) {
            // 更新会员签名缓存
            Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
            if (member != null) {
                member.put(Member.MEMBER_SIGNATURE, memberSignature.getMemberSignature());

                redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberSignatureByMemberId(String memberId, String userId, String systemRequestUserId) {
        memberSignatureService.deleteByMemberId(memberId, systemRequestUserId);

        // 更新会员签名缓存
        Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
        if (member != null) {
            member.put(Member.MEMBER_SIGNATURE, "");
            redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
        }
    }

    @Override
    public MemberPerferenceLanguage saveMemberPerferenceLanguage(String appId, String memberId, String userId,
                                                                 MemberPerferenceLanguage memberPerferenceLanguage, String memberPerferenceLanguageId,
                                                                 String systemRequestUserId) {
        memberPerferenceLanguage.setAppId(appId);
        memberPerferenceLanguage.setMemberId(memberId);

        MemberPerferenceLanguage result = memberPerferenceLanguageService.save(memberPerferenceLanguage, memberPerferenceLanguageId, systemRequestUserId);

//        memberPerferenceLanguage, memberPerferenceLanguageId, systemRequestUserId
        if (result != null) {
            // 更新会员签名缓存
            Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
            if (member != null) {
                member.put(Member.MEMBER_PREFERENCE_LANGUAGE, memberPerferenceLanguage.getMemberPreferenceLanguage());

                redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberPerferenceLanguageByMemberId(String memberId, String userId, String systemRequestUserId) {
        memberPerferenceLanguageService.deleteByMemberId(memberId, systemRequestUserId);

        // 更新会员签名缓存
        Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
        if (member != null) {
            member.put(Member.MEMBER_PREFERENCE_LANGUAGE, "");
            redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
        }
    }

    @Override
    public MemberBackground saveMemberBackground(String appId, String memberId, String userId, MemberBackground memberBackground,
                                                 String memberBackgroundId, String systemRequestUserId) {
        memberBackground.setAppId(appId);
        memberBackground.setMemberId(memberId);

        MemberBackground result = memberBackgroundService.save(memberBackground, memberBackgroundId, systemRequestUserId);
//        memberBackground, memberBackgroundId, systemRequestUserId
        if (result != null) {
            // 更新会员背景缓存
            Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
            if (member != null) {
                member.put(Member.MEMBER_BACKGROUND, memberBackground.getMemberBackground());

                redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
            }
        }
        return result;
    }

    @Override
    public void deleteMemberBackgroundByMemberId(String memberId, String userId, String systemRequestUserId) {
        memberBackgroundService.deleteByMemberId(memberId, systemRequestUserId);

        // 更新会员背景缓存
        Member member = (Member) redisTemplate.opsForValue().get(MEMBER_ITEM_BY_USER_ID + userId);
        if (member != null) {
            member.put(Member.MEMBER_BACKGROUND, "");
            redisTemplate.opsForValue().set(MEMBER_ITEM_BY_USER_ID + member.getUserId(), member);
        }
    }
}