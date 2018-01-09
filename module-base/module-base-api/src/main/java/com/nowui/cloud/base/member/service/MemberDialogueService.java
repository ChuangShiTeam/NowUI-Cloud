package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.MemberDialogue;

import java.util.List;

/**
 * 会员对话业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberDialogueService extends BaseService<MemberDialogue> {

    /**
     * 会员对话统计
     *
     * @param appId 应用编号
     * @param initiateMemberId 发起会员编号
     * @param initiateUserId 发起用户编号
     * @param respondMemberId 响应会员编号
     * @param respondUserId 响应用户编号
     * @return Integer 会员对话统计
     */
    Integer countForAdmin(String appId, String initiateMemberId, String initiateUserId, String respondMemberId, String respondUserId);

    /**
     * 会员对话列表
     *
     * @param appId 应用编号
     * @param initiateMemberId 发起会员编号
     * @param initiateUserId 发起用户编号
     * @param respondMemberId 响应会员编号
     * @param respondUserId 响应用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDialogue> 会员对话列表
     */
    List<MemberDialogue> listForAdmin(String appId, String initiateMemberId, String initiateUserId, String respondMemberId, String respondUserId, Integer pageIndex, Integer pageSize);
}
