package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员等级	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_level_info")
public class MemberLevelView extends BaseView {

    /**
     * 
     */
    @Field
    private String memberLevelId;
    public static final String MEMBER_LEVEL_ID = "memberLevelId";

    /**
     * 
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";


    public String getMemberLevelId() {
        return getString(MEMBER_LEVEL_ID);
    }

    public void setMemberLevelId(String memberLevelId) {
        put(MEMBER_LEVEL_ID, memberLevelId);
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


}