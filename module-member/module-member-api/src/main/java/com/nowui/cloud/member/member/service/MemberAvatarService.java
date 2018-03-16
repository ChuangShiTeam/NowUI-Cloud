package com.nowui.cloud.member.member.service;

import com.nowui.cloud.member.member.entity.MemberAvatar;
import com.nowui.cloud.member.member.view.MemberAvatarView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员头像业务接口
 *
 * @author marcus
 *
 * 2018-03-16
 */
public interface MemberAvatarService extends BaseService<MemberAvatar, MemberAvatarView> {

    /**
     * 根据会员编号查询会员头像信息
     * 
     * @param memberId 会员编号
     * @return MemberAvatar 会员头像信息
     */
    MemberAvatar findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员头像信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}