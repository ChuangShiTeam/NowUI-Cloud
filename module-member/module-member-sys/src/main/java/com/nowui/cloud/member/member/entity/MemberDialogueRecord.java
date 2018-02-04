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
 * 会员对话记录
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@TableName(value = "member_dialogue_record_info")
public class MemberDialogueRecord extends BaseEntity {

    /**
     * 会员对话记录编号
     */
    @Id
    @TableId
    @NotNull(message = "会员对话记录编号不能为空")
    @Length(max = 32, message = "会员对话记录编号长度超出限制")
    private String memberDialogueRecordId;
    public static final String MEMBER_DIALOGUE_RECORD_ID = "memberDialogueRecordId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员对话编号
     */
    @TableField
    @NotNull(message = "会员对话编号不能为空")
    @Length(max = 32, message = "会员对话编号长度超出限制")
    private String memberDialogueId;
    public static final String MEMBER_DIALOGUE_ID = "memberDialogueId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 对话内容
     */
    @TableField
    @NotNull(message = "对话内容不能为空")
    @Length(max = 2000, message = "对话内容长度超出限制")
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