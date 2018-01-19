package com.nowui.cloud.sns.topic.entity;

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
 * 话题信息
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "topic_info")
@TableName(value = "topic_info")
public class Topic extends BaseEntity {

    /**
     * 话题id
     */
	@Id
    @TableId
    @NotNull(message = "话题id不能为空")
    @Length(max = 32, message = "话题id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /** 
     * 论坛编号
     */
    @Field
    @TableField
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String topicForumId;
    public static final String TOPIC_FORUM_ID = "topicForumId";

    /**
     * 动态内容
     */
    @Field
    @TableField
    @NotNull(message = "动态内容不能为空")
    @Length(max = 1000, message = "动态长度超出限制")
    private String topicSummary;
    public static final String TOPIC_SUMMARY = "topicSummary";

    /**
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 经度
     */
    @Field
    @TableField
    @NotNull(message = "经度不能为空")
    @Length(max = 12, message = "经度长度超出限制")
    private String latitude;
    public static final String LATITUDE = "latitude";

    /**
     * 纬度
     */
    @Field
    @TableField
    @NotNull(message = "纬度不能为空")
    @Length(max = 12, message = "纬度长度超出限制")
    private String longtitude;
    public static final String LONGTITUDE = "longtitude";

    /**
     * 位置
     */
    @Field
    @TableField
    @NotNull(message = "位置不能为空")
    @Length(max = 200, message = "位置长度超出限制")
    private String topicLocation;
    public static final String TOPIC_LOCATION = "topicLocation";

    /**
     * 是否有位置
     */
    @Field
    @TableField
    @NotNull(message = "是否有位置不能为空")
    @Length(max = 1, message = "是否有位置长度超出限制")
    private Boolean topicIsLocation;
    public static final String TOPIC_IS_LOCATION = "topicIsLocation";

    /**
     * 置顶
     */
    @Field
    @TableField
    @NotNull(message = "置顶不能为空")
    @Length(max = 1, message = "置顶长度超出限制")
    private Boolean topicIsTop;
    public static final String TOPIC_IS_TOP = "topicIsTop";

    /**
     * 是否推荐
     */
    @Field
    @TableField
    @NotNull(message = "是否推荐不能为空")
    @Length(max = 1, message = "是否推荐长度超出限制")
    private Boolean topicIsRecommend;
    public static final String TOPIC_IS_RECOMAND = "topicIsRecommend";

    /**
     * 置顶级别
     */
    @Field
    @TableField
    @NotNull(message = "置顶级别不能为空")
    @Length(max = 11, message = "置顶级别长度超出限制")
    private Integer topTopLevel;
    public static final String TOP_TOP_LEVEL = "topTopLevel";
    
    /**
     * 论坛编号
     */
    public static final String FORUM_ID = "forumId";
    /**
     * 话题媒体list
     */
    public static final String TOPIC_MEDIA_LIST = "topicMediaList";
    
    /**
     * 话题所在论坛列表
     */
    public static final String TOPIC_FORUM_LIST = "topicForumList";
    /**
     * 话题被收藏数
     */
    public static final String TOPIC_COUNT_BOOK_MARK = "topicCountBookMark";
    /**
     * 话题被点赞数
     */
    public static final String TOPIC_COUNT_LIKE = "topicCountLike";
    /**
     * 话题被评论数
     */
    public static final String TOPIC_COUNT_COMMENT = "topicCountComment";
    /**
     * 话题提醒谁看列表
     */
    public static final String TOPIC_TIP_USER_LIST = "topicTipUserList";
    /**
     * 话题评论列表
     */
    public static final String TOPIC_COMMENT_LIST = "topicCommentList";
    /**
     * 话题是否被用户点赞
     */
    public static final String TOPIC_USER_IS_LIKE = "topicUserIsLike";
    /**
     * 话题是否被用户收藏
     */
    public static final String TOPIC_USER_IS_BOOKEMARK = "topicUserIsBookMark";
    
    
    
    
    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getTopicForumId() {
        return getString(TOPIC_FORUM_ID);
    }
    
    public void setTopicForumId(String topicForumId) {
        put(TOPIC_FORUM_ID, topicForumId);
    }

    public String getTopicSummary() {
        return getString(TOPIC_SUMMARY);
    }
    
    public void setTopicSummary(String topicSummary) {
        put(TOPIC_SUMMARY, topicSummary);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getLatitude() {
        return getString(LATITUDE);
    }
    
    public void setLatitude(String latitude) {
        put(LATITUDE, latitude);
    }

    public String getLongtitude() {
        return getString(LONGTITUDE);
    }
    
    public void setLongtitude(String longtitude) {
        put(LONGTITUDE, longtitude);
    }

    public String getTopicLocation() {
        return getString(TOPIC_LOCATION);
    }
    
    public void setTopicLocation(String topicLocation) {
        put(TOPIC_LOCATION, topicLocation);
    }

    public Boolean getTopicIsLocation() {
        return getBoolean(TOPIC_IS_LOCATION);
    }
    
    public void setTopicIsLocation(Boolean topicIsLocation) {
        put(TOPIC_IS_LOCATION, topicIsLocation);
    }

    public Boolean getTopicIsTop() {
        return getBoolean(TOPIC_IS_TOP);
    }
    
    public void setTopicIsTop(Boolean topicIsTop) {
        put(TOPIC_IS_TOP, topicIsTop);
    }

    public Boolean getTopicIsRecommend() {
        return getBoolean(TOPIC_IS_RECOMAND);
    }
    
    public void setTopicIsRecommend(Boolean topicIsRecommend) {
        put(TOPIC_IS_RECOMAND, topicIsRecommend);
    }

    public Integer getTopTopLevel() {
        return getInteger(TOP_TOP_LEVEL);
    }
    
    public void setTopTopLevel(Integer topTopLevel) {
        put(TOP_TOP_LEVEL, topTopLevel);
    }


}