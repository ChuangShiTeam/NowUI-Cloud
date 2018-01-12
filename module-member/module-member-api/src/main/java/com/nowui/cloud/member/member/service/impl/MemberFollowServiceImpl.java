package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.mapper.MemberFollowMapper;
import com.nowui.cloud.member.member.service.MemberFollowService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员关注业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class MemberFollowServiceImpl extends BaseServiceImpl<MemberFollowMapper, MemberFollow> implements MemberFollowService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String userId) {
        Integer count = count(
                new BaseWrapper<MemberFollow>()
                        .eq(MemberFollow.APP_ID, appId)
                        .likeAllowEmpty(MemberFollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberFollow.USER_ID, userId)
                        .eq(MemberFollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberFollow> listForAdmin(String appId, String memberId, String userId, Integer pageIndex, Integer pageSize) {
        List<MemberFollow> memberFollowList = list(
                new BaseWrapper<MemberFollow>()
                        .eq(MemberFollow.APP_ID, appId)
                        .likeAllowEmpty(MemberFollow.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberFollow.USER_ID, userId)
                        .eq(MemberFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberFollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberFollowList;
    }

}