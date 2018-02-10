package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题评论的取消点赞视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_comment_user_unlike_info")
public class TopicCommentUserUnlikeView extends BaseView {

    /**
     * 话题评论的取消点赞编号
     */
    @KeyId
    @Field
    @NotNull(message = "话题评论的取消点赞编号不能为空")
    private String commentUserUnlikeId;
    public static final String COMMENT_USER_UNLIKE_ID = "commentUserUnlikeId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 被取消点赞的话题评论编号
     */
    @Field
    @NotNull(message = "被取消点赞的话题评论编号不能为空")
    private String commentId;
    public static final String COMMENT_ID = "commentId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
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