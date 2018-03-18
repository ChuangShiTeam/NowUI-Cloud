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
 * 话题用户取消点赞关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_unlike_info")
public class TopicUserUnlike extends BaseEntity {

    /**
     * 用户话题取消点赞id
     */
	@Id
    @TableId
    @NotNull(message = "用户话题取消点赞id不能为空")
    @Length(max = 32, message = "用户话题取消点赞id长度超出限制")
    private String topicUserUnlikeId;
    public static final String TOPIC_USER_UNLIKE_ID = "topicUserUnlikeId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 取消点会员编号
     */
    @TableField
    @NotNull(message = "取消点会员编号不能为空")
    @Length(max = 32, message = "取消点会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";
    
	public String getTopicUserUnlikeId() {
		return topicUserUnlikeId;
	}
	public void setTopicUserUnlikeId(String topicUserUnlikeId) {
		this.topicUserUnlikeId = topicUserUnlikeId;
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
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}