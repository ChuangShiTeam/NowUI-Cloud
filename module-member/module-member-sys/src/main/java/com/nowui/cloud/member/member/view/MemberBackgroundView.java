package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员背景视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_background_info")
public class MemberBackgroundView extends BaseView {

    /**
     * 会员背景编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员背景编号不能为空")
    @Length(max = 32, message = "会员背景编号长度超出限制")
    private String memberBackgroundId;
    public static final String MEMBER_BACKGROUND_ID = "memberBackgroundId";

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
     * 会员背景文件编号
     */
    @Field
    @NotNull(message = "会员背景文件编号不能为空")
    @Length(max = 32, message = "会员背景文件编号长度超出限制")
    private String memberBackgroundFileId;
    public static final String MEMBER_BACKGROUND_FILE_ID = "memberBackgroundFileId";


    public String getMemberBackgroundId() {
        return memberBackgroundId;
    }

    public void setMemberBackgroundId(String memberBackgroundId) {
        this.memberBackgroundId = memberBackgroundId;
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
    
    public String getMemberBackgroundFileId() {
        return memberBackgroundFileId;
    }

    public void setMemberBackgroundFileId(String memberBackgroundFileId) {
        this.memberBackgroundFileId = memberBackgroundFileId;
    }
    

}