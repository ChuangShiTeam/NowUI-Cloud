package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberWechat;
import com.nowui.cloud.member.member.mapper.MemberWechatMapper;
import com.nowui.cloud.member.member.repository.MemberWechatRepository;
import com.nowui.cloud.member.member.service.MemberWechatService;
import com.nowui.cloud.member.member.view.MemberWechatView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员微信业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberWechatServiceImpl extends BaseServiceImpl<MemberWechatMapper, MemberWechat, MemberWechatRepository, MemberWechatView> implements MemberWechatService {

    @Override
    public MemberWechat findByMemberId(String memberId) {
        MemberWechat memberWechat = find(
                new BaseWrapper<MemberWechat>()
                        .eq(MemberWechatView.MEMBER_ID, memberId)
                        .eq(MemberWechatView.SYSTEM_STATUS, true)
        );
        return memberWechat;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberWechat> memberWechatList = list(
                new BaseWrapper<MemberWechat>()
                        .eq(MemberWechatView.MEMBER_ID, memberId)
                        .eq(MemberWechatView.SYSTEM_STATUS, true)
        );

        if (memberWechatList != null && memberWechatList.size() > 0) {
            memberWechatList.stream().forEach(memberWechat -> delete(memberWechat.getMemberWechatId(), memberWechat.getAppId(), systemRequestUserId, memberWechat.getSystemVersion()));
        }
    }
}