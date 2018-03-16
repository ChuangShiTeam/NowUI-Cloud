package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员账号视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_account_info")
public class MemberAccountView extends BaseView {

    /**
     * 会员账号编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员账号编号不能为空")
    @Length(max = 32, message = "会员账号编号长度超出限制")
    private String memberAccountId;
    public static final String MEMBER_ACCOUNT_ID = "memberAccountId";

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
     * 会员账号
     */
    @Field
    @NotNull(message = "会员账号不能为空")
    @Length(max = 30, message = "会员账号长度超出限制")
    private String memberAccount;
    public static final String MEMBER_ACCOUNT = "memberAccount";


    public String getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(String memberAccountId) {
        this.memberAccountId = memberAccountId;
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
    
    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }
    

}