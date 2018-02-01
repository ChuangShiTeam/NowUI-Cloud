package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.mapper.MemberAddressMapper;
import com.nowui.cloud.member.member.service.MemberAddressService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员地址业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberAddressServiceImpl extends BaseServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressService {

    @Override
    public MemberAddress findByMemberId(String memberId) {
        MemberAddress memberAddress = find(
                new BaseWrapper<MemberAddress>()
                        .eq(MemberAddress.MEMBER_ID, memberId)
                        .eq(MemberAddress.SYSTEM_STATUS, true)
        );
        return memberAddress;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberAddress> memberAddressList = list(
                new BaseWrapper<MemberAddress>()
                        .eq(MemberAddress.MEMBER_ID, memberId)
                        .eq(MemberAddress.SYSTEM_STATUS, true)
        );
        
        if (memberAddressList != null && memberAddressList.size() > 0) {
            memberAddressList.stream()
                                .forEach(memberAddress -> delete(memberAddress.getMemberAddressId(), systemRequestUserId, memberAddress.getSystemVersion()));
        }
        
    }

}