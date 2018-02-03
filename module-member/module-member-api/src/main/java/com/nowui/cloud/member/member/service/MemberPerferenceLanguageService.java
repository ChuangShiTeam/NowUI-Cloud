package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.entity.MemberPerferenceLanguage;
import com.nowui.cloud.member.member.view.MemberPerferenceLanguageView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;

/**
 * 会员偏好语言业务接口
 *
 * @author marcus
 *
 * 2018-01-29
 */
public interface MemberPerferenceLanguageService extends SuperService<MemberPerferenceLanguage, MemberPerferenceLanguageView> {

    /**
     * 根据会员编号查询会员偏好语言信息
     * 
     * @param memberId 会员编号
     * @return MemberPerferenceLanguage 会员偏好语言信息
     */
    MemberPerferenceLanguage findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员偏好语言信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
    
}
