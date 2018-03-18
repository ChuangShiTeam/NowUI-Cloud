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
 * 话题提示
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_tip_info")
public class TopicTip extends BaseEntity {

    /**
     * 话题提示编号
     */
	@Id
    @TableId
    @NotNull(message = "话题提示编号不能为空")
    @Length(max = 32, message = "话题提示编号长度超出限制")
    private String topicTipId;
    public static final String TOPIC_TIP_ID = "topicTipId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 发送用户
     */
    @TableField
    @NotNull(message = "发送用户不能为空")
    @Length(max = 32, message = "发送用户长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
	public String getTopicTipId() {
		return topicTipId;
	}
	public void setTopicTipId(String topicTipId) {
		this.topicTipId = topicTipId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



}