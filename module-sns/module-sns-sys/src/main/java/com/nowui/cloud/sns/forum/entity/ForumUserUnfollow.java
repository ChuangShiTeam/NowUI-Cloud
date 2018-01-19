package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
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
@Document(indexName = "nowui", type = "forum_user_unfollow_map")
@TableName(value = "forum_user_unfollow_map")
public class ForumUserUnfollow extends BaseEntity {

    /**
     * 论坛用户取关关联id
     */
	@Id
    @TableId
    @NotNull(message = "论坛用户取关关联id不能为空")
    @Length(max = 32, message = "论坛用户取关关联id长度超出限制")
    private String forumUserUnfollowId;
    public static final String FORUM_USER_UNFOLLOW_ID = "forumUserUnfollowId";

    /**
     * 应用Id
     */
    @Field
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户ID
     */
    @Field
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 32, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 论坛Id
     */
    @Field
    @TableField
    @NotNull(message = "论坛Id不能为空")
    @Length(max = 32, message = "论坛Id长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";


    public String getforumUserUnfollowId() {
        return getString(FORUM_USER_UNFOLLOW_ID);
    }
    
    public void setforumUserUnfollowId(String forumUserUnfollowId) {
        put(FORUM_USER_UNFOLLOW_ID, forumUserUnfollowId);
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