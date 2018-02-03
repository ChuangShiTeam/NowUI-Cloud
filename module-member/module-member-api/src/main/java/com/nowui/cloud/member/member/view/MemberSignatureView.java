package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员签名	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_signature_info")
public class MemberSignatureView extends BaseView {

    /**
     * 会员签名编号
     */
    @Field
    private String memberSignatureId;
    public static final String MEMBER_SIGNATURE_ID = "memberSignatureId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 会员签名
     */
    @Field
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";


    public String getMemberSignatureId() {
        return getString(MEMBER_SIGNATURE_ID);
    }

    public void setMemberSignatureId(String memberSignatureId) {
        put(MEMBER_SIGNATURE_ID, memberSignatureId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getMemberSignature() {
        return getString(MEMBER_SIGNATURE);
    }

    public void setMemberSignature(String memberSignature) {
        put(MEMBER_SIGNATURE, memberSignature);
    }


}