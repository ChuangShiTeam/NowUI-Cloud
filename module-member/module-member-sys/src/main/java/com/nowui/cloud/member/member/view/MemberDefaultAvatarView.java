package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员默认头像视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_default_avatar_info")
public class MemberDefaultAvatarView extends BaseView {

    /**
     * 会员默认头像编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员默认头像编号不能为空")
    @Length(max = 32, message = "会员默认头像编号长度超出限制")
    private String memberDefaultAvatarId;
    public static final String MEMBER_DEFAULT_AVATAR_ID = "memberDefaultAvatarId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 头像文件编号
     */
    @Field
    @NotNull(message = "头像文件编号不能为空")
    @Length(max = 32, message = "头像文件编号长度超出限制")
    private String userAvatarFileId;
    public static final String USER_AVATAR_FILE_ID = "userAvatarFileId";


    public String getMemberDefaultAvatarId() {
        return memberDefaultAvatarId;
    }

    public void setMemberDefaultAvatarId(String memberDefaultAvatarId) {
        this.memberDefaultAvatarId = memberDefaultAvatarId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getUserAvatarFileId() {
        return userAvatarFileId;
    }

    public void setUserAvatarFileId(String userAvatarFileId) {
        this.userAvatarFileId = userAvatarFileId;
    }
    

}