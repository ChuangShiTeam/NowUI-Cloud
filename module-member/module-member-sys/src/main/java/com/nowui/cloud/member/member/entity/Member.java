package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 会员
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_info")
public class Member extends BaseEntity {

    /**
     * 会员编号
     */
    @TableId
    @TableField
    private String memberId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 用户编号
     */
    @TableField
    private String userId;

    /**
     * 是否置顶
     */
    @TableField
    private Boolean memberIsTop;

    /**
     * 会员置顶级别
     */
    @TableField
    private Integer memberTopLevel;

    /**
     * 置顶结束时间
     */
    @TableField
    private Date memberTopEndTime;

    /**
     * 会员是否推荐
     */
    @TableField
    private Boolean memberIsRecommed;


    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getMemberIsTop() {
        return memberIsTop;
    }
    
    public void setMemberIsTop(Boolean memberIsTop) {
        this.memberIsTop = memberIsTop;
    }

    public Integer getMemberTopLevel() {
        return memberTopLevel;
    }
    
    public void setMemberTopLevel(Integer memberTopLevel) {
        this.memberTopLevel = memberTopLevel;
    }

    public Date getMemberTopEndTime() {
        return memberTopEndTime;
    }
    
    public void setMemberTopEndTime(Date memberTopEndTime) {
        this.memberTopEndTime = memberTopEndTime;
    }

    public Boolean getMemberIsRecommed() {
        return memberIsRecommed;
    }
    
    public void setMemberIsRecommed(Boolean memberIsRecommed) {
        this.memberIsRecommed = memberIsRecommed;
    }


}