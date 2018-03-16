package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberPassword;
import com.nowui.cloud.member.member.view.MemberPasswordView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员密码业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberPasswordService extends BaseService<MemberPassword, MemberPasswordView> {

    /**
     * 根据会员编号查询会员密码信息
     * 
     * @param memberId 会员编号
     * @return MemberPassword 会员密码信息
     */
    MemberPassword findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员密码信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
    
}