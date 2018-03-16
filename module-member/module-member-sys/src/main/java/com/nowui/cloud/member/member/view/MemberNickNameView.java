package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员昵称视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_nick_name_info")
public class MemberNickNameView extends BaseView {

    /**
     * 会员昵称编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员昵称编号不能为空")
    @Length(max = 32, message = "会员昵称编号长度超出限制")
    private String memberNickNameId;
    public static final String MEMBER_NICK_NAME_ID = "memberNickNameId";

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
     * 会员昵称
     */
    @Field
    @NotNull(message = "会员昵称不能为空")
    @Length(max = 200, message = "会员昵称长度超出限制")
    private String memberNickName;
    public static final String MEMBER_NICK_NAME = "memberNickName";


    public String getMemberNickNameId() {
        return memberNickNameId;
    }

    public void setMemberNickNameId(String memberNickNameId) {
        this.memberNickNameId = memberNickNameId;
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
    
    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }
    

}