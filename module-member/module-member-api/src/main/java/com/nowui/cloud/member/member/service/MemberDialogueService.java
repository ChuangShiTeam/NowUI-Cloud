package com.nowui.cloud.member.member.service;
import java.util.List;

import com.nowui.cloud.member.member.entity.MemberDialogue;
import com.nowui.cloud.member.member.view.MemberDialogueView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 会员对话业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberDialogueService extends SuperService<MemberDialogue,MemberDialogueView> {

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
    
    /**
     * 根据请求用户编号和响应用户编号查询会员对话信息
     * 
     * @param initiateUserId 发起用户编号
     * @param respondUserId 响应用户编号
     * @return MemberDialogue 会员对话信息
     */
    MemberDialogue findByInitiateUserIdAndRespondUserId(String initiateUserId, String respondUserId);
    
}
