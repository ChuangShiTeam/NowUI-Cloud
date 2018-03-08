package com.nowui.cloud.sns.topic.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 话题信息
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_info")
public class Topic extends BaseEntity {

    /**
     * 话题编号
     */
	@Id
    @TableId
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 发布动态的用户的昵称
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的用户的昵称不能为空")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 发布动态的用户头像
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的用户头像不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 发布动态的用户签名
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的用户签名不能为空")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";

    /**
     * 动态内容
     */
    @TableField
    @NotNull(message = "动态内容不能为空")
    @Length(max = 1000, message = "动态长度超出限制")
    private String topicSummary;
    public static final String TOPIC_SUMMARY = "topicSummary";

    /**
     * 纬度
     */
    @TableField
    @NotNull(message = "纬度不能为空")
    @Length(max = 12, message = "纬度长度超出限制")
    private String latitude;
    public static final String LATITUDE = "latitude";

    /**
     * 经度
     */
    @TableField
    @NotNull(message = "经度不能为空")
    @Length(max = 12, message = "经度长度超出限制")
    private String longtitude;
    public static final String LONGTITUDE = "longtitude";

    /**
     * 位置
     */
    @TableField
    @NotNull(message = "位置不能为空")
    @Length(max = 200, message = "位置长度超出限制")
    private String topicLocation;
    public static final String TOPIC_LOCATION = "topicLocation";

    /**
     * 是否有位置
     */
    @TableField
    @NotNull(message = "是否有位置不能为空")
    private Boolean topicIsLocation;
    public static final String TOPIC_IS_LOCATION = "topicIsLocation";

    /**
     * 置顶
     */
    @TableField
    @NotNull(message = "置顶不能为空")
    private Boolean topicIsTop;
    public static final String TOPIC_IS_TOP = "topicIsTop";

    /**
     * 是否推荐
     */
    @TableField
    @NotNull(message = "是否推荐不能为空")
    private Boolean topicIsRecommend;
    public static final String TOPIC_IS_RECOMAND = "topicIsRecommend";

    /**
     * 置顶级别
     */
    @TableField
    @NotNull(message = "置顶级别不能为空")
    private Integer topicTopLevel;
    public static final String TOPIC_TOP_LEVEL = "topicTopLevel";
    
    /**
     * 置顶截止时间
     */
    @TableField
    private Date topicTopEndTime;
    public static final String TOPIC_TOP_END_TIME = "topicTopEndTime";

    
    /**
     * 话题媒体list
     */
    @TableField(exist = false)
    private JSONObject topicMediaList;
    public static final String TOPIC_MEDIA_LIST = "topicMediaList";
    
    /**
     * 话题提醒谁看列表
     */
    @TableField(exist = false)
    private JSONObject topicTipUserList;
    public static final String TOPIC_TIP_USER_LIST = "topicTipUserList";
    
    /**
     * 话题所在论坛列表
     */
    @TableField(exist = false)
    private JSONObject topicForumList;
    public static final String TOPIC_FORUM_LIST = "topicForumList";
    
    
    /**
     * 话题被收藏数
     */
    public static final String TOPIC_COUNT_BOOKMARK = "topicCountBookmark";
    /**
     * 话题被点赞数
     */
    public static final String TOPIC_COUNT_LIKE = "topicCountLike";
    /**
     * 给话题点赞的用户列表
     */
    public static final String TOPIC_USER_LIKE_LIST = "topicUserLikeList";
    /**
     * 话题被评论数
     */
    public static final String TOPIC_COUNT_COMMENT = "topicCountComment";
    
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
    public static final String TOPIC_USER_IS_BOOKEMARK = "topicUserIsBookmark";
    
    /**
     * 话题是否是自己发的
     */
    public static final String TOPIC_IS_SELF = "topicIsSelf";
    
    /**
     * 话题发布人
     */
    public static final String TOPIC_USER = "topicUser";
    
   /**
    * 请求用户
    */
    public static final String REQUEST_USER = "requestUser";
    
    /**
     * 排除的话题Id列表
     */
    public static final String EXCLUDE_TOPIC_ID_LIST = "excludeTopicIdList";
    /**
     * 话题评论查询第几页
     */
    public static final String COMMENT_PAGE_INDEX = "commentPageIndex";
    /**
     * 话题评论查询多少条
     */
    public static final String COMMENT_PAGE_SIZE = "commentPageSize";
    
    
    
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

    public String getTopicSummary() {
        return getString(TOPIC_SUMMARY);
    }
    
    public void setTopicSummary(String topicSummary) {
        put(TOPIC_SUMMARY, topicSummary);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
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