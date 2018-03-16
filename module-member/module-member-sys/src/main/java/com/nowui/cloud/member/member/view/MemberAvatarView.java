package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员头像视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_avatar_info")
public class MemberAvatarView extends BaseView {

    /**
     * 会员头像编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员头像编号不能为空")
    @Length(max = 32, message = "会员头像编号长度超出限制")
    private String memberAvatarId;
    public static final String MEMBER_AVATAR_ID = "memberAvatarId";

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
     * 会员头像文件编号
     */
    @Field
    @NotNull(message = "会员头像文件编号不能为空")
    @Length(max = 32, message = "会员头像文件编号长度超出限制")
    private String memberAvatarFileId;
    public static final String MEMBER_AVATAR_FILE_ID = "memberAvatarFileId";


    public String getMemberAvatarId() {
        return memberAvatarId;
    }

    public void setMemberAvatarId(String memberAvatarId) {
        this.memberAvatarId = memberAvatarId;
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
    
    public String getMemberAvatarFileId() {
        return memberAvatarFileId;
    }

    public void setMemberAvatarFileId(String memberAvatarFileId) {
        this.memberAvatarFileId = memberAvatarFileId;
    }
    

}