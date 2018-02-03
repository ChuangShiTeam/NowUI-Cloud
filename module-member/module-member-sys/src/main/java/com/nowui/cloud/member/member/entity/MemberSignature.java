package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 	会员签名
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Component
@TableName(value = "member_signature_info")
public class MemberSignature extends BaseEntity {

    /**
     * 会员签名编号
     */
    @Id
    @TableId
    @NotNull(message = "会员签名编号不能为空")
    @Length(max = 32, message = "会员签名编号长度超出限制")
    private String memberSignatureId;
    public static final String MEMBER_SIGNATURE_ID = "memberSignatureId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员签名
     */
    @Field
    @TableField
    @NotNull(message = "会员签名不能为空")
    @Length(max = 500, message = "会员签名长度超出限制")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";


    public String getMemberSignatureId() {
        return getString(MEMBER_SIGNATURE_ID);
    }
    
    public void setMemberSignatureId(String memberSignatureId) {
        put(MEMBER_SIGNATURE_ID, memberSignatureId);
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

    public String getMemberSignature() {
        return getString(MEMBER_SIGNATURE);
    }
    
    public void setMemberSignature(String memberSignature) {
        put(MEMBER_SIGNATURE, memberSignature);
    }


}