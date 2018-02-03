package com.nowui.cloud.member.member.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员二维码编号	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_qrcode_info")
public class MemberQrcodeView extends BaseView {

    /**
     * 会员二维码编号
     */
    @Field
    private String memberQrcodeId;
    public static final String MEMBER_QRCODE_ID = "memberQrcodeId";

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
    private String memerId;
    public static final String MEMER_ID = "memerId";


    public String getMemberQrcodeId() {
        return getString(MEMBER_QRCODE_ID);
    }

    public void setMemberQrcodeId(String memberQrcodeId) {
        put(MEMBER_QRCODE_ID, memberQrcodeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemerId() {
        return getString(MEMER_ID);
    }

    public void setMemerId(String memerId) {
        put(MEMER_ID, memerId);
    }


}