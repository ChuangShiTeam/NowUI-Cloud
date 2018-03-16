package com.nowui.cloud.member.member.view;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_info")
public class MemberView extends BaseView {

    /**
     * 会员编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 是否置顶
     */
    @Field
    @NotNull(message = "是否置顶不能为空")
    @Digits(integer = 1, fraction = 0, message = "是否置顶长度超出限制")
    private Boolean memberIsTop;
    public static final String MEMBER_IS_TOP = "memberIsTop";

    /**
     * 会员置顶级别
     */
    @Field
    @NotNull(message = "会员置顶级别不能为空")
    @Digits(integer = 5, fraction = 0, message = "会员置顶级别长度超出限制")
    private Integer memberTopLevel;
    public static final String MEMBER_TOP_LEVEL = "memberTopLevel";

    /**
     * 置顶结束时间
     */
    @Field
    @NotNull(message = "置顶结束时间不能为空")
    @Past
    private Date memberTopEndTime;
    public static final String MEMBER_TOP_END_TIME = "memberTopEndTime";

    /**
     * 会员是否推荐
     */
    @Field
    @NotNull(message = "会员是否推荐不能为空")
    @Digits(integer = 1, fraction = 0, message = "会员是否推荐长度超出限制")
    private Boolean memberIsRecommed;
    public static final String MEMBER_IS_RECOMMED = "memberIsRecommed";
    
    /**
     * 会员账号
     */
    @Field
    @NotNull(message = "会员账号不能为空")
    @Length(max = 30, message = "会员账号长度超出限制")
    private String memberAccount;
    public static final String MEMBER_ACCOUNT = "memberAccount";
    
    /**
     * 省
     */
    @Field
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";

    /**
     * 市
     */
    @Field
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";

    /**
     * 区
     */
    @Field
    @NotNull(message = "区不能为空")
    @Length(max = 50, message = "区长度超出限制")
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";

    /**
     * 详细地址
     */
    @Field
    @NotNull(message = "详细地址不能为空")
    @Length(max = 200, message = "详细地址长度超出限制")
    private String memberAddressAddress;
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";
    
    /**
     * 会员头像文件编号
     */
    @Field
    @NotNull(message = "会员头像文件编号不能为空")
    @Length(max = 32, message = "会员头像文件编号长度超出限制")
    private String memberAvatarFileId;
    public static final String MEMBER_AVATAR_FILE_ID = "memberAvatarFileId";
    
    /**
     * 会员头像文件路径
     */
    @Field
    @NotNull(message = "会员头像文件路径不能为空")
    @Length(max = 200, message = "会员头像文件路径长度超出限制")
    private String memberAvatarFilePath;
    public static final String MEMBER_AVATAR_FILE_PATH = "memberAvatarFilePath";
    
    /**
     * 会员背景文件编号
     */
    @Field
    @NotNull(message = "会员背景文件编号不能为空")
    @Length(max = 32, message = "会员背景文件编号长度超出限制")
    private String memberBackgroundFileId;
    public static final String MEMBER_BACKGROUND_FILE_ID = "memberBackgroundFileId";
    
    /**
     * 会员背景文件路径
     */
    @Field
    @NotNull(message = "会员背景文件编号不能为空")
    @Length(max = 200, message = "会员背景文件编号长度超出限制")
    private String memberBackgroundFilePath;
    public static final String MEMBER_BACKGROUND_FILE_PATH = "memberBackgroundFilePath";
    
    /**
     * 会员邮箱
     */
    @Field
    @NotNull(message = "会员邮箱不能为空")
    @Length(max = 50, message = "会员邮箱长度超出限制")
    private String memberEmail;
    public static final String MEMBER_EMAIL = "memberEmail";
    
    /**
     * 会员手机号码
     */
    @Field
    @NotNull(message = "会员手机号码不能为空")
    @Length(max = 11, message = "会员手机号码长度超出限制")
    private String memberMobile;
    public static final String MEMBER_MOBILE = "memberMobile";
    
    /**
     * 会员昵称
     */
    @Field
    @NotNull(message = "会员昵称不能为空")
    @Length(max = 200, message = "会员昵称长度超出限制")
    private String memberNickName;
    public static final String MEMBER_NICK_NAME = "memberNickName";
    
    /**
     * 会员密码
     */
    @Field
    @NotNull(message = "会员密码不能为空")
    @Length(max = 128, message = "会员密码长度超出限制")
    private String memberPassword;
    public static final String MEMBER_PASSWORD = "memberPassword";
    
    /**
     * 会员偏好语言
     */
    @Field
    @NotNull(message = "会员偏好语言不能为空")
    @Length(max = 100, message = "会员偏好语言长度超出限制")
    private String memberPreferenceLanguage;
    public static final String MEMBER_PREFERENCE_LANGUAGE = "memberPreferenceLanguage";
    
    /**
     * 会员性别
     */
    @Field
    @NotNull(message = "会员性别不能为空")
    @Length(max = 1, message = "会员性别长度超出限制")
    private String memberSex;
    public static final String MEMBER_SEX = "memberSex";
    
    /**
     * 会员签名
     */
    @Field
    @NotNull(message = "会员签名不能为空")
    @Length(max = 500, message = "会员签名长度超出限制")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    /**
     * 微信用户唯一标识
     */
    @Field
    @NotNull(message = "微信用户唯一标识不能为空")
    @Length(max = 50, message = "微信用户唯一标识长度超出限制")
    private String wechatOpenId;
    public static final String WECHAT_OPEN_ID = "wechatOpenId";

    /**
     * 微信多应用用户共享唯一标识
     */
    @Field
    @NotNull(message = "微信多应用用户共享唯一标识不能为空")
    @Length(max = 50, message = "微信多应用用户共享唯一标识长度超出限制")
    private String wechatUnionId;
    public static final String WECHAT_UNION_ID = "wechatUnionId";

    /**
     * 微信昵称
     */
    @Field
    @NotNull(message = "微信昵称不能为空")
    @Length(max = 100, message = "微信昵称长度超出限制")
    private String wechatNickName;
    public static final String WECHAT_NICK_NAME = "wechatNickName";

    /**
     * 性别
     */
    @Field
    @NotNull(message = "性别不能为空")
    @Length(max = 1, message = "性别长度超出限制")
    private String wechatSex;
    public static final String WECHAT_SEX = "wechatSex";

    /**
     * 国家
     */
    @Field
    @NotNull(message = "国家不能为空")
    @Length(max = 50, message = "国家长度超出限制")
    private String wechatCountry;
    public static final String WECHAT_COUNTRY = "wechatCountry";

    /**
     * 省
     */
    @Field
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String wechatProvince;
    public static final String WECHAT_PROVINCE = "wechatProvince";

    /**
     * 市
     */
    @Field
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String wechatCity;
    public static final String WECHAT_CITY = "wechatCity";

    /**
     * 微信头像本地文件Id
     */
    @Field
    @NotNull(message = "微信头像本地文件Id不能为空")
    @Length(max = 32, message = "微信头像本地文件Id长度超出限制")
    private String wechatHeadImgFileId;
    public static final String WECHAT_HEAD_IMG_FILE_ID = "wechatHeadImgFileId";

    /**
     * 微信头像地址
     */
    @Field
    @NotNull(message = "微信头像地址不能为空")
    @Length(max = 200, message = "微信头像地址长度超出限制")
    private String wechatHeadImgUrl;
    public static final String WECHAT_HEAD_IMG_URL = "wechatHeadImgUrl";

    /**
     * 微信用户特权
     */
    @Field
    @NotNull(message = "微信用户特权不能为空")
    @Length(max = 200, message = "微信用户特权长度超出限制")
    private String wehchatPrivilege;
    public static final String WEHCHAT_PRIVILEGE = "wehchatPrivilege";

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
    
    public Boolean getMemberIsTop() {
        return memberIsTop;
    }

    public void setMemberIsTop(Boolean memberIsTop) {
        this.memberIsTop = memberIsTop;
    }
    
    public Integer getMemberTopLevel() {
        return memberTopLevel;
    }

    public void setMemberTopLevel(Integer memberTopLevel) {
        this.memberTopLevel = memberTopLevel;
    }
    
    public Date getMemberTopEndTime() {
        return memberTopEndTime;
    }

    public void setMemberTopEndTime(Date memberTopEndTime) {
        this.memberTopEndTime = memberTopEndTime;
    }
    
    public Boolean getMemberIsRecommed() {
        return memberIsRecommed;
    }

    public void setMemberIsRecommed(Boolean memberIsRecommed) {
        this.memberIsRecommed = memberIsRecommed;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
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

    public String getMemberAvatarFileId() {
        return memberAvatarFileId;
    }

    public void setMemberAvatarFileId(String memberAvatarFileId) {
        this.memberAvatarFileId = memberAvatarFileId;
    }

    public String getMemberAvatarFilePath() {
        return memberAvatarFilePath;
    }

    public void setMemberAvatarFilePath(String memberAvatarFilePath) {
        this.memberAvatarFilePath = memberAvatarFilePath;
    }

    public String getMemberBackgroundFileId() {
        return memberBackgroundFileId;
    }

    public void setMemberBackgroundFileId(String memberBackgroundFileId) {
        this.memberBackgroundFileId = memberBackgroundFileId;
    }

    public String getMemberBackgroundFilePath() {
        return memberBackgroundFilePath;
    }

    public void setMemberBackgroundFilePath(String memberBackgroundFilePath) {
        this.memberBackgroundFilePath = memberBackgroundFilePath;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberPreferenceLanguage() {
        return memberPreferenceLanguage;
    }

    public void setMemberPreferenceLanguage(String memberPreferenceLanguage) {
        this.memberPreferenceLanguage = memberPreferenceLanguage;
    }

    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }

    public String getMemberSignature() {
        return memberSignature;
    }

    public void setMemberSignature(String memberSignature) {
        this.memberSignature = memberSignature;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getWechatUnionId() {
        return wechatUnionId;
    }

    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    public String getWechatNickName() {
        return wechatNickName;
    }

    public void setWechatNickName(String wechatNickName) {
        this.wechatNickName = wechatNickName;
    }

    public String getWechatSex() {
        return wechatSex;
    }

    public void setWechatSex(String wechatSex) {
        this.wechatSex = wechatSex;
    }

    public String getWechatCountry() {
        return wechatCountry;
    }

    public void setWechatCountry(String wechatCountry) {
        this.wechatCountry = wechatCountry;
    }

    public String getWechatProvince() {
        return wechatProvince;
    }

    public void setWechatProvince(String wechatProvince) {
        this.wechatProvince = wechatProvince;
    }

    public String getWechatCity() {
        return wechatCity;
    }

    public void setWechatCity(String wechatCity) {
        this.wechatCity = wechatCity;
    }

    public String getWechatHeadImgFileId() {
        return wechatHeadImgFileId;
    }

    public void setWechatHeadImgFileId(String wechatHeadImgFileId) {
        this.wechatHeadImgFileId = wechatHeadImgFileId;
    }

    public String getWechatHeadImgUrl() {
        return wechatHeadImgUrl;
    }

    public void setWechatHeadImgUrl(String wechatHeadImgUrl) {
        this.wechatHeadImgUrl = wechatHeadImgUrl;
    }

    public String getWehchatPrivilege() {
        return wehchatPrivilege;
    }

    public void setWehchatPrivilege(String wehchatPrivilege) {
        this.wehchatPrivilege = wehchatPrivilege;
    }

}