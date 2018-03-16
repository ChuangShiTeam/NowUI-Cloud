package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员微信视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_wechat_info")
public class MemberWechatView extends BaseView {
    
    /**
     * 会员微信编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员微信编号不能为空")
    @Length(max = 32, message = "会员微信编号长度超出限制")
    private String memberWechatId;
    public static final String MEMBER_WECHAT_ID = "memberWechatId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

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


    public String getMemberWechatId() {
        return memberWechatId;
    }

    public void setMemberWechatId(String memberWechatId) {
        this.memberWechatId = memberWechatId;
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