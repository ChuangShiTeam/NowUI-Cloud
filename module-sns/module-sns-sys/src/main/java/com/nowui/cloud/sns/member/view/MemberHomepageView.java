package com.nowui.cloud.sns.member.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题提醒视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "member_homepage")
public class MemberHomepageView extends BaseView {

    /**
     * 会员主页编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员主页编号不能为空")
    private String memberHomepageId;
    public static final String MEMBER_HOMEPAGE_ID = "memberHomepageId";

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
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 用户性别
     */
    @Field
    private String userSex;
    public static final String USER_SEX = "userSex";
    
    /**
     * 用户头像路径
     */
    @Field
    @NotNull(message = "用户头像路径不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 用户昵称
     */
    @Field
    @NotNull(message = "用户昵称不能为空")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 会员签名
     */
    @Field
    @NotNull(message = "会员签名不能为空")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    /**
     * 主页背景
     */
    @Field
    @NotNull(message = "主页背景不能为空")
    private String memberBackgroundFilePath;
    public static final String MEMBER_BACKGROUND_FILE_PATH = "memberBackgroundFilePath";
    /**
     * 会员地址--省份
     */
    @Field
    @NotNull(message = "会员地址--省份不能为空")
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";
    
    /**
     * 会员地址--城市
     */
    @Field
    @NotNull(message = "会员地址--城市不能为空")
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";
    
    /**
     * 会员地址--区
     */
    @Field
    @NotNull(message = "会员地址--区不能为空")
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";
    
    /**
     * 会员地址--详细地址
     */
    @Field
    @NotNull(message = "会员地址--详细地址不能为空")
    private String memberAddressAddress;
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";

    /**
     * 动态数
     */
    @Field
    @NotNull(message = "动态数不能为空")
    private Integer memberSendTopicCount;
    public static final String MEMBER_SEND_TOPIC_COUNT = "memberSendTopicCount";
    
    /**
     * 关注数
     */
    @Field
    @NotNull(message = "关注数不能为空")
    private Integer memberFollowCount;
    public static final String MEMBER_FOLLOW_COUNT = "memberFollowCount";
    
    /**
     * 粉丝数
     */
    @Field
    @NotNull(message = "粉丝数不能为空")
    private Integer memberBeFollowCount;
    public static final String MEMBER_BE_FOLLOW_COUNT = "memberBeFollowCount";
    
    /**
     * 宠物
     */
    @Field
    @NotNull(message = "宠物不能为空")
    private String pet;
    public static final String PET = "pet";
    
    
	public String getMemberHomepageId() {
		return memberHomepageId;
	}
	public void setMemberHomepageId(String memberHomepageId) {
		this.memberHomepageId = memberHomepageId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserAvatarFilePath() {
		return userAvatarFilePath;
	}
	public void setUserAvatarFilePath(String userAvatarFilePath) {
		this.userAvatarFilePath = userAvatarFilePath;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getMemberSignature() {
		return memberSignature;
	}
	public void setMemberSignature(String memberSignature) {
		this.memberSignature = memberSignature;
	}
	public String getMemberBackgroundFilePath() {
		return memberBackgroundFilePath;
	}
	public void setMemberBackgroundFilePath(String memberBackgroundFilePath) {
		this.memberBackgroundFilePath = memberBackgroundFilePath;
	}
	public String getMemberAddressProvince() {
		return memberAddressProvince;
	}
	public void setMemberAddressProvince(String memberAddressProvince) {
		this.memberAddressProvince = memberAddressProvince;
	}
	public String getMemberAddressCity() {
		return memberAddressCity;
	}
	public void setMemberAddressCity(String memberAddressCity) {
		this.memberAddressCity = memberAddressCity;
	}
	public String getMemberAddressArea() {
		return memberAddressArea;
	}
	public void setMemberAddressArea(String memberAddressArea) {
		this.memberAddressArea = memberAddressArea;
	}
	public String getMemberAddressAddress() {
		return memberAddressAddress;
	}
	public void setMemberAddressAddress(String memberAddressAddress) {
		this.memberAddressAddress = memberAddressAddress;
	}
	public Integer getMemberSendTopicCount() {
		return memberSendTopicCount;
	}
	public void setMemberSendTopicCount(Integer memberSendTopicCount) {
		this.memberSendTopicCount = memberSendTopicCount;
	}
	public Integer getMemberFollowCount() {
		return memberFollowCount;
	}
	public void setMemberFollowCount(Integer memberFollowCount) {
		this.memberFollowCount = memberFollowCount;
	}
	public Integer getMemberBeFollowCount() {
		return memberBeFollowCount;
	}
	public void setMemberBeFollowCount(Integer memberBeFollowCount) {
		this.memberBeFollowCount = memberBeFollowCount;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}

}