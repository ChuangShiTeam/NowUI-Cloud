package com.nowui.cloud.sns.member.view;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员举报视图
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
    @KeyId
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
		return memberInformId;
	}
	public void setMemberInformId(String memberInformId) {
		this.memberInformId = memberInformId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInformUserId() {
		return informUserId;
	}
	public void setInformUserId(String informUserId) {
		this.informUserId = informUserId;
	}
	public String getInformMemberId() {
		return informMemberId;
	}
	public void setInformMemberId(String informMemberId) {
		this.informMemberId = informMemberId;
	}

}