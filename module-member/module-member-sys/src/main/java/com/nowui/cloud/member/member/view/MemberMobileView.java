package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员手机号码视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_mobile_info")
public class MemberMobileView extends BaseView {

    /**
     * 会员手机号码编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员手机号码编号不能为空")
    @Length(max = 32, message = "会员手机号码编号长度超出限制")
    private String memberMobileId;
    public static final String MEMBER_MOBILE_ID = "memberMobileId";

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
     * 会员手机号码
     */
    @Field
    @NotNull(message = "会员手机号码不能为空")
    @Length(max = 11, message = "会员手机号码长度超出限制")
    private String memberMobile;
    public static final String MEMBER_MOBILE = "memberMobile";


    public String getMemberMobileId() {
        return memberMobileId;
    }

    public void setMemberMobileId(String memberMobileId) {
        this.memberMobileId = memberMobileId;
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
    
    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
    

}