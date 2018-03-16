package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberMobile;
import com.nowui.cloud.member.member.view.MemberMobileView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员手机号码业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberMobileService extends BaseService<MemberMobile, MemberMobileView> {

    /**
     * 根据会员编号查询会员手机号码信息
     * 
     * @param memberId 会员编号
     * @return MemberMobile 会员手机号码信息
     */
    MemberMobile findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员手机号码信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}