package com.nowui.cloud.member.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.member.member.entity.MemberBackgroud;

import java.util.List;

/**
 * 会员背景业务接口
 *
 * @author marcus
 *
 * 2018-01-14
 */
public interface MemberBackgroudService extends BaseService<MemberBackgroud> {

    /**
     * 会员背景统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberBackgroundFileId 会员背景图文件编号
     * @return Integer 会员背景统计
     */
    Integer countForAdmin(String appId, String memberId, String memberBackgroundFileId);

    /**
     * 会员背景列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberBackgroundFileId 会员背景图文件编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberBackgroud> 会员背景列表
     */
    List<MemberBackgroud> listForAdmin(String appId, String memberId, String memberBackgroundFileId, Integer pageIndex, Integer pageSize);
}
