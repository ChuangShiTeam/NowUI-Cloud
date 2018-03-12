package com.nowui.cloud.sns.member.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.member.entity.MemberFollow;
import com.nowui.cloud.sns.member.view.MemberFollowView;

/**
 * 会员关注业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface MemberFollowService extends BaseService<MemberFollow,MemberFollowView> {

    /**
     * 会员关注统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @return Integer 会员关注统计
     */
    Integer countForAdmin(String appId, String memberId, String userId);

    /**
     * 会员关注列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberFollow> 会员关注列表
     */
    List<MemberFollow> listForAdmin(String appId, String memberId, String userId, Integer pageIndex, Integer pageSize);

    /**
     * 验证用户是否关注了用户
     * 
     * @param userId 用户编号
     * @param followUserId 关注用户编号
     * @return Boolean true 是   false 否
     */
    Boolean checkIsFollow(String userId, String followUserId);
    
    /**
     * 统计会员关注数
     * 
     * @param userId 用户编号
     * @return Integer 会员关注数
     */
    Integer countFollow(String userId);
    
    /**
     * 统计会员被关注数
     * 
     * @param userId 用户编号
     * @return 会员被关注数
     */
    Integer countBeFollowed(String userId);
    
    /**
     * 会员关注列表
     *
     * @param userId 用户编号
     * @return List<MemberFollowView> 会员关注视图列表
     */
    List<MemberFollowView> listByUserId(String userId);
    
    /**
     * 会员关注列表
     *
     * @param memberId 会员编号
     * @return List<MemberFollowView> 会员关注视图列表
     */
    List<MemberFollowView> listByMemberId(String memberId);
    
    /**
     * 会员被关注列表
     *
     * @param followUserId 被关注用户编号
     * @return List<MemberFollowView> 会员关注视图列表
     */
    List<MemberFollowView> listByFollowUserId(String followUserId);
    
    /**
     * 会员被关注列表
     *
     * @param followMemberId 被关注会员编号
     * @return List<MemberFollowView> 会员关注视图列表
     */
    List<MemberFollowView> listByFollowMemberId(String followMemberId);
    
    /**
     * 根据用户编号和被关注用户编号查询会员关注
     * 
     * @param userId 操作者用户编号
     * @param befollowUserId 被关注者用户编号
     * @return MemberFollowView 会员关注视图信息
     */
    MemberFollowView findByUserIdAndBeFollowUserId(String userId, String beFollowUserId);
    
    /**
     * 根据用户编号和被关注用户编号查询会员关注
     * 
     * @param memberId 操作者会员编号
     * @param befollowMemberId 被关注者会员编号
     * @return MemberFollowView 会员关注视图信息
     */
    MemberFollowView findByMemberIdAndBeFollowMemberId(String memberId, String beFollowMemberId);

    /**
     * 根据用户编号和被关注用户编号查询会员关注
     * 
     * @param userId 操作者用户编号
     * @param befollowMemberId 被关注者会员编号
     * @return MemberFollowView 会员关注视图信息
     */
    MemberFollowView findByUserIdAndBeFollowMemberId(String userId, String beFollowMemberId);

    /**
     * 根据用户编号和被关注用户编号查询会员关注
     * 
     * @param memberId 操作者会员编号
     * @param beFollowUserId 被关注者会员编号
     * @return MemberFollowView 会员关注视图信息
     */
    MemberFollowView findByMemberIdAndBeFollowUserId(String memberId, String beFollowUserId);
}
