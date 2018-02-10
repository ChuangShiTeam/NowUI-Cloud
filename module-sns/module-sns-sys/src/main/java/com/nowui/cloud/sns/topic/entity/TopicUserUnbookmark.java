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
 * 话题用户取消收藏关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_unbookmark_info")
public class TopicUserUnbookmark extends BaseEntity {

    /**
     * 用户话题取消收藏id
     */
	@Id
    @TableId
    @NotNull(message = "用户话题取消收藏id不能为空")
    @Length(max = 32, message = "用户话题取消收藏id长度超出限制")
    private String topicUserUnbookmarkId;
    public static final String TOPIC_USER_UNBOOKMARK_ID = "topicUserUnbookmarkId";

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
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";


    public String getTopicUserUnbookmarkId() {
        return getString(TOPIC_USER_UNBOOKMARK_ID);
    }
    
    public void setUserUnbookmarkId(String topicUserUnbookmarkId) {
        put(TOPIC_USER_UNBOOKMARK_ID, topicUserUnbookmarkId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }


}