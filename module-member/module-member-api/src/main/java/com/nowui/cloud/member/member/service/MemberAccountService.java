package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberAccount;
import com.nowui.cloud.member.member.view.MemberAccountView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员账号业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberAccountService extends BaseService<MemberAccount, MemberAccountView> {

    /**
     * 根据会员编号查询会员账号信息
     * 
     * @param memberId 会员编号
     * @return MemberAccount 会员账号信息
     */
    MemberAccount findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员账号信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);

}