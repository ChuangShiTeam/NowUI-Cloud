package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员邮箱视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_email_info")
public class MemberEmailView extends BaseView {

    /**
     * 会员邮箱编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员邮箱编号不能为空")
    @Length(max = 32, message = "会员邮箱编号长度超出限制")
    private String memberEmailId;
    public static final String MEMBER_EMAIL_ID = "memberEmailId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员邮箱
     */
    @Field
    @NotNull(message = "会员邮箱不能为空")
    @Length(max = 50, message = "会员邮箱长度超出限制")
    private String memberEmail;
    public static final String MEMBER_EMAIL = "memberEmail";


    public String getMemberEmailId() {
        return memberEmailId;
    }

    public void setMemberEmailId(String memberEmailId) {
        this.memberEmailId = memberEmailId;
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
    
    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
    

}