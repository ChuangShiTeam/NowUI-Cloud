package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员性别
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_sex_info")
public class MemberSex extends BaseEntity {

    /**
     * 会员性别编号
     */
    @TableId
    @TableField
    private String memberSexId;

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
     * 会员性别
     */
    @TableField
    private String memberSex;


    public String getMemberSexId() {
        return memberSexId;
    }
    
    public void setMemberSexId(String memberSexId) {
        this.memberSexId = memberSexId;
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

    public String getMemberSex() {
        return memberSex;
    }
    
    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }


}