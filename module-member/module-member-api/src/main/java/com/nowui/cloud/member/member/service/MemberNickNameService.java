package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberNickName;
import com.nowui.cloud.member.member.view.MemberNickNameView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员昵称业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberNickNameService extends BaseService<MemberNickName, MemberNickNameView> {

    /**
     * 根据会员编号查询会员昵称信息
     * 
     * @param memberId 会员编号
     * @return MemberNickName 会员昵称信息
     */
    MemberNickName findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员昵称信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}