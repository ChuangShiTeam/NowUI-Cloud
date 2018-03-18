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
     * 请求者的会员编号(用于查看别人的主页等类似接口)
     */
    @TableField(exist = false)
    @NotNull(message = "请求者的会员编号不能为空")
    @Length(max = 32, message = "请求者的会员编号长度超出限制")
    private String requestMemberId;
    public static final String REQUEST_MEMBER_ID = "requestMemberId";
    
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
    @TableField(exist = false)
    @NotNull(message = "话题被收藏数不能为空")
    private Integer topicCountBookmark;
    public static final String TOPIC_COUNT_BOOKMARK = "topicCountBookmark";
    
    /**
     * 话题被点赞数
     */
    @TableField(exist = false)
    @NotNull(message = "话题被点赞数不能为空")
    private Integer topicCountLike;
    public static final String TOPIC_COUNT_LIKE = "topicCountLike";
    
    /**
     * 话题被评论数
     */
    @TableField(exist = false)
    @NotNull(message = "话题被评论数不能为空")
    private Integer topicCountComment;
    public static final String TOPIC_COUNT_COMMENT = "topicCountComment";
    
    /**
     * 给话题点赞的用户列表
     */
    public static final String TOPIC_USER_LIKE_LIST = "topicUserLikeList";
    
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
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
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

	public String getRequestMemberId() {
		return requestMemberId;
	}

	public void setRequestMemberId(String requestMemberId) {
		this.requestMemberId = requestMemberId;
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

	public String getMemberSignature() {
		return memberSignature;
	}

	public void setMemberSignature(String memberSignature) {
		this.memberSignature = memberSignature;
	}

	public String getTopicSummary() {
		return topicSummary;
	}

	public void setTopicSummary(String topicSummary) {
		this.topicSummary = topicSummary;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getTopicLocation() {
		return topicLocation;
	}

	public void setTopicLocation(String topicLocation) {
		this.topicLocation = topicLocation;
	}

	public Boolean getTopicIsLocation() {
		return topicIsLocation;
	}

	public void setTopicIsLocation(Boolean topicIsLocation) {
		this.topicIsLocation = topicIsLocation;
	}

	public Boolean getTopicIsTop() {
		return topicIsTop;
	}

	public void setTopicIsTop(Boolean topicIsTop) {
		this.topicIsTop = topicIsTop;
	}

	public Boolean getTopicIsRecommend() {
		return topicIsRecommend;
	}

	public void setTopicIsRecommend(Boolean topicIsRecommend) {
		this.topicIsRecommend = topicIsRecommend;
	}

	public Integer getTopicTopLevel() {
		return topicTopLevel;
	}

	public void setTopicTopLevel(Integer topicTopLevel) {
		this.topicTopLevel = topicTopLevel;
	}

	public Date getTopicTopEndTime() {
		return topicTopEndTime;
	}

	public void setTopicTopEndTime(Date topicTopEndTime) {
		this.topicTopEndTime = topicTopEndTime;
	}

	public JSONObject getTopicMediaList() {
		return topicMediaList;
	}

	public void setTopicMediaList(JSONObject topicMediaList) {
		this.topicMediaList = topicMediaList;
	}

	public JSONObject getTopicTipUserList() {
		return topicTipUserList;
	}

	public void setTopicTipUserList(JSONObject topicTipUserList) {
		this.topicTipUserList = topicTipUserList;
	}

	public JSONObject getTopicForumList() {
		return topicForumList;
	}

	public void setTopicForumList(JSONObject topicForumList) {
		this.topicForumList = topicForumList;
	}

	public Integer getTopicCountBookmark() {
		return topicCountBookmark;
	}

	public void setTopicCountBookmark(Integer topicCountBookmark) {
		this.topicCountBookmark = topicCountBookmark;
	}

	public Integer getTopicCountLike() {
		return topicCountLike;
	}

	public void setTopicCountLike(Integer topicCountLike) {
		this.topicCountLike = topicCountLike;
	}

	public Integer getTopicCountComment() {
		return topicCountComment;
	}

	public void setTopicCountComment(Integer topicCountComment) {
		this.topicCountComment = topicCountComment;
	}
    
	
}