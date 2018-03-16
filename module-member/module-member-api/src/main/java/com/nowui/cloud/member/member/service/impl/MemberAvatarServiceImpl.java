package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberAvatar;
import com.nowui.cloud.member.member.mapper.MemberAvatarMapper;
import com.nowui.cloud.member.member.repository.MemberAvatarRepository;
import com.nowui.cloud.member.member.service.MemberAvatarService;
import com.nowui.cloud.member.member.view.MemberAvatarView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员头像业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberAvatarServiceImpl extends BaseServiceImpl<MemberAvatarMapper, MemberAvatar, MemberAvatarRepository, MemberAvatarView> implements MemberAvatarService {

    @Override
    public MemberAvatar findByMemberId(String memberId) {
        MemberAvatar memberAvatar = find(
                new BaseWrapper<MemberAvatar>()
                        .eq(MemberAvatarView.MEMBER_ID, memberId)
                        .eq(MemberAvatarView.SYSTEM_STATUS, true)
        );
        return memberAvatar;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberAvatar> memberAvatarList = list(
                new BaseWrapper<MemberAvatar>()
                        .eq(MemberAvatarView.MEMBER_ID, memberId)
                        .eq(MemberAvatarView.SYSTEM_STATUS, true)
        );

        if (memberAvatarList != null && memberAvatarList.size() > 0) {
            memberAvatarList.stream().forEach(memberAvatar -> delete(memberAvatar.getMemberAvatarId(), memberAvatar.getAppId(), systemRequestUserId, memberAvatar.getSystemVersion()));
        }
        
    }

}