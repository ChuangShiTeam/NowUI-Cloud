package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import com.nowui.cloud.member.member.repository.MemberAddressRepository;
import com.nowui.cloud.member.member.router.MemberAddressRouter;
import com.nowui.cloud.member.member.view.MemberAddressView;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.mapper.MemberAddressMapper;
import com.nowui.cloud.member.member.service.MemberAddressService;
import com.nowui.cloud.mybatisplus.BaseWrapper;

/**
 * 会员地址业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-08
 */
@Service
public class MemberAddressServiceImpl extends SuperServiceImpl<MemberAddressMapper, MemberAddress, MemberAddressRepository, MemberAddressView> implements MemberAddressService {

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
            memberAddressList.stream().forEach(memberAddress -> delete(memberAddress.getMemberAddressId(), systemRequestUserId, memberAddress.getSystemVersion()));
        }

    }

}