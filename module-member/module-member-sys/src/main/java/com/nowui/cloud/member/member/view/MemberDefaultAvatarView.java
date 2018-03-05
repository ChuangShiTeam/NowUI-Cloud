package com.nowui.cloud.member.member.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户默认头像视图
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Component
@Document(collection = "member_default_avatar_info")
public class MemberDefaultAvatarView extends BaseView {

    /**
     * 用户默认头像编号
     */
    @KeyId
    @Field
    @NotNull(message = "用户默认头像编号不能为空")
    private String memberDefaultAvatarId;
    public static final String MEMBER_DEFAULT_AVATAR_ID = "memberDefaultAvatarId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 头像文件编号
     */
    @Field
    @NotNull(message = "头像文件编号不能为空")
    private String userAvatarFileId;
    public static final String USER_AVATAR_FILE_ID = "userAvatarFileId";

    /**
     * 头像文件路径
     */
    @Field
    @NotNull(message = "头像文件路径不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";

    public String getMemberDefaultAvatarId() {
        return getString(MEMBER_DEFAULT_AVATAR_ID);
    }

    public void setMemberDefaultAvatarId(String memberDefaultAvatarId) {
        put(MEMBER_DEFAULT_AVATAR_ID, memberDefaultAvatarId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getUserAvatarFileId() {
        return getString(USER_AVATAR_FILE_ID);
    }

    public void setUserAvatarFileId(String userAvatarFileId) {
        put(USER_AVATAR_FILE_ID, userAvatarFileId);
    }
    
    public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
    }

    public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
    }

}