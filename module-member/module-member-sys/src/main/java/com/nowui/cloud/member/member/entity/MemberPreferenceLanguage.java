package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员偏好语言
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_preference_language_info")
public class MemberPreferenceLanguage extends BaseEntity {

    /**
     * 会员偏好语言编号
     */
    @TableId
    @TableField
    private String memberPreferenceLanguageId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 会员编号
     */
    @TableField
    private String memberId;

    /**
     * 会员偏好语言
     */
    @TableField
    private String memberPreferenceLanguage;


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