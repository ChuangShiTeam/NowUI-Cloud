package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员性别视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_sex_info")
public class MemberSexView extends BaseView {

    /**
     * 会员性别编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员性别编号不能为空")
    @Length(max = 32, message = "会员性别编号长度超出限制")
    private String memberSexId;
    public static final String MEMBER_SEX_ID = "memberSexId";

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
     * 会员性别
     */
    @Field
    @NotNull(message = "会员性别不能为空")
    @Length(max = 1, message = "会员性别长度超出限制")
    private String memberSex;
    public static final String MEMBER_SEX = "memberSex";


    public String getMemberSexId() {
        return memberSexId;
    }

    public void setMemberSexId(String memberSexId) {
        this.memberSexId = memberSexId;
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
    
    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }
    

}