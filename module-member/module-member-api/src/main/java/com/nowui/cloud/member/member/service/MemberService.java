package com.nowui.cloud.member.member.service;
import java.util.List;

import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.service.BaseService;

/**
 * 会员业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface MemberService extends BaseService<Member> {

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

}
