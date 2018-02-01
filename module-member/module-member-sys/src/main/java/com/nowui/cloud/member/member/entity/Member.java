package com.nowui.cloud.member.member.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 会员
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "member_info")
@TableName(value = "member_info")
public class Member extends BaseEntity {

    /**
     * 会员编号
     */
    @Id
    @TableId
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

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
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 是否置顶
     */
    @Field
    @TableField
    @NotNull(message = "是否置顶不能为空")
    private Boolean memberIsTop;
    public static final String MEMBER_IS_TOP = "memberIsTop";

    /**
     * 会员置顶级别
     */
    @Field
    @TableField
    @NotNull(message = "会员置顶级别不能为空")
    private Integer memberTopLevel;
    public static final String MEMBER_TOP_LEVEL = "memberTopLevel";

    /**
     * 置顶结束时间
     */
    @Field
    @TableField
    @NotNull(message = "置顶结束时间不能为空")
    private Date memberTopEndTime;
    public static final String MEMBER_TOP_END_TIME = "memberTopEndTime";

    /**
     * 会员是否推荐
     */
    @Field
    @TableField
    @NotNull(message = "会员是否推荐不能为空")
    private Boolean memberIsRecommed;
    public static final String MEMBER_IS_RECOMMED = "memberIsRecommed";

    public static final String MEMBER_USER = "memberUser";
    
    public static final String MEMBER_BACKGROUND = "memberBackground";
    
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";
    
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";
    
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";
    
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";
    
    public static final String MEMBER_PREFERENCE_LANGUAGE = "memberPreferenceLanguage";
    
    /**
     * 用户发布的动态数
     */
    public static final String MEMBER_SEND_TOPIC_COUNT = "memberSendTopicCount";
    
    /**
     * 用户粉丝数
     */
    public static final String MEMBER_BE_FOLLOW_COUNT = "memberBeFollowCount";
    
    /**
     * 用户关注数
     */
    public static final String MEMBER_FOLLOW_COUNT = "memberFollowCount";
    
    /**
     * 查询到的会员是否是自己
     */
    public static final String MEMBER_IS_SELF = "memberIsSelf";
    
    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public Boolean getMemberIsTop() {
        return getBoolean(MEMBER_IS_TOP);
    }
    
    public void setMemberIsTop(Boolean memberIsTop) {
        put(MEMBER_IS_TOP, memberIsTop);
    }

    public Integer getMemberTopLevel() {
        return getInteger(MEMBER_TOP_LEVEL);
    }
    
    public void setMemberTopLevel(Integer memberTopLevel) {
        put(MEMBER_TOP_LEVEL, memberTopLevel);
    }

    public Date getMemberTopEndTime() {
        return getDate(MEMBER_TOP_END_TIME);
    }
    
    public void setMemberTopEndTime(Date memberTopEndTime) {
        put(MEMBER_TOP_END_TIME, memberTopEndTime);
    }

    public Boolean getMemberIsRecommed() {
        return getBoolean(MEMBER_IS_RECOMMED);
    }
    
    public void setMemberIsRecommed(Boolean memberIsRecommed) {
        put(MEMBER_IS_RECOMMED, memberIsRecommed);
    }

}