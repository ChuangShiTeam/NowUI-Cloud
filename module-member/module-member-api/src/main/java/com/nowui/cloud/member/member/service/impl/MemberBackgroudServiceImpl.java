package com.nowui.cloud.member.member.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.member.member.entity.MemberBackgroud;
import com.nowui.cloud.member.member.mapper.MemberBackgroudMapper;
import com.nowui.cloud.member.member.service.MemberBackgroudService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 会员背景业务实现
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Service
public class MemberBackgroudServiceImpl extends BaseServiceImpl<MemberBackgroudMapper, MemberBackgroud> implements MemberBackgroudService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String memberBackgroundFileId) {
        Integer count = count(
                new BaseWrapper<MemberBackgroud>()
                        .eq(MemberBackgroud.APP_ID, appId)
                        .likeAllowEmpty(MemberBackgroud.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBackgroud.MEMBER_BACKGROUND_FILE_ID, memberBackgroundFileId)
                        .eq(MemberBackgroud.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberBackgroud> listForAdmin(String appId, String memberId, String memberBackgroundFileId, Integer pageIndex, Integer pageSize) {
        List<MemberBackgroud> memberBackgroudList = list(
                new BaseWrapper<MemberBackgroud>()
                        .eq(MemberBackgroud.APP_ID, appId)
                        .likeAllowEmpty(MemberBackgroud.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBackgroud.MEMBER_BACKGROUND_FILE_ID, memberBackgroundFileId)
                        .eq(MemberBackgroud.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberBackgroud.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberBackgroudList;
    }

}