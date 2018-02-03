package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员偏好语言视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_perference_language_info")
public class MemberPerferenceLanguageView extends BaseView {

    /**
     * 会员偏好语言编号
     */
    @Field
    private String memberPreferenceLanguageId;
    public static final String MEMBER_PREFERENCE_LANGUAGE_ID = "memberPreferenceLanguageId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员偏好语言
     */
    @Field
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