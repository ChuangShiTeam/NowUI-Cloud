package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.member.member.repository.MemberInformRepository;
import com.nowui.cloud.member.member.view.MemberInformView;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberInform;
import com.nowui.cloud.member.member.mapper.MemberInformMapper;
import com.nowui.cloud.member.member.service.MemberInformService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员举报业务实现
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Service
public class MemberInformServiceImpl extends SuperServiceImpl<MemberInformMapper, MemberInform,MemberInformRepository,MemberInformView> implements MemberInformService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String userId, String informUserId, String informMemberId) {
        Integer count = count(
                new BaseWrapper<MemberInform>()
                        .eq(MemberInform.APP_ID, appId)
                        .likeAllowEmpty(MemberInform.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberInform.USER_ID, userId)
                        .likeAllowEmpty(MemberInform.INFORM_USER_ID, informUserId)
                        .likeAllowEmpty(MemberInform.INFORM_MEMBER_ID, informMemberId)
                        .eq(MemberInform.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberInform> listForAdmin(String appId, String memberId, String userId, String informUserId, String informMemberId, Integer pageIndex, Integer pageSize) {
        List<MemberInform> memberInformList = list(
                new BaseWrapper<MemberInform>()
                        .eq(MemberInform.APP_ID, appId)
                        .likeAllowEmpty(MemberInform.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberInform.USER_ID, userId)
                        .likeAllowEmpty(MemberInform.INFORM_USER_ID, informUserId)
                        .likeAllowEmpty(MemberInform.INFORM_MEMBER_ID, informMemberId)
                        .eq(MemberInform.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberInform.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberInformList;
    }

}