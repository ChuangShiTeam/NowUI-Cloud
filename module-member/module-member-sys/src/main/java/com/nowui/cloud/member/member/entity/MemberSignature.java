package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员签名
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_signature_info")
public class MemberSignature extends BaseEntity {

    /**
     * 会员签名编号
     */
    @TableId
    @TableField
    private String memberSignatureId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 会员编号
     */
    @TableField
    private String memberId;

    /**
     * 会员签名
     */
    @TableField
    private String memberSignature;


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