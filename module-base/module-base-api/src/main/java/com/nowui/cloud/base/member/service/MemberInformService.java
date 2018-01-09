package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.MemberInform;

import java.util.List;

/**
 * 会员举报业务接口
 *
 * @author marcus
 *
 * 2018-01-09
 */
public interface MemberInformService extends BaseService<MemberInform> {

    /**
     * 会员举报统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param informUserId 被举报用户编号
     * @param informMemberId 被举报会员编号
     * @return Integer 会员举报统计
     */
    Integer countForAdmin(String appId, String memberId, String userId, String informUserId, String informMemberId);

    /**
     * 会员举报列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param informUserId 被举报用户编号
     * @param informMemberId 被举报会员编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberInform> 会员举报列表
     */
    List<MemberInform> listForAdmin(String appId, String memberId, String userId, String informUserId, String informMemberId, Integer pageIndex, Integer pageSize);
}
