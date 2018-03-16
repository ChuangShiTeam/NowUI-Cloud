package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员密码视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_password_info")
public class MemberPasswordView extends BaseView {

    /**
     * 会员密码编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员密码编号不能为空")
    @Length(max = 32, message = "会员密码编号长度超出限制")
    private String memberPasswordId;
    public static final String MEMBER_PASSWORD_ID = "memberPasswordId";

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
     * 会员密码
     */
    @Field
    @NotNull(message = "会员密码不能为空")
    @Length(max = 128, message = "会员密码长度超出限制")
    private String memberPassword;
    public static final String MEMBER_PASSWORD = "memberPassword";


    public String getMemberPasswordId() {
        return memberPasswordId;
    }

    public void setMemberPasswordId(String memberPasswordId) {
        this.memberPasswordId = memberPasswordId;
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
    
    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    

}