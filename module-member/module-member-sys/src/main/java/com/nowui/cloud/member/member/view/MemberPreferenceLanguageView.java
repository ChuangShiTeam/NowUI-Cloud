package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员偏好语言视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_preference_language_info")
public class MemberPreferenceLanguageView extends BaseView {

    /**
     * 会员偏好语言编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员偏好语言编号不能为空")
    @Length(max = 32, message = "会员偏好语言编号长度超出限制")
    private String memberPreferenceLanguageId;
    public static final String MEMBER_PREFERENCE_LANGUAGE_ID = "memberPreferenceLanguageId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员偏好语言
     */
    @Field
    @NotNull(message = "会员偏好语言不能为空")
    @Length(max = 100, message = "会员偏好语言长度超出限制")
    private String memberPreferenceLanguage;
    public static final String MEMBER_PREFERENCE_LANGUAGE = "memberPreferenceLanguage";


    public String getMemberPreferenceLanguageId() {
        return memberPreferenceLanguageId;
    }

    public void setMemberPreferenceLanguageId(String memberPreferenceLanguageId) {
        this.memberPreferenceLanguageId = memberPreferenceLanguageId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberPreferenceLanguage() {
        return memberPreferenceLanguage;
    }

    public void setMemberPreferenceLanguage(String memberPreferenceLanguage) {
        this.memberPreferenceLanguage = memberPreferenceLanguage;
    }
    

}