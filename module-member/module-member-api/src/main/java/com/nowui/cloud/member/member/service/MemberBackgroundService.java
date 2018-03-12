package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.view.MemberBackgroundView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;

/**
 * 会员背景业务接口
 *
 * @author marcus
 *
 * 2018-01-14
 */
public interface MemberBackgroundService extends BaseService<MemberBackground,MemberBackgroundView> {

    /**
     * 根据会员编号查询会员背景信息
     * 
     * @param memberId 会员编号
     * @return MemberBackground 会员背景    信息
     */
    MemberBackground findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员背景信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}
