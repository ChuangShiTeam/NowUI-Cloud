package com.nowui.cloud.member.member.service;
import com.nowui.cloud.member.member.entity.MemberPreferenceLanguage;
import com.nowui.cloud.member.member.view.MemberPreferenceLanguageView;
import com.nowui.cloud.service.BaseService;

/**
 * 会员偏好语言业务接口
 *
 * @author marcus
 *
 * 2018-01-29
 */
public interface MemberPreferenceLanguageService extends BaseService<MemberPreferenceLanguage, MemberPreferenceLanguageView> {

    /**
     * 根据会员编号查询会员偏好语言信息
     * 
     * @param memberId 会员编号
     * @return MemberPreferenceLanguage 会员偏好语言信息
     */
    MemberPreferenceLanguage findByMemberId(String memberId);
    
    /**
     * 根据会员编号删除会员偏好语言信息
     * 
     * @param memberId 会员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByMemberId(String memberId, String systemRequestUserId);
    
}
