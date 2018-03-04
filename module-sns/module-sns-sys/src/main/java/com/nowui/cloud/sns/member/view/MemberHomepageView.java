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
    
    // TODO ,,,,动态数,关注数,粉丝数
    
    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
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
    
    
    
    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberAddressProvince() {
        return getString(MEMBER_ADDRESS_PROVINCE);
    }

    public void setMemberAddressProvince(String memberAddressProvince) {
        put(MEMBER_ADDRESS_PROVINCE, memberAddressProvince);
    }

    public String getMemberAddressCity() {
        return getString(MEMBER_ADDRESS_CITY);
    }

    public void setMemberAddressCity(String memberAddressCity) {
        put(MEMBER_ADDRESS_CITY, memberAddressCity);
    }

    public String getMemberAddressArea() {
        return getString(MEMBER_ADDRESS_AREA);
    }

    public void setMemberAddressArea(String memberAddressArea) {
        put(MEMBER_ADDRESS_AREA, memberAddressArea);
    }

    public String getMemberAddressAddress() {
        return getString(MEMBER_ADDRESS_ADDRESS);
    }

    public void setMemberAddressAddress(String memberAddressAddress) {
        put(MEMBER_ADDRESS_ADDRESS, memberAddressAddress);
    }
    
    public String getMemberBackgroundFilePath() {
        return getString(MEMBER_BACKGROUND_FILE_PATH);
    }

    public void setMemberBackgroundFilePath(String memberBackgroundFilePath) {
        put(MEMBER_BACKGROUND_FILE_PATH, memberBackgroundFilePath);
    }

    public String getMemberSignature() {
        return getString(MEMBER_SIGNATURE);
    }

    public void setMemberSignature(String memberSignature) {
        put(MEMBER_SIGNATURE, memberSignature);
    }

    
    
    public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
    }
    
    public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
    }

    public String getUserSex() {
        return getString(USER_SEX);
    }

    public void setUserSex(String userSex) {
        put(USER_SEX, userSex);
    }

    public String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
    }

    
	public String getMemberHomepageId() {
		return getString(MEMBER_HOMEPAGE_ID);
	}

	public void setMemberHomepageId(String memberHomepageId) {
		put(MEMBER_HOMEPAGE_ID, memberHomepageId);
	}

	public Integer getMemberSendTopicCount() {
		return getInteger(MEMBER_SEND_TOPIC_COUNT);
	}

	public void setMemberSendTopicCount(Integer memberSendTopicCount) {
		put(MEMBER_SEND_TOPIC_COUNT, memberSendTopicCount);
	}

	public Integer getMemberFollowCount() {
		return getInteger(MEMBER_FOLLOW_COUNT);
	}

	public void setMemberFollowCount(Integer memberFollowCount) {
		put(MEMBER_FOLLOW_COUNT, memberFollowCount);
	}

	public Integer getMemberBeFollowCount() {
		return getInteger(MEMBER_BE_FOLLOW_COUNT);
	}

	public void setMemberBeFollowCount(Integer memberBeFollowCount) {
		put(MEMBER_BE_FOLLOW_COUNT, memberBeFollowCount);
	}

	public String getPet() {
		return getString(PET);
	}

	public void setPet(String pet) {
		put(PET, pet);
	}

}