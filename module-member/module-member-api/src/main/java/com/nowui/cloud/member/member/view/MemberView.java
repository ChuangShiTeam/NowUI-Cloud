package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 会员	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_info")
public class MemberView extends BaseView {

    /**
     * 会员编号
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 是否置顶
     */
    @Field
    private Boolean memberIsTop;
    public static final String MEMBER_IS_TOP = "memberIsTop";

    /**
     * 会员置顶级别
     */
    @Field
    private Integer memberTopLevel;
    public static final String MEMBER_TOP_LEVEL = "memberTopLevel";

    /**
     * 置顶结束时间
     */
    @Field
    private Date memberTopEndTime;
    public static final String MEMBER_TOP_END_TIME = "memberTopEndTime";

    /**
     * 会员是否推荐
     */
    @Field
    private Boolean memberIsRecommed;
    public static final String MEMBER_IS_RECOMMED = "memberIsRecommed";


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