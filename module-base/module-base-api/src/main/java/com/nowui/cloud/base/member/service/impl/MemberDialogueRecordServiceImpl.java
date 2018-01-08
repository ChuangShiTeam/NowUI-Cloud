package com.nowui.cloud.base.member.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.member.entity.MemberDialogueRecord;
import com.nowui.cloud.base.member.mapper.MemberDialogueRecordMapper;
import com.nowui.cloud.base.member.service.MemberDialogueRecordService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 会员对话记录业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberDialogueRecordServiceImpl extends BaseServiceImpl<MemberDialogueRecordMapper, MemberDialogueRecord> implements MemberDialogueRecordService {

    @Override
    public Integer adminCount(String appId, String memberDialogueId, String memberId, String userId) {
        Integer count = count(
                new BaseWrapper<MemberDialogueRecord>()
                        .eq(MemberDialogueRecord.APP_ID, appId)
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_DIALOGUE_ID, memberDialogueId)
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberDialogueRecord.USER_ID, userId)
                        .eq(MemberDialogueRecord.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberDialogueRecord> adminList(String appId, String memberDialogueId, String memberId, String userId, Integer pageIndex, Integer pageSize) {
        List<MemberDialogueRecord> memberDialogueRecordList = list(
                new BaseWrapper<MemberDialogueRecord>()
                        .eq(MemberDialogueRecord.APP_ID, appId)
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_DIALOGUE_ID, memberDialogueId)
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberDialogueRecord.USER_ID, userId)
                        .eq(MemberDialogueRecord.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberDialogueRecord.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberDialogueRecordList;
    }

}