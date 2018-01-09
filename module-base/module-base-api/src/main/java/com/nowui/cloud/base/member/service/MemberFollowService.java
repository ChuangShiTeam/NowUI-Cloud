package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.MemberFollow;

import java.util.List;

/**
 * 会员关注业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface MemberFollowService extends BaseService<MemberFollow> {

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
}
