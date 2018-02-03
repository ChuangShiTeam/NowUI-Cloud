package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.view.MemberSignatureView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 	会员签名业务接口
 *
 * @author marcus
 *
 * 2018-01-14
 */
public interface MemberSignatureService extends SuperService<MemberSignature,MemberSignatureView> {

    /**
     * 根据会员编号查询会员签名信息
     * 
     * @param memberId 会员编号
     * @return MemberSignature会员签名信息
     */
    MemberSignature findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员签名信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
}
