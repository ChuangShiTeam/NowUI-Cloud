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
 * 点赞话题关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_like_info")
public class TopicUserLike extends BaseEntity {

    /**
     * 用户话题点赞id
     */
	@Id
    @TableId
    @NotNull(message = "用户话题点赞id不能为空")
    @Length(max = 32, message = "用户话题点赞id长度超出限制")
    private String topicUserLikeId;
    public static final String TOPIC_USER_LIKE_ID = "topicUserLikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 点赞人编号
     */
    @TableField
    @NotNull(message = "点赞人编号不能为空")
    @Length(max = 32, message = "关注人编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 点赞人昵称
     */
    @TableField(exist = false)
    @NotNull(message = "昵称不能为空")
    @Length(max = 32, message = "昵称长度超出限制")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 点赞人头像
     */
    @TableField(exist = false)
    @NotNull(message = "头像不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    public static final String TOPIC_USER_LIKE_IS_SELF = "topicUserLikeIsSelf";

	public String getTopicUserLikeId() {
		return topicUserLikeId;
	}

	public void setTopicUserLikeId(String topicUserLikeId) {
		this.topicUserLikeId = topicUserLikeId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserAvatarFilePath() {
		return userAvatarFilePath;
	}

	public void setUserAvatarFilePath(String userAvatarFilePath) {
		this.userAvatarFilePath = userAvatarFilePath;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}