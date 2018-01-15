package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.mapper.MemberBackgroundMapper;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员背景业务实现
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Service
public class MemberBackgroundServiceImpl extends BaseServiceImpl<MemberBackgroundMapper, MemberBackground> implements MemberBackgroundService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String memberBackgroundFileId) {
        Integer count = count(
                new BaseWrapper<MemberBackground>()
                        .eq(MemberBackground.APP_ID, appId)
                        .likeAllowEmpty(MemberBackground.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBackground.MEMBER_BACKGROUND_FILE_ID, memberBackgroundFileId)
                        .eq(MemberBackground.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberBackground> listForAdmin(String appId, String memberId, String memberBackgroundFileId, Integer pageIndex, Integer pageSize) {
        List<MemberBackground> memberBackgroudList = list(
                new BaseWrapper<MemberBackground>()
                        .eq(MemberBackground.APP_ID, appId)
                        .likeAllowEmpty(MemberBackground.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBackground.MEMBER_BACKGROUND_FILE_ID, memberBackgroundFileId)
                        .eq(MemberBackground.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberBackground.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberBackgroudList;
    }

}