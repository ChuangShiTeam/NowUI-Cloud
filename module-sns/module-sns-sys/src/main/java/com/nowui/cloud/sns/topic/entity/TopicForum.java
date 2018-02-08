package com.nowui.cloud.sns.topic.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题论坛
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_forum_map")
public class TopicForum extends BaseEntity {

    /**
     * 话题论坛编号
     */
	@Id
    @TableId
    @NotNull(message = "话题论坛编号不能为空")
    @Length(max = 32, message = "话题论坛编号长度超出限制")
    private String topicForumId;
    public static final String TOPIC_FORUM_ID = "topicForumId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛编号
     */
    @TableField
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";
    
    
    /**
     * 话题相关信息
     */
    @TableField(exist = false)
    @NotNull(message = "话题相关信息不能为空")
    private JSONObject topicInfo;
    public static final String TOPIC_INFO = "topicInfo";
    

    public String getTopicForumId() {
        return getString(TOPIC_FORUM_ID);
    }
    
    public void setTopicForumId(String topicForumId) {
        put(TOPIC_FORUM_ID, topicForumId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

	public JSONObject getTopicInfo() {
        return getJSONObject(TOPIC_INFO);
	}

	public void setTopicInfo(JSONObject topicInfo) {
        put(TOPIC_INFO, topicInfo);
	}

}