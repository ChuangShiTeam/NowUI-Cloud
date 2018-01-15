package com.nowui.cloud.member.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.member.member.entity.MemberSignature;

import java.util.List;

/**
 * 	会员签名业务接口
 *
 * @author marcus
 *
 * 2018-01-14
 */
public interface MemberSignatureService extends BaseService<MemberSignature> {

    /**
     * 	会员签名统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberSignature 会员签名
     * @return Integer 	会员签名统计
     */
    Integer countForAdmin(String appId, String memberId, String memberSignature);

    /**
     * 	会员签名列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberSignature 会员签名
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberSignature> 	会员签名列表
     */
    List<MemberSignature> listForAdmin(String appId, String memberId, String memberSignature, Integer pageIndex, Integer pageSize);

    /**
     * 根据会员编号查询会员签名信息
     * 
     * @param memberId 会员编号
     * @return MemberSignature会员签名信息
     */
    MemberSignature findByMemberId(String memberId);
}
