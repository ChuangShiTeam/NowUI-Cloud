package com.nowui.cloud.member.member.service;
import java.util.List;

import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberService extends BaseService<Member,MemberView> {

    /**
     * 会员统计
     *
     * @param appId 应用编号
     * @param memberIsTop 是否置顶
     * @param memberIsRecommed 会员是否推荐
     * @return Integer 会员视图统计
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
     * @return List<MemberView> 会员视图列表
     */
    List<MemberView> listForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户编号查询会员信息
     * 
     * @param userId 用户编号
     * @return MemberView 会员视图信息
     */
    MemberView findByUserId(String userId);
    
    /**
     * 批量查询会员信息
     * 
     * @param userIds 用户编号集合
     * @return List<MemberView> 会员视图列表信息
     */
    List<MemberView> listByUserIds(List<String> userIds);
    
    /**
     * 更新会员签名
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberSignature 会员签名
     * @param systemRequestUserId 请求用户编号
     * @return MemberSignature
     */
    Boolean updateMemberSignature(String appId, String memberId, String memberSignature, String systemRequestUserId);
    
    /**
     * 更新会员背景
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberBackground 会员背景
     * @param memberBackgroundFileId 会员背景文件编号
     * @param memberBackgroundFilePath 会员背景文件路径
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 不成功
     */
    Boolean updateMemberBackground(String appId, String memberId, String memberBackgroundFileId, String memberBackgroundFilePath, String systemRequestUserId);
    
    /**
     * 更新会员偏好语言
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberPreferenceLanguage 会员偏好语言
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 不成功
     */
    Boolean updateMemberPreferenceLanguage(String appId, String memberId, String memberPreferenceLanguage, String systemRequestUserId);

    /**
     * 更新会员地址
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberAddress 会员地址
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 不成功
     */
    Boolean updateMemberAddress(String appId, String memberId, MemberAddress memberAddress, String systemRequestUserId);
    
}
