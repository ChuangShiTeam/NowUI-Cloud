package com.nowui.cloud.sns.member.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员对话记录视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_dialogue_record_info")
public class MemberDialogueRecordView extends BaseView {

    /**
     * 会员对话记录编号
     */
    @KeyId
    @Field
    private String memberDialogueRecordId;
    public static final String MEMBER_DIALOGUE_RECORD_ID = "memberDialogueRecordId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员对话编号
     */
    @Field
    private String memberDialogueId;
    public static final String MEMBER_DIALOGUE_ID = "memberDialogueId";

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
     * 对话内容
     */
    @Field
    private String memberDialogueContent;
    public static final String MEMBER_DIALOGUE_CONTENT = "memberDialogueContent";


    public String getMemberDialogueRecordId() {
        return getString(MEMBER_DIALOGUE_RECORD_ID);
    }

    public void setMemberDialogueRecordId(String memberDialogueRecordId) {
        put(MEMBER_DIALOGUE_RECORD_ID, memberDialogueRecordId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberDialogueId() {
        return getString(MEMBER_DIALOGUE_ID);
    }

    public void setMemberDialogueId(String memberDialogueId) {
        put(MEMBER_DIALOGUE_ID, memberDialogueId);
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

    public String getMemberDialogueContent() {
        return getString(MEMBER_DIALOGUE_CONTENT);
    }

    public void setMemberDialogueContent(String memberDialogueContent) {
        put(MEMBER_DIALOGUE_CONTENT, memberDialogueContent);
    }


}