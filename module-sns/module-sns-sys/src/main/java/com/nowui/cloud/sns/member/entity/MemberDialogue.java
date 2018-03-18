package com.nowui.cloud.sns.member.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 会员对话
 *
 * @author marcus
 *
 * 2018-01-08
 */
/**
 * @author xupengfei
 *
 * 2018年3月18日
 */
@Component
@TableName(value = "member_dialogue_info")
public class MemberDialogue extends BaseEntity {

    /**
     * 会员对话编号
     */
    @Id
    @TableId
    @NotNull(message = "会员对话编号不能为空")
    @Length(max = 32, message = "会员对话编号长度超出限制")
    private String memberDialogueId;
    public static final String MEMBER_DIALOGUE_ID = "memberDialogueId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 发起会员编号
     */
    @TableField
    @NotNull(message = "发起会员编号不能为空")
    @Length(max = 32, message = "发起会员编号长度超出限制")
    private String initiateMemberId;
    public static final String INITIATE_MEMBER_ID = "initiateMemberId";

    /**
     * 发起用户编号
     */
    @TableField
    @NotNull(message = "发起用户编号不能为空")
    @Length(max = 32, message = "发起用户编号长度超出限制")
    private String initiateUserId;
    public static final String INITIATE_USER_ID = "initiateUserId";

    /**
     * 响应会员编号
     */
    @TableField
    @NotNull(message = "响应会员编号不能为空")
    @Length(max = 32, message = "响应会员编号长度超出限制")
    private String respondMemberId;
    public static final String RESPOND_MEMBER_ID = "respondMemberId";

    /**
     * 响应用户编号
     */
    @TableField
    @NotNull(message = "响应用户编号不能为空")
    @Length(max = 32, message = "响应用户编号长度超出限制")
    private String respondUserId;
    public static final String RESPOND_USER_ID = "respondUserId";
    
    public static final String MEMBER_DIALOGUE_RECORD_LIST = "memberDialogueRecordList";
    
    public static final String MEMBER_DIALOGUE_RECORD_COUNT = "memberDialogueRecordCount";

	public String getMemberDialogueId() {
		return memberDialogueId;
	}

	public void setMemberDialogueId(String memberDialogueId) {
		this.memberDialogueId = memberDialogueId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getInitiateMemberId() {
		return initiateMemberId;
	}

	public void setInitiateMemberId(String initiateMemberId) {
		this.initiateMemberId = initiateMemberId;
	}

	public String getInitiateUserId() {
		return initiateUserId;
	}

	public void setInitiateUserId(String initiateUserId) {
		this.initiateUserId = initiateUserId;
	}

	public String getRespondMemberId() {
		return respondMemberId;
	}

	public void setRespondMemberId(String respondMemberId) {
		this.respondMemberId = respondMemberId;
	}

	public String getRespondUserId() {
		return respondUserId;
	}

	public void setRespondUserId(String respondUserId) {
		this.respondUserId = respondUserId;
	}



}