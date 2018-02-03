package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员背景	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_background_info")
public class MemberBackgroundView extends BaseView {

    /**
     * 会员背景编号
     */
    @Field
    private String memberBackgroundId;
    public static final String MEMBER_BACKGROUND_ID = "memberBackgroundId";

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
     * 会员背景
     */
    @Field
    private String memberBackground;
    public static final String MEMBER_BACKGROUND = "memberBackground";


    public String getMemberBackgroundId() {
        return getString(MEMBER_BACKGROUND_ID);
    }

    public void setMemberBackgroundId(String memberBackgroundId) {
        put(MEMBER_BACKGROUND_ID, memberBackgroundId);
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

    public String getMemberBackground() {
        return getString(MEMBER_BACKGROUND);
    }

    public void setMemberBackground(String memberBackground) {
        put(MEMBER_BACKGROUND, memberBackground);
    }


}