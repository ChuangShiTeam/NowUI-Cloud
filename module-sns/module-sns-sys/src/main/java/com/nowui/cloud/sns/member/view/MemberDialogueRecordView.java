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
		return memberDialogueRecordId;
	}
	public void setMemberDialogueRecordId(String memberDialogueRecordId) {
		this.memberDialogueRecordId = memberDialogueRecordId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMemberDialogueId() {
		return memberDialogueId;
	}
	public void setMemberDialogueId(String memberDialogueId) {
		this.memberDialogueId = memberDialogueId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMemberDialogueContent() {
		return memberDialogueContent;
	}
	public void setMemberDialogueContent(String memberDialogueContent) {
		this.memberDialogueContent = memberDialogueContent;
	}


}