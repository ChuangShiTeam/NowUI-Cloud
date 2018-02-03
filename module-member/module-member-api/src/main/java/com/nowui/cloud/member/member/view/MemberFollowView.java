package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 关注会员信息视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_follow_info")
public class MemberFollowView extends BaseView {

    /**
     * 会员关注编号
     */
    @Field
    private String memberFollowId;
    public static final String MEMBER_FOLLOW_ID = "memberFollowId";

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
     * 关注会员编号
     */
    @Field
    private String followMemberId;
    public static final String FOLLOW_MEMBER_ID = "followMemberId";

    /**
     * 被关注用户编号
     */
    @Field
    private String followUserId;
    public static final String FOLLOW_USER_ID = "followUserId";


    public String getMemberFollowId() {
        return getString(MEMBER_FOLLOW_ID);
    }

    public void setMemberFollowId(String memberFollowId) {
        put(MEMBER_FOLLOW_ID, memberFollowId);
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

    public String getFollowMemberId() {
        return getString(FOLLOW_MEMBER_ID);
    }

    public void setFollowMemberId(String followMemberId) {
        put(FOLLOW_MEMBER_ID, followMemberId);
    }

    public String getFollowUserId() {
        return getString(FOLLOW_USER_ID);
    }

    public void setFollowUserId(String followUserId) {
        put(FOLLOW_USER_ID, followUserId);
    }


}