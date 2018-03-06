package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员默认头像
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Component
@TableName(value = "member_default_avatar_info")
public class MemberDefaultAvatar extends BaseEntity {

    /**
     * 会员默认头像编号
     */
    @Id
    @TableId
    @NotNull(message = "会员默认头像编号不能为空")
    @Length(max = 32, message = "会员默认头像编号长度超出限制")
    private String memberDefaultAvatarId;
    public static final String MEMBER_DEFAULT_AVATAR_ID = "memberDefaultAvatarId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 头像文件编号
     */
    @TableField
    @NotNull(message = "头像文件编号不能为空")
    @Length(max = 32, message = "头像文件编号长度超出限制")
    private String userAvatarFileId;
    public static final String USER_AVATAR_FILE_ID = "userAvatarFileId";

    /**
     * 头像文件路径
     */
    @TableField(exist = false)
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