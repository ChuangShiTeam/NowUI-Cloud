package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberWechat;
import com.nowui.cloud.member.member.view.MemberWechatView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员微信业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberWechatService extends BaseService<MemberWechat, MemberWechatView> {

    /**
     * 根据会员编号查询会员性别信息
     * 
     * @param memberId 会员编号
     * @return MemberWechat 会员微信信息
     */
    MemberWechat findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员微信信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}