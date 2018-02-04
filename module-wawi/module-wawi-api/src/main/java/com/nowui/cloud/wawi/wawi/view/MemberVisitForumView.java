package com.nowui.cloud.wawi.wawi.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员访问圈子记录视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "member_visit_forum_info")
public class MemberVisitForumView extends BaseView {

    /**
     * 会员访问圈子编号
     */
    @Field
    @NotNull(message = "会员访问圈子编号不能为空")
    private String memberVisitForumId;
    public static final String MEMBER_VISIT_FORUM_ID = "memberVisitForumId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";


    public String getMemberVisitForumId() {
        return getString(MEMBER_VISIT_FORUM_ID);
    }

    public void setMemberVisitForumId(String memberVisitForumId) {
        put(MEMBER_VISIT_FORUM_ID, memberVisitForumId);
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

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}