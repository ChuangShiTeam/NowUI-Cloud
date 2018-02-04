package com.nowui.cloud.sns.forum.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 论坛用户关注视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "forum_user_follow_info")
public class ForumUserFollowView extends BaseView {

    /**
     * 论坛用户关注编号
     */
    @KeyId
    @Field
    @NotNull(message = "论坛用户关注编号不能为空")
    private String forumUserFollowId;
    public static final String FORUM_USER_FOLLOW_ID = "forumUserFollowId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 论坛编号
     */
    @Field
    @NotNull(message = "论坛编号不能为空")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 是否置顶
     */
    @Field
    @NotNull(message = "是否置顶不能为空")
    private Boolean forumUserFollowIsTop;
    public static final String FORUM_USER_FOLLOW_IS_TOP = "forumUserFollowIsTop";


    public String getForumUserFollowId() {
        return getString(FORUM_USER_FOLLOW_ID);
    }

    public void setForumUserFollowId(String forumUserFollowId) {
        put(FORUM_USER_FOLLOW_ID, forumUserFollowId);
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

    public String getForumId() {
        return getString(FORUM_ID);
    }

    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public Boolean getForumUserFollowIsTop() {
        return getBoolean(FORUM_USER_FOLLOW_IS_TOP);
    }

    public void setForumUserFollowIsTop(Boolean forumUserFollowIsTop) {
        put(FORUM_USER_FOLLOW_IS_TOP, forumUserFollowIsTop);
    }


}