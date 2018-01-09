package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.MemberDialogueRecord;

import java.util.List;

/**
 * 会员对话记录业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberDialogueRecordService extends BaseService<MemberDialogueRecord> {

    /**
     * 会员对话记录统计
     *
     * @param appId 应用编号
     * @param memberDialogueId 会员对话编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @return Integer 会员对话记录统计
     */
    Integer countForAdmin(String appId, String memberDialogueId, String memberId, String userId);

    /**
     * 会员对话记录列表
     *
     * @param appId 应用编号
     * @param memberDialogueId 会员对话编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDialogueRecord> 会员对话记录列表
     */
    List<MemberDialogueRecord> listForAdmin(String appId, String memberDialogueId, String memberId, String userId, Integer pageIndex, Integer pageSize);
}
