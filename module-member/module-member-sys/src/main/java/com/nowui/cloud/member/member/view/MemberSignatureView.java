package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员签名视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_signature_info")
public class MemberSignatureView extends BaseView {

    /**
     * 会员签名编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员签名编号不能为空")
    @Length(max = 32, message = "会员签名编号长度超出限制")
    private String memberSignatureId;
    public static final String MEMBER_SIGNATURE_ID = "memberSignatureId";

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
     * 会员签名
     */
    @Field
    @NotNull(message = "会员签名不能为空")
    @Length(max = 500, message = "会员签名长度超出限制")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";


    public String getMemberSignatureId() {
        return memberSignatureId;
    }

    public void setMemberSignatureId(String memberSignatureId) {
        this.memberSignatureId = memberSignatureId;
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
    
    public String getMemberSignature() {
        return memberSignature;
    }

    public void setMemberSignature(String memberSignature) {
        this.memberSignature = memberSignature;
    }
    

}