package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛用户取关关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@TableName(value = "forum_user_unfollow_map")
public class ForumUserUnfollow extends BaseEntity {

    /**
     * 论坛用户取关关联id
     */
    @TableId
    @NotNull(message = "论坛用户取关关联id不能为空")
    @Length(max = 32, message = "论坛用户取关关联id长度超出限制")
    private String forumUserUnfollowMapId;
    public static final String FORUM_USER_UNFOLLOW_MAP_ID = "forumUserUnfollowMapId";

    /**
     * 应用Id
     */
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户ID
     */
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 11, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 论坛Id
     */
    @TableField
    @NotNull(message = "论坛Id不能为空")
    @Length(max = 32, message = "论坛Id长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";


    public String getForumUserUnfollowMapId() {
        return getString(FORUM_USER_UNFOLLOW_MAP_ID);
    }
    
    public void setForumUserUnfollowMapId(String forumUserUnfollowMapId) {
        put(FORUM_USER_UNFOLLOW_MAP_ID, forumUserUnfollowMapId);
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


}