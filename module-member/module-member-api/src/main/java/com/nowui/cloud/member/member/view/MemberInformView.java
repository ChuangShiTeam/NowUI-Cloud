package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员举报	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_inform_info")
public class MemberInformView extends BaseView {

    /**
     * 会员举报编号
     */
    @Field
    private String memberInformId;
    public static final String MEMBER_INFORM_ID = "memberInformId";

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
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 被举报用户编号
     */
    @Field
    private String informUserId;
    public static final String INFORM_USER_ID = "informUserId";

    /**
     * 被举报会员编号
     */
    @Field
    private String informMemberId;
    public static final String INFORM_MEMBER_ID = "informMemberId";


    public String getMemberInformId() {
        return getString(MEMBER_INFORM_ID);
    }

    public void setMemberInformId(String memberInformId) {
        put(MEMBER_INFORM_ID, memberInformId);
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

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getInformUserId() {
        return getString(INFORM_USER_ID);
    }

    public void setInformUserId(String informUserId) {
        put(INFORM_USER_ID, informUserId);
    }

    public String getInformMemberId() {
        return getString(INFORM_MEMBER_ID);
    }

    public void setInformMemberId(String informMemberId) {
        put(INFORM_MEMBER_ID, informMemberId);
    }


}