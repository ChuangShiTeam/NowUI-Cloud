package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员偏好语言
 *
 * @author marcus
 *
 * 2018-01-29
 */
@Component
@TableName(value = "member_preference_language_info")
public class MemberPreferenceLanguage extends BaseEntity {

    /**
     * 会员偏好语言编号
     */
    @Id
    @TableId
    @NotNull(message = "会员偏好语言编号不能为空")
    @Length(max = 32, message = "会员偏好语言编号长度超出限制")
    private String memberPreferenceLanguageId;
    public static final String MEMBER_PREFERENCE_LANGUAGE_ID = "memberPreferenceLanguageId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员偏好语言
     */
    @TableField
    @NotNull(message = "会员偏好语言不能为空")
    @Length(max = 100, message = "会员偏好语言长度超出限制")
    private String memberPreferenceLanguage;
    public static final String MEMBER_PREFERENCE_LANGUAGE = "memberPreferenceLanguage";


    public String getMemberPreferenceLanguageId() {
        return getString(MEMBER_PREFERENCE_LANGUAGE_ID);
    }
    
    public void setMemberPreferenceLanguageId(String memberPreferenceLanguageId) {
        put(MEMBER_PREFERENCE_LANGUAGE_ID, memberPreferenceLanguageId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getMemberPreferenceLanguage() {
        return getString(MEMBER_PREFERENCE_LANGUAGE);
    }
    
    public void setMemberPreferenceLanguage(String memberPreferenceLanguage) {
        put(MEMBER_PREFERENCE_LANGUAGE, memberPreferenceLanguage);
    }


}