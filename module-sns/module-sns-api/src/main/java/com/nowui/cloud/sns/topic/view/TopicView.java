package com.nowui.cloud.sns.topic.view;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;
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
    @KeyId
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 应用编号
     */
    @KeyId
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 发布动态的用户的昵称
     */
    @Field
    @NotNull(message = "发布动态的用户的昵称不能为空")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 发布动态的用户头像
     */
    @Field
    @NotNull(message = "发布动态的用户头像不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 发布动态的用户签名
     */
    @Field
    @NotNull(message = "发布动态的用户签名不能为空")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
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
    
    /**
     * 话题媒体list
     */
    @Field
    private JSONObject topicMediaList;
    public static final String TOPIC_MEDIA_LIST = "topicMediaList";
    
    /**
     * 话题提醒谁看列表
     */
    @Field
    private JSONObject topicTipUserList;
    public static final String TOPIC_TIP_USER_LIST = "topicTipUserList";
    
    /**
     * 话题所在论坛列表
     */
    @Field
    private JSONObject topicForumList;
    public static final String TOPIC_FORUM_LIST = "topicForumList";
    
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

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
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
    
    public JSONObject getTopicMediaList() {
        return getJSONObject(TOPIC_MEDIA_LIST);
	}

	public void setTopicMediaList(JSONObject topicMediaList) {
        put(TOPIC_MEDIA_LIST, topicMediaList);
	}

	public JSONObject getTopicTipUserList() {
        return getJSONObject(TOPIC_TIP_USER_LIST);
	}

	public void setTopicTipUserList(JSONObject topicTipUserList) {
        put(TOPIC_TIP_USER_LIST, topicTipUserList);
	}

	public JSONObject getTopicForumList() {
        return getJSONObject(TOPIC_FORUM_LIST);
	}

	public void setTopicForumList(JSONObject topicForumList) {
        put(TOPIC_FORUM_LIST, topicForumList);
	}

	public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
    }
    
    public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
    }

	public String getUserNickName() {
		return getString(USER_NICKNAME);
	}

	public void setUserNickName(String userNickName) {
		put(USER_NICKNAME, userNickName);
	}

	public String getMemberSignature() {
		return getString(MEMBER_SIGNATURE);
	}

	public void setMemberSignature(String memberSignature) {
		put(MEMBER_SIGNATURE, memberSignature);
	}
	
	


}