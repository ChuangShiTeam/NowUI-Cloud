package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员举报
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Component
@TableName(value = "member_inform_info")
public class MemberInform extends BaseEntity {

    /**
     * 会员举报编号
     */
    @Id
    @TableId
    @NotNull(message = "会员举报编号不能为空")
    @Length(max = 32, message = "会员举报编号长度超出限制")
    private String memberInformId;
    public static final String MEMBER_INFORM_ID = "memberInformId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

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
     * 被举报用户编号
     */
    @TableField
    @NotNull(message = "被举报用户编号不能为空")
    @Length(max = 32, message = "被举报用户编号长度超出限制")
    private String informUserId;
    public static final String INFORM_USER_ID = "informUserId";

    /**
     * 被举报会员编号
     */
    @TableField
    @NotNull(message = "被举报会员编号不能为空")
    @Length(max = 32, message = "被举报会员编号长度超出限制")
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