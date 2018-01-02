package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.Member;

import java.util.List;

/**
 * 会员业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface MemberService extends BaseService<Member> {

    /**
     * 会员统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @return Integer 会员统计
     */
    Integer adminCount(String appId, String userId);

    /**
     * 会员列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Member> 会员列表
     */
    List<Member> adminList(String appId, String userId, Integer m, Integer n);
}
