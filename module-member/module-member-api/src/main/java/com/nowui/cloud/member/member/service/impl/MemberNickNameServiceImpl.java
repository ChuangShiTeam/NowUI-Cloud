package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberNickName;
import com.nowui.cloud.member.member.mapper.MemberNickNameMapper;
import com.nowui.cloud.member.member.repository.MemberNickNameRepository;
import com.nowui.cloud.member.member.service.MemberNickNameService;
import com.nowui.cloud.member.member.view.MemberNickNameView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员昵称业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberNickNameServiceImpl extends BaseServiceImpl<MemberNickNameMapper, MemberNickName, MemberNickNameRepository, MemberNickNameView> implements MemberNickNameService {

    @Override
    public MemberNickName findByMemberId(String memberId) {
        MemberNickName memberNickName = find(
                new BaseWrapper<MemberNickName>()
                        .eq(MemberNickNameView.MEMBER_ID, memberId)
                        .eq(MemberNickNameView.SYSTEM_STATUS, true)
        );
        return memberNickName;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberNickName> memberNickNameList = list(
                new BaseWrapper<MemberNickName>()
                        .eq(MemberNickNameView.MEMBER_ID, memberId)
                        .eq(MemberNickNameView.SYSTEM_STATUS, true)
        );

        if (memberNickNameList != null && memberNickNameList.size() > 0) {
            memberNickNameList.stream().forEach(memberNickName -> delete(memberNickName.getMemberNickNameId(), memberNickName.getAppId(), systemRequestUserId, memberNickName.getSystemVersion()));
        }
        
    }

}