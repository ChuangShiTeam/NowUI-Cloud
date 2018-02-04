package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.view.BaseView;

import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_info")
public class TopicView extends BaseView {

    /**
     * 话题编号
     */
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 话题内容
     */
    @Field
    @NotNull(message = "话题内容不能为空")
    private String topicSummary;
    public static final String TOPIC_SUMMARY = "topicSummary";

    /**
     * 是否有位置
     */
    @Field
    @NotNull(message = "是否有位置不能为空")
    private Boolean topicIsLocation;
    public static final String TOPIC_IS_LOCATION = "topicIsLocation";

    /**
     * 纬度
     */
    @Field
    @NotNull(message = "纬度不能为空")
    private String latitude;
    public static final String LATITUDE = "latitude";

    /**
     * 经度
     */
    @Field
    @NotNull(message = "经度不能为空")
    private String longtitude;
    public static final String LONGTITUDE = "longtitude";

    /**
     * 位置
     */
    @Field
    @NotNull(message = "位置不能为空")
    private String topicLocation;
    public static final String TOPIC_LOCATION = "topicLocation";

    /**
     * 置顶
     */
    @Field
    @NotNull(message = "置顶不能为空")
    private Boolean topicIsTop;
    public static final String TOPIC_IS_TOP = "topicIsTop";

    /**
     * 置顶级别
     */
    @Field
    @NotNull(message = "置顶级别不能为空")
    private Integer topicTopLevel;
    public static final String TOPIC_TOP_LEVEL = "topicTopLevel";

    /**
     * 置顶截止时间
     */
    @Field
    @NotNull(message = "置顶截止时间不能为空")
    private Date topicTopEndTime;
    public static final String TOPIC_TOP_END_TIME = "topicTopEndTime";

    /**
     * 是否推荐
     */
    @Field
    @NotNull(message = "是否推荐不能为空")
    private Boolean topicIsRecommend;
    public static final String TOPIC_IS_RECOMMEND = "topicIsRecommend";


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

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getTopicSummary() {
        return getString(TOPIC_SUMMARY);
    }

    public void setTopicSummary(String topicSummary) {
        put(TOPIC_SUMMARY, topicSummary);
    }

    public Boolean getTopicIsLocation() {
        return getBoolean(TOPIC_IS_LOCATION);
    }

    public void setTopicIsLocation(Boolean topicIsLocation) {
        put(TOPIC_IS_LOCATION, topicIsLocation);
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

    public Boolean getTopicIsTop() {
        return getBoolean(TOPIC_IS_TOP);
    }

    public void setTopicIsTop(Boolean topicIsTop) {
        put(TOPIC_IS_TOP, topicIsTop);
    }

    public Integer getTopicTopLevel() {
        return getInteger(TOPIC_TOP_LEVEL);
    }

    public void setTopicTopLevel(Integer topicTopLevel) {
        put(TOPIC_TOP_LEVEL, topicTopLevel);
    }

    public Date getTopicTopEndTime() {
        return getDate(TOPIC_TOP_END_TIME);
    }

    public void setTopicTopEndTime(Date topicTopEndTime) {
        put(TOPIC_TOP_END_TIME, topicTopEndTime);
    }

    public Boolean getTopicIsRecommend() {
        return getBoolean(TOPIC_IS_RECOMMEND);
    }

    public void setTopicIsRecommend(Boolean topicIsRecommend) {
        put(TOPIC_IS_RECOMMEND, topicIsRecommend);
    }


}