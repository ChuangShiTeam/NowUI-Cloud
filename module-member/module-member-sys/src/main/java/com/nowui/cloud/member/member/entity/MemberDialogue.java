package com.nowui.cloud.member.member.entity;

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