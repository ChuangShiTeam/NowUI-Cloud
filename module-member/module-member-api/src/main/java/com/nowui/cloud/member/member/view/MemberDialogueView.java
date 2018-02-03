package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员对话视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_dialogue_info")
public class MemberDialogueView extends BaseView {

    /**
     * 会员对话编号
     */
    @Field
    private String memberDialogueId;
    public static final String MEMBER_DIALOGUE_ID = "memberDialogueId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 发起会员编号
     */
    @Field
    private String initiateMemberId;
    public static final String INITIATE_MEMBER_ID = "initiateMemberId";

    /**
     * 发起用户编号
     */
    @Field
    private String initiateUserId;
    public static final String INITIATE_USER_ID = "initiateUserId";

    /**
     * 响应会员编号
     */
    @Field
    private String respondMemberId;
    public static final String RESPOND_MEMBER_ID = "respondMemberId";

    /**
     * 响应用户编号
     */
    @Field
    private String respondUserId;
    public static final String RESPOND_USER_ID = "respondUserId";


    public String getMemberDialogueId() {
        return getString(MEMBER_DIALOGUE_ID);
    }

    public void setMemberDialogueId(String memberDialogueId) {
        put(MEMBER_DIALOGUE_ID, memberDialogueId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getInitiateMemberId() {
        return getString(INITIATE_MEMBER_ID);
    }

    public void setInitiateMemberId(String initiateMemberId) {
        put(INITIATE_MEMBER_ID, initiateMemberId);
    }

    public String getInitiateUserId() {
        return getString(INITIATE_USER_ID);
    }

    public void setInitiateUserId(String initiateUserId) {
        put(INITIATE_USER_ID, initiateUserId);
    }

    public String getRespondMemberId() {
        return getString(RESPOND_MEMBER_ID);
    }

    public void setRespondMemberId(String respondMemberId) {
        put(RESPOND_MEMBER_ID, respondMemberId);
    }

    public String getRespondUserId() {
        return getString(RESPOND_USER_ID);
    }

    public void setRespondUserId(String respondUserId) {
        put(RESPOND_USER_ID, respondUserId);
    }


}