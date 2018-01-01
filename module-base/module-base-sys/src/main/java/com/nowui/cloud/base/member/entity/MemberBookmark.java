package com.nowui.cloud.base.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员收藏
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@TableName(value = "member_bookmark_info")
public class MemberBookmark extends BaseEntity {

    /**
     * 会员收藏编号
     */
    @TableId
    @NotNull(message = "会员收藏编号不能为空")
    @Length(max = 32, message = "会员收藏编号长度超出限制")
    private String memberBookmarkId;
    public static final String MEMBER_BOOKMARK_ID = "memberBookmarkId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 收藏主体编号
     */
    @TableField
    @NotNull(message = "收藏主体编号不能为空")
    @Length(max = 32, message = "收藏主体编号长度超出限制")
    private String objectId;
    public static final String OBJECT_ID = "objectId";

    /**
     * 会员收藏标题
     */
    @TableField
    @NotNull(message = "会员收藏标题不能为空")
    @Length(max = 200, message = "会员收藏标题长度超出限制")
    private String memberBookmarkTitle;
    public static final String MEMBER_BOOKMARK_TITLE = "memberBookmarkTitle";


    public String getMemberBookmarkId() {
        return getString(MEMBER_BOOKMARK_ID);
    }

    public void setMemberBookmarkId(String memberBookmarkId) {
        put(MEMBER_BOOKMARK_ID, memberBookmarkId);
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
    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }
    public String getObjectId() {
        return getString(OBJECT_ID);
    }

    public void setObjectId(String objectId) {
        put(OBJECT_ID, objectId);
    }
    public String getMemberBookmarkTitle() {
        return getString(MEMBER_BOOKMARK_TITLE);
    }

    public void setMemberBookmarkTitle(String memberBookmarkTitle) {
        put(MEMBER_BOOKMARK_TITLE, memberBookmarkTitle);
    }

}