package com.nowui.cloud.sns.forum.view;

import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 论坛取消关注视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "forum_user_unfollow_info")
public class ForumUserUnfollowView extends BaseView {

    /**
     * 论坛用户取消关注编号
     */
    @KeyId
    @Field
    @NotNull(message = "论坛用户取消关注编号不能为空")
    private String forumUserUnfollowId;
    public static final String FORUM_USER_UNFOLLOW_ID = "forumUserUnfollowId";

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
    @NotNull(message = "会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 论坛编号
     */
    @Field
    @NotNull(message = "论坛编号不能为空")
    private String forumId;
    public static final String FORUM_ID = "forumId";
    
    /**
     * 用户论坛是否置顶
     */
    @Field
    @NotNull(message = "论坛是否置顶不能为空")
    private Boolean forumUserFollowIsTop;
    public static final String FORUM_USER_FOLLOW_IS_TOP = "forumUserFollowIsTop";


    public String getForumUserUnfollowId() {
        return getString(FORUM_USER_UNFOLLOW_ID);
    }

    public void setForumUserUnfollowId(String forumUserUnfollowId) {
        put(FORUM_USER_UNFOLLOW_ID, forumUserUnfollowId);
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