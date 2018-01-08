package com.nowui.cloud.base.member.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.member.entity.MemberDialogue;
import com.nowui.cloud.base.member.mapper.MemberDialogueMapper;
import com.nowui.cloud.base.member.service.MemberDialogueService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 会员对话业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberDialogueServiceImpl extends BaseServiceImpl<MemberDialogueMapper, MemberDialogue> implements MemberDialogueService {

    @Override
    public Integer adminCount(String appId, String initiateMemberId, String initiateUserId, String respondMemberId, String respondUserId) {
        Integer count = count(
                new BaseWrapper<MemberDialogue>()
                        .eq(MemberDialogue.APP_ID, appId)
                        .likeAllowEmpty(MemberDialogue.INITIATE_MEMBER_ID, initiateMemberId)
                        .likeAllowEmpty(MemberDialogue.INITIATE_USER_ID, initiateUserId)
                        .likeAllowEmpty(MemberDialogue.RESPOND_MEMBER_ID, respondMemberId)
                        .likeAllowEmpty(MemberDialogue.RESPOND_USER_ID, respondUserId)
                        .eq(MemberDialogue.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberDialogue> adminList(String appId, String initiateMemberId, String initiateUserId, String respondMemberId, String respondUserId, Integer pageIndex, Integer pageSize) {
        List<MemberDialogue> memberDialogueList = list(
                new BaseWrapper<MemberDialogue>()
                        .eq(MemberDialogue.APP_ID, appId)
                        .likeAllowEmpty(MemberDialogue.INITIATE_MEMBER_ID, initiateMemberId)
                        .likeAllowEmpty(MemberDialogue.INITIATE_USER_ID, initiateUserId)
                        .likeAllowEmpty(MemberDialogue.RESPOND_MEMBER_ID, respondMemberId)
                        .likeAllowEmpty(MemberDialogue.RESPOND_USER_ID, respondUserId)
                        .eq(MemberDialogue.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberDialogue.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberDialogueList;
    }

}