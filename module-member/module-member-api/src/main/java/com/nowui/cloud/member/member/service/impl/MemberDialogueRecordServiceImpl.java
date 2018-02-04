package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.member.member.repository.MemberDialogueRecordRepository;
import com.nowui.cloud.member.member.router.MemberDialogueRecordRouter;
import com.nowui.cloud.member.member.view.MemberDialogueRecordView;
import com.nowui.cloud.member.member.view.MemberDialogueView;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberDialogueRecord;
import com.nowui.cloud.member.member.mapper.MemberDialogueRecordMapper;
import com.nowui.cloud.member.member.service.MemberDialogueRecordService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员对话记录业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberDialogueRecordServiceImpl extends
        SuperServiceImpl<MemberDialogueRecordMapper, MemberDialogueRecord,MemberDialogueRecordRepository,MemberDialogueRecordView> implements MemberDialogueRecordService {

    @Override
    public Integer countForAdmin(String appId, String memberDialogueId, String memberId, String userId) {
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
    public List<MemberDialogueRecord> listForAdmin(String appId, String memberDialogueId, String memberId, String userId, Integer pageIndex, Integer pageSize) {
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
    
    @Override
    public Integer countByMemberDialogueIdForMobile(String memberDialogueId) {
        Integer count = count(
                new BaseWrapper<MemberDialogueRecord>()
                        .eq(MemberDialogueRecord.MEMBER_DIALOGUE_ID, memberDialogueId)
                        .eq(MemberDialogueRecord.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberDialogueRecord> listByMemberDialogueIdForMobile(String memberDialogueId, Date systemCreateTime, Integer pageIndex,
            Integer pageSize) {
        List<MemberDialogueRecord> memberDialogueRecordList = list(
                new BaseWrapper<MemberDialogueRecord>()
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_DIALOGUE_ID, memberDialogueId)
                        .eq(MemberDialogueRecord.SYSTEM_STATUS, true)
                        .gt(MemberDialogueRecord.SYSTEM_CREATE_TIME, systemCreateTime)
                        .orderDesc(Arrays.asList(MemberDialogueRecord.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberDialogueRecordList;
    }

    @Override
    public List<MemberDialogueRecord> listByMemberDialogueIdForMobile(String memberDialogueId, Integer pageIndex,
            Integer pageSize) {
        List<MemberDialogueRecord> memberDialogueRecordList = list(
                new BaseWrapper<MemberDialogueRecord>()
                        .likeAllowEmpty(MemberDialogueRecord.MEMBER_DIALOGUE_ID, memberDialogueId)
                        .eq(MemberDialogueRecord.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberDialogueRecord.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberDialogueRecordList;
    }

}