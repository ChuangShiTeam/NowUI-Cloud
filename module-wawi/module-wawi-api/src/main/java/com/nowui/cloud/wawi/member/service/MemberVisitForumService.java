package com.nowui.cloud.wawi.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.wawi.member.entity.MemberVisitForum;

import java.util.List;

/**
 * 会员访问圈子业务接口
 *
 * @author marcus
 *
 * 2018-01-17
 */
public interface MemberVisitForumService extends BaseService<MemberVisitForum> {

    /**
     * 会员访问圈子统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @return Integer 会员访问圈子统计
     */
    Integer countForAdmin(String appId, String memberId, String userId);

    /**
     * 会员访问圈子列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberVisitForum> 会员访问圈子列表
     */
    List<MemberVisitForum> listForAdmin(String appId, String memberId, String userId, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户编号查询会员访问圈子记录
     * 
     * @param userId 用户编号
     * @return MemberVisitForum 会员访问圈子信息
     */
    MemberVisitForum findByUserId(String userId);
}
