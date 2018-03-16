package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.view.MemberAddressView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员地址业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberAddressService extends BaseService<MemberAddress, MemberAddressView> {
    
    /**
     * 根据会员编号查询会员地址信息
     * 
     * @param memberId 会员编号
     * @return MemberAddress 会员地址信息
     */
    MemberAddress findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员地址信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}
