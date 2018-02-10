package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题评论的取消点赞
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Component

@TableName(value = "topic_comment_user_unlike_info")
public class TopicCommentUserUnlike extends BaseEntity {

    /**
     * 话题评论的取消点赞编号
     */
    @Id
    @TableId
    @NotNull(message = "话题评论的取消点赞编号不能为空")
    @Length(max = 32, message = "话题评论的取消点赞编号长度超出限制")
    private String commentUserUnlikeId;
    public static final String COMMENT_USER_UNLIKE_ID = "commentUserUnlikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 被取消点赞的话题评论编号
     */
    @TableField
    @NotNull(message = "被取消点赞的话题评论编号不能为空")
    @Length(max = 32, message = "被取消点赞的话题评论编号长度超出限制")
    private String commentId;
    public static final String COMMENT_ID = "commentId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";


    public String getCommentUserUnlikeId() {
        return getString(COMMENT_USER_UNLIKE_ID);
    }
    
    public void setCommentUserUnlikeId(String commentUserUnlikeId) {
        put(COMMENT_USER_UNLIKE_ID, commentUserUnlikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getCommentId() {
        return getString(COMMENT_ID);
    }
    
    public void setCommentId(String commentId) {
        put(COMMENT_ID, commentId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }


}