package com.nowui.cloud.member.member.service;
import java.util.List;

import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberPerferenceLanguage;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 会员业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberService extends SuperService<Member,MemberView> {

    /**
     * 会员统计
     *
     * @param appId 应用编号
     * @param memberIsTop 是否置顶
     * @param memberIsRecommed 会员是否推荐
     * @return Integer 会员统计
     */
    Integer countForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed);

    /**
     * 会员列表
     *
     * @param appId 应用编号
     * @param memberIsTop 是否置顶
     * @param memberIsRecommed 会员是否推荐
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Member> 会员列表
     */
    List<Member> listForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户编号查询会员信息
     * 
     * @param userId 用户编号
     * @return Member 会员信息
     */
    Member findByUserId(String userId);
    
    /**
     * 保存会员地址信息
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param memberAddress 会员地址
     * @param memberAddressId 会员地址编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveMemberAddress(String appId, String memberId, String userId, MemberAddress memberAddress, String memberAddressId, String systemRequestUserId);
    
    /**
     * 根据会员编号删除会员地址信息
     * 
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteMemberAddressByMemberId(String memberId, String userId, String systemRequestUserId);
    
    /**
     * 保存会员签名信息
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param memberSignature 会员签名
     * @param memberSignatureId 会员签名编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveMemberSignature(String appId, String memberId, String userId, MemberSignature memberSignature, String memberSignatureId, String systemRequestUserId);
    
    /**
     * 根据会员编号删除会员签名信息
     * 
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteMemberSignatureByMemberId(String memberId, String userId, String systemRequestUserId);
    
    /**
     * 保存会员偏好语言信息
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param memberPerferenceLanguage 会员偏好语言
     * @param memberPerferenceLanguageId 会员偏好语言编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveMemberPerferenceLanguage(String appId, String memberId, String userId, MemberPerferenceLanguage memberPerferenceLanguage, String memberPerferenceLanguageId, String systemRequestUserId);
    
    /**
     * 根据会员编号删除会员偏好语言信息
     * 
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteMemberPerferenceLanguageByMemberId(String memberId, String userId, String systemRequestUserId);
    
    /**
     * 保存会员背景信息
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param memberBackground 会员背景
     * @param memberBackgroundId 会员背景编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveMemberBackground(String appId, String memberId, String userId, MemberBackground memberBackground, String memberBackgroundId, String systemRequestUserId);
    
    /**
     * 根据会员编号删除会员背景信息
     * 
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteMemberBackgroundByMemberId(String memberId, String userId, String systemRequestUserId);
    
}
