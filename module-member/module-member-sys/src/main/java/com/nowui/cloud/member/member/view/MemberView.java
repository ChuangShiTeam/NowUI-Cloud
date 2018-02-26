package com.nowui.cloud.member.member.view;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_info")
public class MemberView extends BaseView {

    /**
     * 会员编号
     */
    @KeyId
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 是否置顶
     */
    @Field
    private Boolean memberIsTop;
    public static final String MEMBER_IS_TOP = "memberIsTop";

    /**
     * 会员置顶级别
     */
    @Field
    private Integer memberTopLevel;
    public static final String MEMBER_TOP_LEVEL = "memberTopLevel";

    /**
     * 置顶结束时间
     */
    @Field
    private Date memberTopEndTime;
    public static final String MEMBER_TOP_END_TIME = "memberTopEndTime";

    /**
     * 会员是否推荐
     */
    @Field
    private Boolean memberIsRecommed;
    public static final String MEMBER_IS_RECOMMED = "memberIsRecommed";

    /**
     * 省
     */
    @Field
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";

    /**
     * 市
     */
    @Field
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";

    /**
     * 区
     */
    @Field
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";

    /**
     * 详细地址
     */
    @Field
    private String memberAddressAddress;
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";
    
    /**
     * 会员背景
     */
    @Field
    private String memberBackground;    
    public static final String MEMBER_BACKGROUND = "memberBackground";
    
    /**
     * 会员背景图片文件路径
     */
    @Field
    private String memberBackgroundPath;    
    public static final String MEMBER_BACKGROUND_PATH = "memberBackgroundPath";
    
    /**
     * 会员签名
     */
    @Field
    private String memberSignature;    
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    /**
     * 会员偏好语言
     */
    @Field
    private String memberPreferenceLanguage;    
    public static final String MEMBER_PREFERENCE_LANGUAGE = "memberPreferenceLanguage";
    
    /**
     * 用户头像文件编号
     */
    @Field
    private String userAvatar;
    public static final String USER_AVATAR = "userAvatar";
    
    /**
     * 用户头像文件路径
     */
    @Field
    private String userAvatarPath;
    public static final String USER_AVATAR_PATH = "userAvatarPath";
    
    /**
     * 用户性别
     */
    @Field
    private String userSex;
    public static final String USER_SEX = "userSex";
    
    /**
     * 用户昵称
     */
    @Field
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
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

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public Boolean getMemberIsTop() {
        return getBoolean(MEMBER_IS_TOP);
    }

    public void setMemberIsTop(Boolean memberIsTop) {
        put(MEMBER_IS_TOP, memberIsTop);
    }

    public Integer getMemberTopLevel() {
        return getInteger(MEMBER_TOP_LEVEL);
    }

    public void setMemberTopLevel(Integer memberTopLevel) {
        put(MEMBER_TOP_LEVEL, memberTopLevel);
    }

    public Date getMemberTopEndTime() {
        return getDate(MEMBER_TOP_END_TIME);
    }

    public void setMemberTopEndTime(Date memberTopEndTime) {
        put(MEMBER_TOP_END_TIME, memberTopEndTime);
    }

    public Boolean getMemberIsRecommed() {
        return getBoolean(MEMBER_IS_RECOMMED);
    }

    public void setMemberIsRecommed(Boolean memberIsRecommed) {
        put(MEMBER_IS_RECOMMED, memberIsRecommed);
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
    
    public String getMemberBackground() {
        return getString(MEMBER_BACKGROUND);
    }

    public void setMemberBackground(String memberBackground) {
        put(MEMBER_BACKGROUND, memberBackground);
    }
    
    public String getMemberBackgroundPath() {
        return getString(MEMBER_BACKGROUND_PATH);
    }

    public void setMemberBackgroundPath(String memberBackgroundPath) {
        put(MEMBER_BACKGROUND_PATH, memberBackgroundPath);
    }

    public String getMemberSignature() {
        return getString(MEMBER_SIGNATURE);
    }

    public void setMemberSignature(String memberSignature) {
        put(MEMBER_SIGNATURE, memberSignature);
    }

    public String getMemberPreferenceLanguage() {
        return getString(MEMBER_PREFERENCE_LANGUAGE);
    }

    public void setMemberPreferenceLanguage(String memberPreferenceLanguage) {
        put(MEMBER_PREFERENCE_LANGUAGE, memberPreferenceLanguage);
    }

    public void setUserAvatar(String userAvatar) {
        put(USER_AVATAR, userAvatar);
    }
    
    public String getUserAvatarPath() {
        return getString(USER_AVATAR_PATH);
    }
    
    public void setUserAvatarPath(String userAvatarPath) {
        put(USER_AVATAR_PATH, userAvatarPath);
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

}