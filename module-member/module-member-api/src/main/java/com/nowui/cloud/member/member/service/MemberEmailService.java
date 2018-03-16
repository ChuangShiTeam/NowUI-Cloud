package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberEmail;
import com.nowui.cloud.member.member.view.MemberEmailView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员邮箱业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberEmailService extends BaseService<MemberEmail, MemberEmailView> {

    /**
     * 根据会员编号查询会员邮箱信息
     * 
     * @param memberId 会员编号
     * @return MemberEmail 会员邮箱信息
     */
    MemberEmail findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员邮箱信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);

}