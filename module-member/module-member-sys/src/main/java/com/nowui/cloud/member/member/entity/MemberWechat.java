package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员微信
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_wechat_info")
public class MemberWechat extends BaseEntity {

    /**
     * 会员微信编号
     */
    @TableId
    @TableField
    private String memberWechatId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 会员编号
     */
    @TableField
    private String memberId;

    /**
     * 微信用户唯一标识
     */
    @TableField
    private String wechatOpenId;

    /**
     * 微信多应用用户共享唯一标识
     */
    @TableField
    private String wechatUnionId;

    /**
     * 微信昵称
     */
    @TableField
    private String wechatNickName;

    /**
     * 性别
     */
    @TableField
    private String wechatSex;

    /**
     * 国家
     */
    @TableField
    private String wechatCountry;

    /**
     * 省
     */
    @TableField
    private String wechatProvince;

    /**
     * 市
     */
    @TableField
    private String wechatCity;

    /**
     * 微信头像本地文件Id
     */
    @TableField
    private String wechatHeadImgFileId;

    /**
     * 微信头像地址
     */
    @TableField
    private String wechatHeadImgUrl;

    /**
     * 微信用户特权
     */
    @TableField
    private String wehchatPrivilege;


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