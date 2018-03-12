package com.nowui.cloud.member.member.service;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.member.member.entity.MemberDialogueRecord;
import com.nowui.cloud.member.member.view.MemberDialogueRecordView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;

/**
 * 会员对话记录业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberDialogueRecordService extends BaseService<MemberDialogueRecord,MemberDialogueRecordView> {

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

    /**
     * 根据会员对话编号统计会员对话记录数
     * 
     * @param memberDialogueId 会员对话编号
     * @return Integer 会员对话记录统计
     */
    Integer countByMemberDialogueIdForMobile(String memberDialogueId);
    
    /**
     * 根据会话对话编号查询截止对话时间会员对话记录列表
     * 
     * @param memberDialogueId 会员对话编号
     * @param systemCreateTime 创建时间
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDialogueRecord> 会员对话记录列表
     */
    List<MemberDialogueRecord> listByMemberDialogueIdForMobile(String memberDialogueId, Date systemCreateTime, Integer pageIndex, Integer pageSize);

    /**
     * 根据会话对话编号查询会员对话记录列表
     * 
     * @param memberDialogueId 会员对话编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDialogueRecord> 会员对话记录列表
     */
    List<MemberDialogueRecord> listByMemberDialogueIdForMobile(String memberDialogueId, Integer pageIndex, Integer pageSize);
}
